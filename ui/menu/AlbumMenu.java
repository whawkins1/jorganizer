package ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;


public final class AlbumMenu extends JMenu {
   
    private static final long serialVersionUID = 1L;
    private final FilterSubMenu fFilterSubMenu;
    
    public AlbumMenu() {
        super("Album");
        fFilterSubMenu = new FilterSubMenu(); 
        setMnemonic(KeyEvent.VK_A);
    }
    
    
        public final void setMenuItemsEnabled(final boolean aEnable) {
            fFilterSubMenu.setEnabled(aEnable);
        }
}