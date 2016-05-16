package ui.menuitem;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JSeparator;





import list.Album;
import listener.action.NewAlbumListener;
import listener.mouse.TableMouseListener;
import table.MediaTable;
import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.menu.AlbumMenu;
import ui.menubar.MenuBar;

public final class NewAlbumMenuItem extends MenuItemMain {
    private static final long serialVersionUID = 1L;
    
    public NewAlbumMenuItem(final MenuBar aMenuParent,
                            final OrganizerMainFrame aMainFrame, 
                            final List<Album> aAlbumList, 
                            final MediaTable aTable, 
                            final AlbumComboBox aAlbumComboBox,
                            final TableMouseListener aTableMouseListener,
                            final AlbumMenu aAlbumMenu) {
        super("New", KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK, aMenuParent);
        addActionListener(new NewAlbumListener(aMainFrame, 
                                               aAlbumList, 
                                               aTable, 
                                               aAlbumComboBox,
                                               aTableMouseListener,
                                               aAlbumMenu));
        add(new JSeparator());
        setEnabled(false);
    }
}