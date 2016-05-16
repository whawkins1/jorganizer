package ui.menuitem;

import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import ui.frame.OrganizerMainFrame;
import ui.menubar.MenuBar;

import listener.action.CloseAlbumActionListener;

import model.table.MediaTableModel;

public final class CloseAlbumMenuItem extends MenuItemMain {
    
    private static final long serialVersionUID = 1L;
    
    public CloseAlbumMenuItem(final DefaultComboBoxModel<String> aCBModel, 
                                    final JComboBox<String> aCB,
                                    final MediaTableModel aTableModel, 
                                    final OrganizerMainFrame aMain,
                                    final MenuBar aParent) {
        super("Close", KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK, aParent);
        addActionListener(new CloseAlbumActionListener(aCBModel, aCB, aTableModel, aMain ));
        add(new JSeparator());
        setEnabled(false);
    }
}