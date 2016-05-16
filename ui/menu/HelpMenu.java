package ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;

import ui.menuitem.AboutMenuItem;

public final class HelpMenu extends JMenu {
    private static final long serialVersionUID = 1L;
    
    public HelpMenu(final JFrame aParent) {
        super("Help");
        add(new AboutMenuItem(aParent));
        setMnemonic(KeyEvent.VK_H);
    }
}