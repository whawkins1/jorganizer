package ui.menuitem;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public abstract class MenuItemMain extends JMenuItem {
    private static final long serialVersionUID = 1L;
    
    public MenuItemMain(final String aTitle,
                        final int aKeyEvent, 
                        final int aModifier,
                        final JMenuBar aParent) {
        super(aTitle);
        setAccelerator(KeyStroke.getKeyStroke(aKeyEvent, aModifier));
        setMnemonic(aKeyEvent);
            
        aParent.add(this);
    }
}