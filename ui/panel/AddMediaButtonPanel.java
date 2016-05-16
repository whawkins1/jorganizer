package ui.panel;

import java.awt.Dimension;
import java.awt.Window;
import java.io.File;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import list.Album;

import model.table.MediaTableModel;

import ui.button.regular.AddCloseButton;
import ui.button.regular.AddResetButton;
import ui.button.regular.AddTagButton;
import ui.button.regular.ChooseFileButton;
import ui.button.regular.ResetWindowButton;
import ui.combobox.AlbumComboBox;
import ui.dialog.AddMediaDialog;
import ui.frame.OrganizerMainFrame;
import ui.menubar.MenuBar;
import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;

public final class AddMediaButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    public AddMediaButtonPanel(final JTextField aFileNameTextField,
                               final JTextField aDescriptionTextField,
                               final JTextField aTagsTextField,
                               final MonthSpinner aMonthSpinner,
                               final DaySpinner aDaySpinner,
                               final YearSpinner aYearSpinner,
                               final MenuBar aMenuBar,
                               final File aMediaFile,
                               final AlbumComboBox aAlbumComboBox,
                               final List<Album> aAlbumList,
                               final Window aParent,
                               final MediaTableModel aTableModel,
                               final AddMediaDialog aAddMediaDialog,
                               final OrganizerMainFrame aMainFrame) {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 6)));
        final ChooseFileButton fileButton = new ChooseFileButton(aFileNameTextField,
                                                                aMediaFile,
                                                                aDescriptionTextField,
                                                                aTagsTextField,
                                                                aMonthSpinner,
                                                                aDaySpinner,
                                                                aYearSpinner,
                                                                aAlbumComboBox,
                                                                aAlbumList,
                                                                aParent,
                                                                aTableModel,
                                                                aAddMediaDialog,
                                                                aMainFrame);
        add(fileButton);
        add(Box.createRigidArea(new Dimension(0, 6)));
        add(new AddResetButton());
        add(Box.createRigidArea(new Dimension(0, 6)));
        
        final AddCloseButton addCloseButton = new AddCloseButton(aFileNameTextField,
                                                                  aMediaFile,
                                                                  aDescriptionTextField,
                                                                  aTagsTextField,
                                                                  aMonthSpinner,
                                                                  aDaySpinner,
                                                                  aYearSpinner,
                                                                  aAlbumComboBox,
                                                                  aAlbumList,
                                                                  aParent,
                                                                  aTableModel,
                                                                  aAddMediaDialog,
                                                                  aMainFrame);   
        add(addCloseButton);
        add(Box.createRigidArea(new Dimension(0, 6)));
        add(new AddTagButton(aAddMediaDialog, aTagsTextField));
        
        add(Box.createRigidArea(new Dimension(0, 6)));
        add(new ResetWindowButton());
    }
}