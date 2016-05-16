package utility;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import filter.file.AlbumFilter;
import ui.button.radio.SearchDescriptionRadioButton;
import ui.button.radio.SearchTagsRadioButton;
import ui.combobox.AlbumComboBox;
import ui.frame.OrganizerMainFrame;
import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;
import ui.textfield.SearchAlbumTextField;
import list.Album;
import mediadata.MediaData;
import model.table.MediaTableModel;

public final class Utility {
        private static boolean fIsSaved;
        private static File fCurrentFile;
        
        public Utility() {
            fIsSaved = true;
            fCurrentFile = null;
        }
    
    
        public static final JMenuItem makeMenuItem(final String aTitle, 
                                                   final String aActionCommand, 
                                                   final int aKe, 
                                                   final int aIe, 
                                                   final JMenu aParentMenu, 
                                                   final Boolean aCheckBoxMenu)      {
            if(aCheckBoxMenu)           {
                final JCheckBoxMenuItem mc = new JCheckBoxMenuItem(aTitle);
                
                mc.addActionListener(this);
                mc.setActionCommand(aActionCommand);
                if(aKe != 0)                {
                   mc.setAccelerator(KeyStroke.getKeyStroke(aKe, aIe));
                   mc.setMnemonic(aKe);                
                }
                aParentMenu.add(mc);
                
                return mc;          
            }  else  {  
                final JMenuItem m = new JMenuItem(aTitle);
                 
                m.addActionListener(this);
                m.setActionCommand(aActionCommand);
            
                    if(aKe != 0)        {
                        m.setAccelerator(KeyStroke.getKeyStroke(aKe, aIe));
                        m.setMnemonic(aKe);
                    }
                    aParentMenu.add(m);
                
                return m;           
           }            
        }
        
        
        public static final void saveAsAlbum(final OrganizerMainFrame aMainFrame, final List<Album> aAlbumList) {
            final JFileChooser saveAlbumChooser = new JFileChooser();
            saveAlbumChooser.setCurrentDirectory(new File("C:/Users/Bill/Desktop"));
            saveAlbumChooser.setFileFilter(new AlbumFilter());
         
            final int resultChooser = saveAlbumChooser.showSaveDialog(aMainFrame);
         
            final File checkFile = new File(saveAlbumChooser.getSelectedFile() + ".dat");
         
                 try  {
                      if (checkFile.exists())  {
                         final int chosenOption = showConfirmDialog(aMainFrame, "Replace Existing File?");
                      
                          switch(chosenOption)   {
                             case JOptionPane.YES_OPTION:
                                 saveAlbumChooser.approveSelection();
                                 saveFile(aMainFrame, checkFile, aAlbumList);                            
                             case JOptionPane.NO_OPTION:
                                 return;
                           } 
                      } else if (resultChooser == JFileChooser.APPROVE_OPTION)   {
                          saveAlbumChooser.approveSelection();
                          saveFile(aMainFrame, checkFile, aAlbumList);                   
                      }
               } catch (final SecurityException exception)  {
                     showInfoMessage(aMainFrame, "File Read Access Denied");
                     exception.printStackTrace();
              }
        }
        
        
        public static final boolean albumIsSaved() { return fIsSaved; }
        
        
        public static final int showConfirmDialog(final OrganizerMainFrame aMainFrame, final String aMessage) {
            final int result = JOptionPane.showConfirmDialog(aMainFrame, 
                                                             aMessage, 
                                                             "Confirm", 
                                                             JOptionPane.YES_NO_CANCEL_OPTION);
            return result;
        }
        
        
        public static final void saveAlbum(final OrganizerMainFrame aMainFrame,
                                           final List<Album> aAlbumList) {
            try {
                if (fCurrentFile == null)   {
                    saveAsAlbum(aMainFrame, aAlbumList);
                    return;
                }   
                    saveFile(aMainFrame, fCurrentFile, aAlbumList);                         
            } catch (final SecurityException exception) {
                Utility.showErrorMessage(aMainFrame, "File Read Access Denied");
                exception.printStackTrace();
            }               
        }
        
        
        private static final void saveFile(final OrganizerMainFrame aMainFrame, 
                                           final File aFileToSave, 
                                           final List<Album> aAlbumList)   {
            
            try(final DataOutputStream dosOverwriteFile = new DataOutputStream(new FileOutputStream(aFileToSave, false)))  {
                dosOverwriteFile.writeInt(aAlbumList.size());
                
                for(final Album a : aAlbumList) {
                    dosOverwriteFile.writeUTF(a.getTitle());
                    a.store(dosOverwriteFile); 
                }               
                dosOverwriteFile.flush();
                fCurrentFile = aFileToSave;
                fIsSaved = true;
            } catch(final FileNotFoundException exception1)  {
                Utility.showInfoMessage(aMainFrame, "File Not Found");
                  exception1.printStackTrace();
            }  catch(final IOException exception2)   {
                Utility.showInfoMessage(aMainFrame, "Error Writing File");
                exception2.printStackTrace();
            }
        }       
        
