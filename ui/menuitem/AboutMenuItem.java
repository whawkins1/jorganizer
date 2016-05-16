package ui.menuitem;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.OpenAboutListener;

public final class AboutMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;
    
    public AboutMenuItem(final JFrame aParent) {
        super("About");
        addActionListener(new OpenAboutListener(aParent));
    }
}