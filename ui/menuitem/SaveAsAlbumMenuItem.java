package ui.menuitem;

import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;

import listener.action.SaveAsActionListener;

public final class SaveAsAlbumMenuItem extends MenuItemMain {
    private static final long serialVersionUID = 1L;

    public SaveAsAlbumMenuItem(final JMenuBar aParent) { 
        super("Save As", KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK, aParent);
        addActionListener(new SaveAsActionListener());
        setEnabled(false);
    }
}