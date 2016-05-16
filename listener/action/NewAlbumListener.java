package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import list.Album;

import listener.mouse.TableMouseListener;

import model.table.MediaTableModel;

import table.MediaTable;

import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.menu.AlbumMenu;
import ui.panel.TableHeaderPanel;

public final class NewAlbumListener 
                    implements ActionListener {
    private final OrganizerMainFrame fMainFrame;
    private final List<Album> fAlbumList;
    private final MediaTable fTable;
    private final AlbumComboBox fAlbumComboBox;
    private final TableMouseListener fTableMouseListener; 
    private final AlbumMenu fAlbumMenu;
    
    public NewAlbumListener(final OrganizerMainFrame aMainFrame, 
                            final List<Album> aAlbumList, 
                            final MediaTable aTable, 
                            final AlbumComboBox aAlbumComboBox,
                            final TableMouseListener aTableMouseListener,
                            final AlbumMenu aAlbumMenu) {
        this.fMainFrame = aMainFrame;
        this.fAlbumList = aAlbumList;
        this.fTable = aTable;
        this.fAlbumComboBox = aAlbumComboBox;
        this.fTableMouseListener = aTableMouseListener;
        this.fAlbumMenu = aAlbumMenu;
        
    }
    
    @Override public final void actionPerformed(final ActionEvent event) {
            final String fCreatedAlbumTitle = JOptionPane.showInputDialog(fMainFrame, "Enter Album Title");
            
            if(fCreatedAlbumTitle.isEmpty()) {   
                final Album createdAlbum = new Album(fCreatedAlbumTitle); 
                fAlbumList.add(createdAlbum);
                fTableMouseListener.setCurrentAlbum(createdAlbum);
                
                final JTableHeader tableHeader = fTable.getTableHeader();
                final MediaTableModel tableModel = ((MediaTableModel)fTable.getModel());
                for(int i = 0; i < fTable.getColumnCount(); i++) {
                    final TableColumnModel model = fTable.getColumnModel();
                    final TableColumn column = model.getColumn(i);
                    new TableHeaderPanel(column, tableHeader, createdAlbum, tableModel);
                }
                
                final DefaultComboBoxModel<String> comboBoxModel = (DefaultComboBoxModel<String>)fAlbumComboBox.getModel();
                comboBoxModel.addElement(fCreatedAlbumTitle);
                fAlbumComboBox.setSelectedIndex(fAlbumList.indexOf(createdAlbum));
                
                if (comboBoxModel.getSize() == 1)  
                    fAlbumMenu.setEnabled(true);
                   
                    if(fMainFrame.windowIsNotActive())        {
                        OrganizerMainFrame.modifyAccessGUI(true);  
                        fAlbumComboBox.setEnabled(true);
                        fMainFrame.setWindowActive(false);
                    }
                    fTable.repaint();
            }
    }
}