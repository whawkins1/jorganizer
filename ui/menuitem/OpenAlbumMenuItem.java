package ui.menuitem;

import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;

import listener.action.OpenAlbumActionListener;


public final class OpenAlbumMenuItem extends MenuItemMain {
   
    private static final long serialVersionUID = 1L;
    
    public OpenAlbumMenuItem(final JMenuBar aParent) {
        super("Open", KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK, aParent);
        
        setEnabled(false);
        addActionListener(new OpenAlbumActionListener());
    }
}