package jorganizerpackage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.JLabel;

import filter.file.AlbumFilter;
import list.Album;
import mediadata.MediaData;
import model.table.MediaTableModel;
import ui.dialog.AboutDialog;
import ui.frame.OrganizerMainFrame;
import ui.menubar.MenuBar;
import ui.panel.TableHeaderPanel;
import utility.Utility;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.event.KeyAdapter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Date;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public final class JOrganizerMain 
                                 implements ActionListener  {
    private Album loadAlbum;
    final private JFrame frame;
    protected JButton fStartSlideShowButton; 
    protected JButton fSearchAlbumButton;
    private JRadioButton fSearchTagsRadBut;
    private JRadioButton fSearchDescrRadBut;
    private  JComboBox<String> fAlbumComboBox;
    private static  JTable fTable;
    private JTextField fSearchCurrentAlbumTextField;
    private DefaultComboBoxModel<String> fAlbumComboModel;
    private JMenu fFileMenu;
    private JMenu fAlbumMenu; 
    private static JMenu fMediaViewSortSubMenu;
    private JMenu fHelpMenu;
    @SuppressWarnings("unused")
    private JMenuItem fMenuOpenAlbum; 
    private static JMenuItem fMenuSaveAlbum;
    private static JMenuItem fMenuSaveAsAlbum;
    private static JMenuItem fMenuCloseAlbum;
    @SuppressWarnings("unused")
    private JMenuItem fMenuExitAlbum;
    @SuppressWarnings("unused")
    private JMenuItem fMenuNewAlbum;
    private JMenuItem fMenuDeleteAlbum;
    private JMenuItem fMenuViewAudio;
    private JMenuItem fMenuViewImages;
    private JMenuItem fMenuViewVideos;
    @SuppressWarnings("unused")
    private JMenuItem fMenuAboutAlbum;
    protected String fCreatedAlbumTitle;
    private Album fCurrentAlbum;
    private MediaTableModel fTableModel;
    private TableMouseListener fTml;
    private boolean fWindowNotActive;
    private static File fCurrentFile;
    private List<Album> fAlbumList = new ArrayList<Album>();
    AddMediaDialog fAmd;
    private static boolean fSaved;
    TableRowSorter<TableModel> fSorter;
    List<RowFilter<Object,Object>> ffAllFilterList;
    List<RowFilter<Object,Object>> ffAudiofVideoFilterList;
    List<RowFilter<Object,Object>> fImagefAudioFilterList;
    List<RowFilter<Object,Object>> ffImagefVideoFilterList;
    RowFilter<Object, Object> fAllFilter; 
    RowFilter<Object, Object> fImageFilter;
    RowFilter<Object, Object> fAudioFilter;
    RowFilter<Object, Object> fVideoFilter;
    RowFilter<Object, Object> fAudiofImageFilter;
    RowFilter<Object, Object> fAudiofVideoFilter;
    RowFilter<Object, Object> fImagefVideoFilter;
   
    
	 JOrganizerMain(boolean aEnableAudioVideo, String aVlcLocation)	{
		fCurrentAlbum = null;
		fCurrentFile = null;
		fWindowNotActive = true;
        fSaved = false;
		
        frame = new OrganizerMainFrame(aVlcLocation, aEnableAudioVideo); 	    
	}
		
	 
	 @Override public final void actionPerformed(final ActionEvent e)		{
			final String command = e.getActionCommand();
			switch(command) {
			   
			case "Filter Media":
	    	     if(fMenuViewImages.isSelected()) {
	    	    	 fSorter.setRowFilter(fImageFilter);			     	 			 	 	 
	    	     } else if (fMenuViewAudio.isSelected())  {
	    	    	 fSorter.setRowFilter(fAudioFilter);
	    	     } else if (fMenuViewVideos.isSelected())  {
	    	    	 fSorter.setRowFilter(fVideoFilter);	    	     
	    	     } else if((fMenuViewImages.isSelected()) && (fMenuViewAudio.isSelected()))  {
	    	    	 fSorter.setRowFilter(fAudiofImageFilter);
	    	     } else if((fMenuViewImages.isSelected()) && (fMenuViewVideos.isSelected()))  {
	    	    	 fSorter.setRowFilter(fImagefVideoFilter);
	    	     } else if ((fMenuViewAudio.isSelected()) && (fMenuViewVideos.isSelected()))  {
	    	    	 fSorter.setRowFilter(fAudiofVideoFilter);
	    	     } else {
	    	    	 fSorter.setRowFilter(fAllFilter);  	 
	    	     }	    	     
	    	     fTableModel.fireTableDataChanged();
		 }
 }		
		
		
		
		
		
		private final void setfTableFilters()		{
			fSorter = new TableRowSorter<TableModel>(fTableModel); 		    
		    fTable.setRowSorter(fSorter);
		    	
		    
		    fImagefAudioFilterList = new ArrayList<RowFilter<Object,Object>>();
		    fImagefAudioFilterList.add(fAudioFilter);
		    fImagefAudioFilterList.add(fImageFilter);
		    
		    fAudiofImageFilter = RowFilter.orFilter(fImagefAudioFilterList);
		    
		    ffAudiofVideoFilterList = new ArrayList<RowFilter<Object,Object>>();
		    ffAudiofVideoFilterList.add(fAudioFilter);
		    ffAudiofVideoFilterList.add(fVideoFilter);
		    
		    fAudiofVideoFilter = RowFilter.orFilter(ffAudiofVideoFilterList);
		    
		    ffImagefVideoFilterList = new ArrayList<RowFilter<Object,Object>>();
		    ffImagefVideoFilterList.add(fImageFilter);
		    ffImagefVideoFilterList.add(fVideoFilter);
		    
		    fImagefVideoFilter = RowFilter.orFilter(ffImagefVideoFilterList);
		    
		    ffAllFilterList = new ArrayList<RowFilter<Object,Object>>();
		    ffAllFilterList.add(fAudioFilter);
		    ffAllFilterList.add(fImageFilter);
		    ffAllFilterList.add(fVideoFilter);
		    
		    fAllFilter = RowFilter.orFilter(ffAllFilterList);
		}
}