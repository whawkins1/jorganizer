package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import ui.frame.OrganizerMainFrame;

import model.table.MediaTableModel;


public final class CloseAlbumActionListener 
                                implements ActionListener {

    private final DefaultComboBoxModel<String> fCBModel;
    private final JComboBox<String> fCB;
    private final MediaTableModel fTableModel;
    private final OrganizerMainFrame fMain;
    
    public CloseAlbumActionListener(final DefaultComboBoxModel<String> aCBModel, 
                                    final JComboBox<String> aCB,
                                    final MediaTableModel aTableModel, 
                                    final OrganizerMainFrame aMain) {
        this.fCBModel = aCBModel;
        this.fCB = aCB;
        this.fTableModel = aTableModel;
        this.fMain = aMain;
        
    }
    
    
        @Override public final void actionPerformed(final ActionEvent event) {
            fMain.modifyAccessGUI(false);
            fCBModel.removeAllElements();
            fCB.setEnabled(false);
            fTableModel.clearTable();
            fMain.setWindowActive(true);
        }
}