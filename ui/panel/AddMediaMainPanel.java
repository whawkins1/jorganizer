package ui.panel;

import java.awt.BorderLayout;
import java.awt.Window;
import java.io.File;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import list.Album;
import listener.focus.MediaDescriptionTextFieldFocusListener;
import model.table.MediaTableModel;
import ui.combobox.AlbumComboBox;
import ui.dialog.AddMediaDialog;
import ui.frame.OrganizerMainFrame;
import ui.menubar.MenuBar;
import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;
import utility.Utility;

public final class AddMediaMainPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    public AddMediaMainPanel(final AddMediaDialog aDialog, 
                             final MenuBar aMenuBar,
                             final AlbumComboBox aAlbumComboBox,
                             final List<Album> aAlbumList,
                             final MediaTableModel aTableModel,
                             final OrganizerMainFrame aMainFrame) {
        
        super(new BorderLayout(2, 0));
        
        final JTextField fileNameTextField = Utility.makeTextField(34);
        final JTextField descriptionTextField = Utility.makeTextField(34);
        descriptionTextField.addFocusListener(new MediaDescriptionTextFieldFocusListener(descriptionTextField));
        final JTextField tagsTextField = Utility.makeTextField(30);
        
        final MonthSpinner monthSpinner = new MonthSpinner();
        final DaySpinner daySpinner = new DaySpinner();
        final YearSpinner yearSpinner = new YearSpinner();
        
        final AddMediaButtonPanel mediaButtonPanel = new AddMediaButtonPanel(fileNameTextField, 
                                                                             descriptionTextField, 
                                                                             tagsTextField, 
                                                                             monthSpinner, 
                                                                             daySpinner, 
                                                                             yearSpinner,
                                                                             aMenuBar, 
                                                                             final File aMediaFile,
                                                                             aAlbumComboBox,
                                                                             aAlbumList,
                                                                             aDialog,
                                                                             aTableModel,
                                                                             this,
                                                                             aMainFrame);
        add(mediaButtonPanel, BorderLayout.WEST);
        
        final AddMediaInfoPanel infoPanel = new AddMediaInfoPanel(fileNameTextField,
                                                                  descriptionTextField,
                                                                  tagsTextField,
                                                                  monthSpinner,
                                                                  daySpinner,
                                                                  yearSpinner);
        add(infoPanel, BorderLayout.EAST);
    }
}