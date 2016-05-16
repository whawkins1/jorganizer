package ui.menubar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;

import model.table.MediaTableModel;

import table.MediaTable;

import ui.frame.OrganizerMainFrame;
import ui.menu.AlbumMenu;
import ui.menu.FileMenu;
import ui.menu.HelpMenu;

public final class MenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;
    private final FileMenu fFileMenu;
    private final AlbumMenu fAlbumMenu;
    private final MediaTableModel fTableModel;
    
    
    public MenuBar(final DefaultComboBoxModel<String> aCBModel, 
            final JComboBox<String> aCB,
            final MediaTable aTable,
            final OrganizerMainFrame aMainFrame,
            final MenuBar aParent) {
            
            fTableModel = ((MediaTableModel)aTable.getModel());
            fFileMenu = new FileMenu(this, 
                                     aCBModel, 
                                     aCB,
                                      fTableModel,
                                     aMainFrame,
                                     this);
            fAlbumMenu = new AlbumMenu();
        add(fFileMenu);
        add(fAlbumMenu);
        add(new HelpMenu(aMainFrame));
    }
    
    public final void checkMenuAccess()  {
        final int fTableRowCount = fTableModel.getRowCount();     
        
        if (fTableRowCount == 0)  {
              fAlbumMenu.setMenuItemsEnabled(false);
              fFileMenu.setEnabledMenuItems(false);
         } else if((fTableRowCount == 1) && (!(fAlbumMenu.isEnabled())))  {
              fAlbumMenu.setMenuItemsEnabled(true);
              fFileMenu.setEnabledMenuItems(true);
        }
    }       
}