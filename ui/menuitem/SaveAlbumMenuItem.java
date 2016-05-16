package ui.menuitem;

import java.awt.event.KeyEvent;

import javax.swing.JSeparator;

import ui.menubar.MenuBar;
import listener.action.SaveAlbumActionListener;

public final class SaveAlbumMenuItem extends MenuItemMain {
    private static final long serialVersionUID = 1L;
    
    public SaveAlbumMenuItem(final MenuBar aParent) {
        super("Save", KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK, aParent);
        add(new JSeparator());
        addActionListener(new SaveAlbumActionListener());
        setEnabled(false);
    }
}