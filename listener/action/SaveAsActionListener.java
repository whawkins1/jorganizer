package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import list.Album;
import ui.frame.OrganizerMainFrame;
import utility.Utility;


public final class SaveAsActionListener 
                            implements ActionListener {
     private final OrganizerMainFrame fMainFrame;
     private final List<Album> fAlbumList;
    
     public SaveAsActionListener(final OrganizerMainFrame aMainFrame,
                                 final List<Album> aAlbumList) {
         this.fMainFrame = aMainFrame;
         this.fAlbumList = aAlbumList;
     }
    
    
    @Override public final void actionPerformed(final ActionEvent event) {
        Utility.saveAsAlbum(fMainFrame, fAlbumList);
    }
}