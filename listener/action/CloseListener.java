package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.frame.OrganizerMainFrame;

public final class CloseListener 
                       implements ActionListener {

    @Override public final void actionPerformed(final ActionEvent event) {
        OrganizerMainFrame.modifyAccessGUI(false);
        fAlbumComboModel.removeAllElements();
        fAlbumComboBox.setEnabled(false);
        fTableModel.clearTable();
        fWindowNotActive = true;
        
    }

}
