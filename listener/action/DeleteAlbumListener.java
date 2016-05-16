package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.frame.OrganizerMainFrame;

public final class DeleteAlbumListener 
                            implements ActionListener {

    @Override public final void actionPerformed(final ActionEvent event) {
        final int deleteIndex = fAlbumComboBox.getSelectedIndex(); 
        fAlbumComboModel.removeElementAt(deleteIndex);
           if(fAlbumComboModel.getSize() == 0)  {
               fMenuDeleteAlbum.setEnabled(false);
           } else  {
               fAlbumComboBox.setSelectedIndex(deleteIndex);
           }                   
           fTableModel.clearTable();
           modifyAccessGui(false);
    }
}