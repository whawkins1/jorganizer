package ui.panel;

import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.JPanel;

import list.Album;

import model.table.MediaTableModel;

import scrollpane.ScrollPane;

import table.MediaTable;

import ui.button.radio.SearchDescriptionRadioButton;
import ui.button.radio.SearchTagsRadioButton;
import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.textfield.SearchAlbumTextField;


public final class MainTablePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    
        public MainTablePanel(final MediaTableModel aTableModel,
                              final String aVlcLocation, 
                              final boolean aEnableAudioVideo,
                              final ArrayList<Album> aAlbumList,
                              final OrganizerMainFrame aParent) {
            super(new BorderLayout());
            
            final SearchAlbumTextField searchTextField = new SearchAlbumTextField();
            final SearchTagsRadioButton searchTagsRadioButton = new SearchTagsRadioButton();
            final SearchDescriptionRadioButton searchDescriptionRadioButton = new SearchDescriptionRadioButton();
            
            //Get Titles From Albums to Display ComboBox
            final int albumSize = aAlbumList.size();
            final String[] albumTitles = new String[aAlbumList.size()];
            for(int i = 0; i < albumSize; i++) {
                final Album a = aAlbumList.get(i);
                albumTitles[i] = a.getTitle();
            }
            final AlbumComboBox albumComboBox = new AlbumComboBox(aTableModel, aAlbumList, albumTitles);
            
            final SearchPanel searchPanel = new SearchPanel(searchTextField, 
                                                            aTableModel, 
                                                            aAlbumList, 
                                                            aParent,
                                                            searchTagsRadioButton,
                                                            searchDescriptionRadioButton,
                                                            albumComboBox
                                                            );
                            
            add(searchPanel, BorderLayout.NORTH);
            final MediaTable table = new MediaTable(aParent, 
                                                    aTableModel, 
                                                    aEnableAudioVideo, 
                                                    aVlcLocation, 
                                                    searchTextField); 
            add(new ScrollPane(table), BorderLayout.SOUTH);    
        }    
   }