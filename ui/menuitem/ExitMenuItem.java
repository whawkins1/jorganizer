package ui.menuitem;

import java.awt.event.KeyEvent;

import javax.swing.JSeparator;

import ui.menubar.MenuBar;


public final class ExitMenuItem extends MenuItemMain {
    private static final long serialVersionUID = 1L;
    
    public ExitMenuItem(final MenuBar aMenuParent) {
        super("Exit", KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK, aMenuParent);
        add(new JSeparator());
        setEnabled(false);
    }
}