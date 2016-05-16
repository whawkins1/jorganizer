package ui.button.regular;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import list.Album;
import listener.action.AddCloseListener;
import model.table.MediaTableModel;
import ui.combobox.AlbumComboBox;
import ui.dialog.AddMediaDialog;
import ui.frame.OrganizerMainFrame;
import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;

public final class AddCloseButton extends JButton {
    private static final long serialVersionUID = 1L;
    
    public AddCloseButton(final JTextField aFileNameTextField,
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
        
        super("Add/Close");
        final AddCloseListener listener = new AddCloseListener(aFileNameTextField,
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
        addActionListener(listener);
        setPreferredSize(new Dimension(116, 30));
        setMinimumSize(new Dimension(116, 30));
        setMaximumSize(new Dimension(116, 30));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText("Confirms Media To be added To Current Album, Then Closes Window");
    }
}
