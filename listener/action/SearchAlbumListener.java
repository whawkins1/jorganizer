package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ui.button.radio.SearchDescriptionRadioButton;
import ui.button.radio.SearchTagsRadioButton;
import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.textfield.SearchAlbumTextField;
import utility.Utility;
import list.Album;


import model.table.MediaTableModel;

public final class SearchAlbumListener 
                            implements ActionListener {
    
    private final SearchAlbumTextField fSearchTextField;
    private final OrganizerMainFrame fParent;
    private final SearchDescriptionRadioButton fDescriptionRadioButton;
    private final SearchTagsRadioButton fTagsRadioButton;
    private final AlbumComboBox fAlbumComboBox;
    private final MediaTableModel fTableModel;
    private final ArrayList<Album> fAlbumList;
    
    
    public SearchAlbumListener(final SearchAlbumTextField aSearchTextField,
                               final OrganizerMainFrame aParent,
                               final SearchDescriptionRadioButton aDescriptionRadioButton,
                               final SearchTagsRadioButton aTagsRadioButton,
                               final AlbumComboBox aAlbumComboBox,
                               final MediaTableModel aTableModel,
                               final ArrayList<Album> aAlbumList) {
        
        this.fSearchTextField = aSearchTextField;
        this.fParent = aParent;
        this.fDescriptionRadioButton = aDescriptionRadioButton;
        this.fTagsRadioButton = aTagsRadioButton;
        this.fAlbumComboBox = aAlbumComboBox;
        this.fTableModel = aTableModel;
        this.fAlbumList = aAlbumList;
    }
    
    
        @Override public final void actionPerformed(final ActionEvent event) {
           Utility.searchAlbum(fSearchTextField, 
                               fParent, 
                               fDescriptionRadioButton, 
                               fTagsRadioButton, 
                               fAlbumComboBox, 
                               fTableModel,
                               fAlbumList);
        }
}