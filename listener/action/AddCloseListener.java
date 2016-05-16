package listener.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ui.combobox.AlbumComboBox;
import ui.dialog.AddMediaDialog;
import ui.frame.OrganizerMainFrame;
import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;
import utility.Utility;
import list.Album;
import mediadata.MediaData;
import model.table.MediaTableModel;

public final class AddCloseListener implements ActionListener {

    private final JTextField fFileNameTextField;
    private final File fMediaFile;
    private final Window fParent;
    private final JTextField fDescriptionTextField;
    private final JTextField fTagsTextField;
    private final MonthSpinner fMonthSpinner;
    private final DaySpinner fDaySpinner;
    private final YearSpinner fYearSpinner;
    private final AlbumComboBox fAlbumComboBox;
    private final List<Album> fAlbumList;
    private final MediaTableModel fTableModel;
    private final AddMediaDialog fAddMediaDialog;
    private final OrganizerMainFrame fMainFrame;
    
    
    public AddCloseListener(final JTextField aFileNameTextField,
                            final File aMediaFile,
                            final JTextField aDescriptionTextField,
                            final JTextField aTagsTextField,
                            final MonthSpinner aMonthSpinner,
                            final DaySpinner aDaySpinner,
                            final YearSpinner aYearSpinner,
                            final AlbumComboBox aAlbumComboBox,
                            final List<Album> aAlbumList,
                            final Window aParent,
                            final MediaTableModel aTableModel,
                            final AddMediaDialog aAddMediaDialog,
                            final OrganizerMainFrame aMainFrame) {
        
        this.fFileNameTextField = aFileNameTextField;
        this.fMediaFile = aMediaFile;
        this.fParent = aParent;
        this.fDescriptionTextField = aDescriptionTextField;
        this.fTagsTextField = aTagsTextField;
        this.fMonthSpinner = aMonthSpinner;
        this.fDaySpinner = aDaySpinner;
        this.fYearSpinner = aYearSpinner;
        this.fAlbumComboBox = aAlbumComboBox;
        this.fAlbumList = aAlbumList;
        this.fTableModel = aTableModel;
        this.fAddMediaDialog = aAddMediaDialog;
        this.fMainFrame = aMainFrame;
    }
    
    @Override public void actionPerformed(final ActionEvent event) {
        final File replaceFile = Utility.fileNameReplace(fFileNameTextField, 
                                                         fMediaFile, 
                                                         fParent, 
                                                         fAlbumComboBox,
                                                         fAlbumList); 
        if (replaceFile != null)   {            
            Utility.createMediaData(fMediaFile,
                            fDescriptionTextField,
                            fTagsTextField,
                            fMonthSpinner,
                            fDaySpinner,
                            fYearSpinner,
                            fAlbumComboBox,
                            fAlbumList,
                            fParent,
                            fTableModel);
            fMainFrame.checkMenuAccess();
            fAddMediaDialog.setVisible(false);
        } 
    }
    
    
    
    
    
   

}
