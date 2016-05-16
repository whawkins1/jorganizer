package listener.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import list.Album;

import model.table.MediaTableModel;

import ui.button.radio.SearchDescriptionRadioButton;
import ui.button.radio.SearchTagsRadioButton;
import ui.frame.OrganizerMainFrame;
import ui.textfield.SearchAlbumTextField;

import utility.Utility;


public final class PressEnterSearchListener 
                                implements KeyListener {

    private final SearchAlbumTextField fSearchTextField;
    private final OrganizerMainFrame fParent;
    private final SearchDescriptionRadioButton fDescriptionRadioButton;
    private final SearchTagsRadioButton fTagsRadioButton;
    private final Album fCurrentAlbum;
    private final MediaTableModel fTableModel;
    
    public PressEnterSearchListener(final SearchAlbumTextField aSearchTextField,
                                    final OrganizerMainFrame aParent,
                                    final SearchDescriptionRadioButton aDescriptionRadioButton,
                                    final SearchTagsRadioButton aTagsRadioButton,
                                    final Album aCurrentAlbum,
                                    final MediaTableModel aTableModel) {

        this.fSearchTextField = aSearchTextField;
        this.fParent = aParent;
        this.fDescriptionRadioButton = aDescriptionRadioButton;
        this.fTagsRadioButton = aTagsRadioButton;
        this.fCurrentAlbum = aCurrentAlbum;
        this.fTableModel = aTableModel;
    }
    
        @Override public final void keyReleased(final KeyEvent event) { 
            final boolean enteredReleased = (event.getKeyCode() ==  10); 
            if(enteredReleased)  
                Utility.searchAlbum(fSearchTextField, 
                                    fParent, 
                                    fDescriptionRadioButton, 
                                    fTagsRadioButton, 
                                    fCurrentAlbum, 
                                    fTableModel);
        }
         
        @Override public final void keyPressed(final KeyEvent event) {}
    
        @Override public final void keyTyped(final KeyEvent event) {}

}