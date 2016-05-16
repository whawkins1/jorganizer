package ui.combobox;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import list.Album;
import listener.item.AlbumSelectItemListener;
import model.table.MediaTableModel;

public final class AlbumComboBox extends JComboBox<String>{
    private static final long serialVersionUID = 1L;
    
    public AlbumComboBox(final MediaTableModel aTableModel, 
                         final ArrayList<Album> aAlbumList, 
                         final String[] aAlbumTitles) {
        super(new DefaultComboBoxModel<String>(aAlbumTitles));
        addItemListener(new AlbumSelectItemListener(aTableModel, this, aAlbumList));
        setActionCommand("Album Selected");
        setPreferredSize(new Dimension(200, 20));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setEditable(true);
        setEnabled(false);
    }
}
