package table;

import java.awt.Dimension;

import javax.swing.JTable;

import droptarget.TableDropTarget;
import ui.frame.OrganizerMainFrame;
import ui.textfield.SearchAlbumTextField;
import listener.focus.MediaTableFocusListener;
import listener.mouse.TableMouseListener;
import model.table.MediaTableModel;

public final class MediaTable extends JTable {
    private static final long serialVersionUID = 1L;
    
    public MediaTable(final OrganizerMainFrame aFrame,
                      final MediaTableModel aModel,
                      final boolean aEnableAudioVideo,
                      final String aVlcLocation,
                      final SearchAlbumTextField aSearchTextField) {
        super(new MediaTableModel());
        
        setPreferredScrollableViewportSize(new Dimension(800, 200));
        setFillsViewportHeight(true);
        addMouseListener(new TableMouseListener(aFrame, aModel, this, aEnableAudioVideo, aVlcLocation));
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setFocusable(false);
        addFocusListener(new MediaTableFocusListener(aSearchTextField));
        this.setDropTarget(new TableDropTarget());
        setEnabled(false);
    }
}