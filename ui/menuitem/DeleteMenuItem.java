package ui.menuitem;

import java.awt.event.KeyEvent;


import ui.menubar.MenuBar;

public final class DeleteMenuItem extends MenuItemMain {
  
    private static final long serialVersionUID = 1L;
    
    public DeleteMenuItem(final MenuBar aMenuParent) {
        super("Delete", KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK, aMenuParent);
        setEnabled(false);
    }

}
