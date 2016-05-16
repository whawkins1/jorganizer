package ui.menu;

import javax.swing.JMenu;

import ui.menuitemcheckbox.ViewAudioCheckboxMenuItem;
import ui.menuitemcheckbox.ViewImagesCheckboxMenuItem;
import ui.menuitemcheckbox.ViewVideoCheckboxMenuItem;

public final class FilterSubMenu extends JMenu {
    private static final long serialVersionUID = 1L;
    
    public FilterSubMenu() {
        super("Filter By:");
        
        add(new ViewAudioCheckboxMenuItem());
        add(new ViewImagesCheckboxMenuItem());
        add(new ViewVideoCheckboxMenuItem());
        setEnabled(false);
    }

}
