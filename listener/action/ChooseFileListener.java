package listener.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import list.Album;

import mediadata.MediaData;

import ui.button.regular.AddCloseButton;
import ui.button.regular.AddResetButton;
import ui.button.regular.AddTagButton;
import ui.button.regular.ChooseFileButton;
import ui.button.regular.ResetWindowButton;
import ui.combobox.AlbumComboBox;
import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;

import utility.Utility;

import filter.file.AudioFilter;
import filter.file.ImageFilter;
import filter.file.VideoFilter;


public final class ChooseFileListener
                        implements ActionListener {
    private final Window fParent;
    private final JTextField fFileNameTextField;
    private final JTextField fDescriptionTextField;
    private final ChooseFileButton fChooseFileButton;
    private final AddResetButton fAddResetButton;
    private final AddCloseButton fAddCloseButton;
    private final AddTagButton fAddTagButton;
    private final ResetWindowButton fResetWindowButton;
    private final MonthSpinner fMonthSpinner;
    private final DaySpinner fDaySpinner;
    private final YearSpinner fYearSpinner;
    private final List<Album> fAlbumList;
    private final AlbumComboBox fAlbumComboBox;
    
    public ChooseFileListener(final Window aParent, 
                              final JTextField aFileNameTextField,
                              final JTextField aDescriptionTextField,
                              final ChooseFileButton aChooseFileButton,
                              final AddResetButton aAddResetButton,
                              final AddCloseButton aAddCloseButton,
                              final AddTagButton aAddTagButton,
                              final ResetWindowButton aResetWindowButton,
                              final MonthSpinner aMonthSpinner,
                              final DaySpinner aDaySpinner,
                              final YearSpinner aYearSpinner,
                              final List<Album> aAlbumList,
                              final AlbumComboBox aAlbumComboBox) {
        this.fParent = aParent;
        this.fFileNameTextField = aFileNameTextField;
        this.fDescriptionTextField = aDescriptionTextField;
        this.fChooseFileButton = aChooseFileButton;
        this.fAddResetButton = aAddResetButton;
        this.fAddCloseButton = aAddCloseButton;
        this.fAddTagButton = aAddTagButton;
        this.fResetWindowButton = aResetWindowButton;
        this.fMonthSpinner = aMonthSpinner;
        this.fDaySpinner = aDaySpinner;
        this.fYearSpinner = aYearSpinner;
        this.fAlbumList = aAlbumList;
        this.fAlbumComboBox = aAlbumComboBox;
    }
    
        @Override public final void actionPerformed(final ActionEvent event) {
            final JFileChooser mediaChooser = new JFileChooser(new File("C:\\Users\\Bill\\Desktop"));
            mediaChooser.setFileFilter(new ImageFilter());
            mediaChooser.addChoosableFileFilter(new AudioFilter());
            mediaChooser.addChoosableFileFilter(new VideoFilter());
            mediaChooser.setAcceptAllFileFilterUsed(false);
            mediaChooser.setMultiSelectionEnabled(false);
                                
            final int option = mediaChooser.showOpenDialog(fParent);
            String fileNameWithExtension = "";
                
            if(option == JFileChooser.APPROVE_OPTION)   {
                final File selectedFile = mediaChooser.getSelectedFile();
                final String filePathOriginal = (selectedFile.getAbsolutePath().trim().toString());
                
                if(duplicateFilePathFound(filePathOriginal))    {
                    Utility.showErrorMessage(fParent, "The Media " + fileNameWithExtension + " Is Already In the Curent Album, Please Select Another Image");
                    return;
                }
                    final int indexPoint = filePathOriginal.lastIndexOf('.');
                    final int indexForwardSlash = filePathOriginal.lastIndexOf('/');
                    final int indexDoubleBackwardSlash = filePathOriginal.lastIndexOf('\\');
                    
                    final int maxIndexOfSlashes = Math.max(indexForwardSlash, indexDoubleBackwardSlash);
                        String fileExtension = "";
                        if (indexPoint > maxIndexOfSlashes)  
                            fileExtension = filePathOriginal.substring(indexPoint + 1);
                        
                        fileNameWithExtension = selectedFile.getName().trim();
                        final int indexOfFileName = filePathOriginal.lastIndexOf(fileNameWithExtension);
                        final String fileNameWithoutExtension = fileNameWithExtension.substring(indexOfFileName);
                        fFileNameTextField.setText(fileNameWithoutExtension);
                        
                        fFileNameTextField.setEditable(true);
                        fFileNameTextField.setEnabled(true);
                                                                                
                        fDescriptionTextField.setText("Enter Description");
                        fDescriptionTextField.setEditable(true);
                        fDescriptionTextField.setEnabled(true);
                                                                    
                        fChooseFileButton.setEnabled(false);
                        fAddResetButton.setEnabled(true);
                        fAddCloseButton.setEnabled(true);
                        fAddTagButton.setEnabled(true);
                        fResetWindowButton.setEnabled(true);
                        fMonthSpinner.setEnabled(true);
                        fDaySpinner.setEnabled(true);
                        fYearSpinner.setEnabled(true);
               } 
          }
        
    
        
        private final boolean duplicateFilePathFound(final String aFilePath)      {
            final DefaultComboBoxModel<String> comboBoxModel = (DefaultComboBoxModel<String>)fAlbumComboBox.getModel();
            final String currentAlbumTitle = (comboBoxModel.getSelectedItem().toString());
            Album currentAlbum = null;
            for(Album a : fAlbumList) {
                final String title = a.getTitle();
                final boolean isaMatch = currentAlbumTitle.equals(title);
                if(isaMatch) 
                    currentAlbum = a;
            }
            
            if (currentAlbum != null) {
                for (MediaData m : currentAlbum) {
                final String filePathCheck = m.getFilePath();
                final boolean fileExists = aFilePath.equals(filePathCheck);
                    if(fileExists) 
                        return true;
                }
            }    
            return false;
        }
}