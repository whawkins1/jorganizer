package ui.panel;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import list.Album;
import model.table.MediaTableModel;
import ui.combobox.AlbumComboBox;
import ui.label.TitleComboBoxLabel;

public final class SelectAlbumComboBoxPanel extends JPanel {
  
    private static final long serialVersionUID = 1L;
    
    public SelectAlbumComboBoxPanel(final MediaTableModel aTableModel, 
                                    final ArrayList<Album> aAlbumList,
                                    final AlbumComboBox aAlbumComboBox) {
        super(new FlowLayout(FlowLayout.LEFT));
        
        
        
        add(new TitleComboBoxLabel());       
        add(aAlbumComboBox);
    }
}