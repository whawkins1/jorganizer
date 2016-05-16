package ui.panel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import list.Album;
import model.table.MediaTableModel;
import ui.button.radio.SearchDescriptionRadioButton;
import ui.button.radio.SearchTagsRadioButton;
import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.textfield.SearchAlbumTextField;

public final class SearchPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    public SearchPanel(final SearchAlbumTextField aSearchTextField,
                       final MediaTableModel aTableModel,
                       final ArrayList<Album> aAlbumList,
                       final OrganizerMainFrame aParent,
                       final SearchTagsRadioButton aSearchTagsRadioButton,
                       final SearchDescriptionRadioButton aSearchDescriptionRadioButton,
                       final AlbumComboBox aAlbumComboBox) {
        super(new BorderLayout());
        
        add(new SelectAlbumComboBoxPanel(aTableModel, aAlbumList, aAlbumComboBox), BorderLayout.WEST);
        final InputSearchPanel inputSearchPanel = new InputSearchPanel(aSearchTextField,
                                                                        aParent,
                                                                        aSearchDescriptionRadioButton,
                                                                        aSearchTagsRadioButton,
                                                                        aAlbumComboBox,
                                                                        aTableModel,
                                                                        aAlbumList);
        add(inputSearchPanel, BorderLayout.EAST);
    }
}