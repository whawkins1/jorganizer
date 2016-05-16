package ui.panel;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import list.Album;
import model.table.MediaTableModel;
import ui.button.radio.SearchDescriptionRadioButton;
import ui.button.radio.SearchTagsRadioButton;
import ui.button.regular.SearchAlbumButton;
import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.textfield.SearchAlbumTextField;

public final class InputSearchPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    public InputSearchPanel(final SearchAlbumTextField aSearchTextField,
                            final OrganizerMainFrame aParent,
                            final SearchDescriptionRadioButton aDescriptionRadioButton,
                            final SearchTagsRadioButton aTagsRadioButton,
                            final AlbumComboBox aAlbumComboBox,
                            final MediaTableModel aTableModel,
                            final ArrayList<Album> aAlbumList) {
        super(new FlowLayout(FlowLayout.RIGHT, 1, 2));
        
        add(new SearchTagsRadioButton());
        add(new SearchDescriptionRadioButton());
        add(aSearchTextField);
        add(new SearchAlbumButton(new ImageIcon(getClass().getResource("/resources/search.png")),
                aSearchTextField,
                aParent,
                aDescriptionRadioButton,
                aTagsRadioButton,
                aAlbumComboBox,
                aTableModel,
                aAlbumList)
        );
    }
}