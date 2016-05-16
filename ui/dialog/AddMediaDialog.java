package ui.dialog;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JFormattedTextField;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.menubar.MenuBar;
import ui.panel.AddMediaMainPanel;
import ui.panel.DaySpinnerPanel;
import ui.panel.MonthSpinnerPanel;
import ui.panel.SpinnerMainPanel;
import ui.panel.YearSpinnerPanel;
import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;
import utility.Utility;
import filter.file.AudioFilter;
import filter.file.ImageFilter;
import filter.file.VideoFilter;
import list.Album;
import listener.action.AddResetListener;
import listener.focus.MediaDescriptionTextFieldFocusListener;
import listener.window.AddMediaWindowListener;
import mediadata.MediaData;
import model.table.MediaTableModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.lang.StringBuilder;
import java.lang.String;

public final class AddMediaDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    
    
    private Album fAlbumAddingTo;
    
    final private DateFormat fDf;
      
    
                
    public AddMediaDialog(final JFrame aParent, 
                          final MediaTableModel aTableModel,
                          final MenuBar aMenuBar,
                          final AlbumComboBox aAlbumComboBox,
                          final List<Album> aAlbumList,
                          final OrganizerMainFrame aMainFrame)      {
        super(aParent, "Add", true);
        
        fDf = new SimpleDateFormat("mm/dd/yyyy");
        fDf.setLenient(false);
        
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setSize(d.width/4, d.height/4);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        try {
            setIconImage(ImageIO.read(getClass().getResource("/resources/filecabinet.png")));
        } catch (IOException e) {
            Utility.showErrorMessage(aParent, "Error Retrieving filecabinet.png");
            e.printStackTrace();
        }
        pack();
        setVisible(false);          
        
        addWindowListener(new AddMediaWindowListener());
        final AddMediaMainPanel mainPanel = new AddMediaMainPanel(this, 
                                                                 aMenuBar,
                                                                 aAlbumComboBox,
                                                                 aAlbumList,
                                                                 aTableModel,
                                                                 aMainFrame);
        getContentPane().add(mainPanel);                    
    }   
    
    public final void setCurrentAlbum(Album currentAlbum)   {
        this.fAlbumAddingTo = currentAlbum;
        setTitle("Adding To " + fAlbumAddingTo.getTitle());
    }
    
    public final void setMediaFile(final File fMediaFile)  {
        this.fMediaFile = fMediaFile;
    }   
} 