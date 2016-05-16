package ui.button.regular;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import list.Album;
import listener.action.SearchAlbumListener;
import model.table.MediaTableModel;
import ui.button.radio.SearchDescriptionRadioButton;
import ui.button.radio.SearchTagsRadioButton;
import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.textfield.SearchAlbumTextField;

public final class SearchAlbumButton extends JButton {
    private static final long serialVersionUID = 1L;

    public SearchAlbumButton(final ImageIcon aIcon,
                            final SearchAlbumTextField aSearchTextField,
                            final OrganizerMainFrame aParent,
                            final SearchDescriptionRadioButton aDescriptionRadioButton,
                            final SearchTagsRadioButton aTagsRadioButton,
                            final AlbumComboBox aAlbumComboBox,
                            final MediaTableModel aTableModel,
                            final ArrayList<Album> aAlbumList) {
        super(aIcon);
        addActionListener(new SearchAlbumListener(aSearchTextField, 
                                                  aParent, 
                                                  aDescriptionRadioButton, 
                                                  aTagsRadioButton, 
                                                  aAlbumComboBox, 
                                                  aTableModel,
                                                  aAlbumList));
        setToolTipText("Search Current Album");
        setPreferredSize(new Dimension(24, 22));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setEnabled(true);
    }
}