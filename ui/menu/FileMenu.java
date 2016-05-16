package ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JSeparator;

import model.table.MediaTableModel;

import ui.frame.OrganizerMainFrame;
import ui.menubar.MenuBar;
import ui.menuitem.CloseAlbumMenuItem;
import ui.menuitem.DeleteMenuItem;
import ui.menuitem.ExitMenuItem;
import ui.menuitem.NewAlbumMenuItem;
import ui.menuitem.OpenAlbumMenuItem;
import ui.menuitem.SaveAlbumMenuItem;
import ui.menuitem.SaveAsAlbumMenuItem;


public final class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;
    private final SaveAlbumMenuItem fSaveAlbumMenuItem;
    private final SaveAsAlbumMenuItem fSaveAsAlbumMenuItem;
    private final CloseAlbumMenuItem fCloseAlbumMenuItem;
    
    
    public FileMenu(final MenuBar aMenuParent, 
                    final DefaultComboBoxModel<String> aCBModel, 
                    final JComboBox<String> aCB,
                    final MediaTableModel aTableModel, 
                    final OrganizerMainFrame aMain,
                    final MenuBar aParent) {
        
        super("File");
        add(new NewAlbumMenuItem(aMenuParent));
        add(new OpenAlbumMenuItem(aMenuParent));
        fCloseAlbumMenuItem = new CloseAlbumMenuItem(aCBModel, 
                                                     aCB,
                                                     aTableModel, 
                                                     aMain,
                                                     aParent);
        add(fCloseAlbumMenuItem);
        fSaveAlbumMenuItem = new SaveAlbumMenuItem(aMenuParent); 
        add(fSaveAlbumMenuItem);
        fSaveAsAlbumMenuItem = new SaveAsAlbumMenuItem(aMenuParent); 
        add(fSaveAsAlbumMenuItem);
        add(new DeleteMenuItem(aMenuParent));
        add(new JSeparator());
        add(new ExitMenuItem(aMenuParent));
        setMnemonic(KeyEvent.VK_F);
    }
    
        public final void setEnabledMenuItems(final boolean aEnable) {
            fSaveAlbumMenuItem.setEnabled(aEnable);
            fSaveAsAlbumMenuItem.setEnabled(false);
            fCloseAlbumMenuItem.setEnabled(false);  
        }
}