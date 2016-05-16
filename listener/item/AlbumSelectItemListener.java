package listener.item;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import ui.combobox.AlbumComboBox;
import list.Album;
import model.table.MediaTableModel;

public final class AlbumSelectItemListener 
                                implements ItemListener {
    private final MediaTableModel fTableModel;
    private final AlbumComboBox fComboBox;
    private final ArrayList<Album> fAlbumList;
    
    public AlbumSelectItemListener(final MediaTableModel aTableModel, 
                                   final AlbumComboBox aComboBox,
                                   final ArrayList<Album> aAlbumList) {
        this.fTableModel = aTableModel;
        this.fComboBox = aComboBox;
        this.fAlbumList = aAlbumList;
    }
    
    
    @Override public final void itemStateChanged(final ItemEvent event) {
        final boolean isSelection = (event.getStateChange() == ItemEvent.SELECTED);
        if (isSelection) {
            fTableModel.clearTable();
            try {
                final int albumIndex = fComboBox.getSelectedIndex();
                if(albumIndex != -1)  {
                    Album albumChange = fAlbumList.get(albumIndex);
                    for(int m = 0; m < albumChange.size(); m++)   {
                        fTableModel.addMedia(albumChange.getMedia(m));
                    }                  
                    fTableModel.fireTableDataChanged();   
                }   
            } catch (IndexOutOfBoundsException ioobe)  {
               ioobe.printStackTrace();
            }    
        }
    }
}