        public static final void showMessage(final String aMessage, 
                                             final Component aParent,
                                             final String aTitle,
                                             final int aMessageType) {
            JOptionPane.showMessageDialog(aParent, 
                                          aMessage, 
                                          aTitle, 
                                          aMessageType);
            }
        
        public static final void searchAlbum(final SearchAlbumTextField aSearchTextField,
                                             final OrganizerMainFrame aParent,
                                             final SearchDescriptionRadioButton aDescriptionRadioButton,
                                             final SearchTagsRadioButton aTagsRadioButton,
                                             final AlbumComboBox aAlbumComboBox,
                                             final MediaTableModel aTableModel,
                                             final ArrayList<Album> aAlbumList) {
            
            final String userSearchText = aSearchTextField.getText();
            final boolean noSearchTermEntered = ((userSearchText.trim().isEmpty())  ||  
                                                ((userSearchText.trim().equals("Search"))));
            if (noSearchTermEntered) {
                Utility.showMessage("No Search Text Entered", 
                                    aParent, 
                                    "Search Error", 
                                    JOptionPane.ERROR_MESSAGE);
                return;
            } 
                final List<String> tempUserListSearchWords = new ArrayList<String>();
                final StringTokenizer parseUserText = new StringTokenizer(userSearchText);
    
                while( parseUserText.hasMoreTokens() )   
                    tempUserListSearchWords.add(parseUserText.nextToken());
                
             
                final String selectedAlbumTitle = String.valueOf(aAlbumComboBox.getSelectedItem());
                Album selectedAlbum = null;
                
                for(int i = 0; i < aAlbumList.size(); i++) {
                    final Album album = aAlbumList.get(i);
                    final String title = album.getTitle();
                    final boolean isaMatch = title.equals(selectedAlbumTitle);
                    if (isaMatch) {
                        selectedAlbum = album;
                        break;
                    }
                }
                
                
                if (selectedAlbum != null) {
                    if(aTagsRadioButton.isSelected())   {   
                        for(final MediaData m : selectedAlbum)    {   
                            m.setRelevancy(calcRelevancy(tempUserListSearchWords, m.getTags()));
                            m.setSortMode("Sort By Relevancy");
                        }
                    } else if (aDescriptionRadioButton.isSelected())     {
                        for(final MediaData m : selectedAlbum)    {   
                            final List<String> holdImageDescr = new ArrayList<String>();
                            final StringTokenizer parseImageDescr = new StringTokenizer(m.getDescription().trim()); 
                 
                            while(parseImageDescr.hasMoreTokens())   
                                holdImageDescr.add(parseImageDescr.nextToken());
                     
                            m.setRelevancy(calcRelevancy(tempUserListSearchWords, holdImageDescr));
                            m.setSortMode("Sort By Relevancy");
                        }
                    }  else    {
                        Utility.showMessage("Search Mode Not Selected(tags or Description)", 
                                            aParent, 
                                            "Search Error", 
                                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Collections.sort(selectedAlbum);
                    aTableModel.setDataList(selectedAlbum);
                    aTableModel.fireTableDataChanged();
                    return;
                } 
                Utility.showMessage("No Album Selected, Please Try Again", 
                                    aParent, 
                                    "Search Error", 
                                    JOptionPane.ERROR_MESSAGE);
        }
        
        
        private final static int calcRelevancy(final List<String> aSearchWordsToCheck, 
                final List<String> aMediaTextCheck) {
                int relevancyNumber = 0;
                
                for(int i = 0; i < aSearchWordsToCheck.size(); i++)  {
                        for(int j = 0; j < aMediaTextCheck.size(); j++)  {
                             final String searchWord = aSearchWordsToCheck.get(i);
                             final String mediaWord = aMediaTextCheck.get(j);
                
                         final boolean isaMatch = (searchWord.equalsIgnoreCase(mediaWord)); 
                    if (isaMatch) 
                        relevancyNumber++;
                    }      
                }
                return relevancyNumber; 
              }
        
        public final static JLabel makeLabel(String name)        {
            final JLabel l = new JLabel(name, SwingConstants.RIGHT);
            l.setFont(new Font("Serif", Font.BOLD,14));
            return l;
        }
        
        
        public final static JTextField makeTextField(int textFieldSize)        {
            final JTextField t = new JTextField(textFieldSize);
            t.setEnabled(false);
            t.setEditable(false);
            return t;
        }    
        
        private final static String getExtension(final String aFileName) {
            String extension = "";
            int indexPoint = aFileName.indexOf('.');
            
            if (indexPoint > 0) 
                extension = aFileName.substring(++indexPoint);
            
            return extension;
        }
        
        
        public final static File fileNameReplace(final JTextField aMediaTextField, 
                                                     final File aMediaFile,
                                                     final Window aParent,
                                                     final AlbumComboBox aAlbumComboBox,
                                                     final List<Album> aAlbumList)     {
            File newMediaFile = null;
            final String fileName = aMediaFile.getName();
            final String extension = Utility.getExtension(fileName);
            
            
            final String mediaFileNameInput = (aMediaTextField.getText().trim());
            final String mediaFileNameConc = (mediaFileNameInput.concat("." + extension));
            
            
            final boolean fileNamesEqual =  mediaFileNameConc.equals(fileName);
            if (fileNamesEqual)   {
                return aMediaFile;
            }
                final int reply = JOptionPane.showConfirmDialog(aParent, 
                                                                "Replace " + fileName + " With " + mediaFileNameConc, 
                                                                "Replace File?", 
                                                                JOptionPane.YES_NO_CANCEL_OPTION);
                
                if(reply == JOptionPane.YES_OPTION )  {
                    final String absoluteFilePath = aMediaFile.getAbsolutePath();
                    final String filePath = absoluteFilePath.substring(0, absoluteFilePath.lastIndexOf(File.separator));
                    final String filePathNameConc = ("\\" + mediaFileNameConc);
                    
                    final String albumTitle = (aAlbumComboBox.getSelectedItem().toString());
                    final Album currentAlbum = getCurrentAlbum(albumTitle, aAlbumList);
                    
                    if(noDuplicateMedia(fileName, currentAlbum))    {
                        final boolean renameSuccess = (aMediaFile.renameTo(new File(filePath))); 
                        if (renameSuccess)   { 
                            Utility.showInfoMessage(aParent, (fileName + " Has Been Renamed To " + filePathNameConc));
                            newMediaFile = new File(filePathNameConc);
                             
                            return newMediaFile;
                        }   
                            Utility.showInfoMessage(aParent, "The File " + fileName + "Could Not Be Renamed");
                            return newMediaFile;                                                       
                    }  
                        Utility.showErrorMessage(aParent, "The File Name " + mediaFileNameConc + " Is Already In Use, "+ "Please Rename Again");
                }   
                return newMediaFile;
             }
        


        public final static void showInfoMessage(final Window aParent, final String aMessage) {
            JOptionPane.showMessageDialog(aParent, 
                                          aMessage,
                                          "Message",
                                          JOptionPane.INFORMATION_MESSAGE);
        }

        
        public final static void showErrorMessage(final Window aParent, 
                                                  final String aMessage) {
            JOptionPane.showMessageDialog(aParent, 
                                          aMessage, 
                                          "Error!", 
                                          JOptionPane.ERROR_MESSAGE);
        }
        
        
        public final static void createMediaData(final File aMediaFile,
                                                  final JTextField aDescriptionTextField,
                                                  final JTextField aTagsTextField,
                                                  final MonthSpinner aMonthSpinner,
                                                  final DaySpinner aDaySpinner,
                                                  final YearSpinner aYearSpinner,
                                                  final AlbumComboBox aAlbumComboBox,
                                                  final List<Album> aAlbumList,
                                                  final Window aParent,
                                                  final MediaTableModel aTableModel)  {
            final MediaData mediaData = new MediaData();
            final String fileName = aMediaFile.getName();
            mediaData.setFileName(fileName);
            
            final String extension = Utility.getExtension(fileName);
            
            mediaData.setmediaType(extension.toUpperCase());  
            mediaData.setMediaFile(aMediaFile);
            mediaData.setFilePath(aMediaFile.getAbsolutePath().trim());
            mediaData.setDate(new SimpleDateFormat("MM").format( (Date)aMonthSpinner.getValue() ).toString() + "/" + 
                                   new SimpleDateFormat("dd").format( (Date)aDaySpinner.getValue() ).toString() + "/" + 
                                   new SimpleDateFormat("yyyy").format((Date)aYearSpinner.getValue()).toString() );
            
            final String mediaDescription = aDescriptionTextField.getText().trim();
                String descriptionInput = "N/A";
                final boolean isDesciptionInput = !((mediaDescription.isEmpty() || (mediaDescription.equals("Enter Description")))); 
                if(isDesciptionInput)    
                     mediaData.setDescription(descriptionInput);
                
                final String tagsInput = (aTagsTextField.getText().trim());
                List<String> aTagList = new ArrayList<String>();
                if(!tagsInput.isEmpty()) {
                    final String[] tagArraySplit = tagsInput.split(","); 
                    aTagList = Arrays.asList(tagArraySplit);
                    mediaData.addTags( aTagList );
                }  
                   
                final String albumTitle = (aAlbumComboBox.getSelectedItem().toString());
                
                if(albumTitle.isEmpty()) {
                    Utility.showErrorMessage(aParent, "No Album Selected!");
                    return;
                }
                
                final Album currentAlbum = getCurrentAlbum(albumTitle, aAlbumList);
                 
                if (currentAlbum == null) {
                    Utility.showErrorMessage(aParent, "No Album Found, Please Try Again.");
                    return;
                }
                
                currentAlbum.addMedia(mediaData);
                aTableModel.addMedia(mediaData);
        }
        
        
        private final static Album getCurrentAlbum(final String aTitle, final List<Album> aAlbumList) {
            Album currentAlbum = null; 
            for(Album album : aAlbumList) {
                final boolean isAlbum = album.getTitle().equals(aTitle);
                if (isAlbum) {
                    currentAlbum = album;
                    break;
                }
            }
            return currentAlbum;
        }
        
        
        private final static Boolean noDuplicateMedia(final String aFileNameCheck, final Album aAlbum)      {
            boolean noDuplicateFound = true; 
            
            for(int i = 0; i < aAlbum.size(); i++)    {
                final String fileNameStored = aAlbum.getMedia(i).getFilePath();
                final boolean isDuplicateFileName = aFileNameCheck.equals(fileNameStored);
                if(isDuplicateFileName)    {
                    noDuplicateFound = false;
                    break;
                }
            }
            return noDuplicateFound;
        }  
        
}