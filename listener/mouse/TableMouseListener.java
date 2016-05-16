package listener.mouse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import ui.dialog.AddMediaDialog;
import ui.dialog.EditMediaDialog;
import ui.dialog.SlideShowDialog;
import window.DisplayImageWindow;
import jorganizerpackage.JOrganizerMain;
import list.Album;
import mediadata.MediaData;
import model.table.MediaTableModel;

public final class TableMouseListener    
                        implements MouseListener, ActionListener{
    final private JFrame fParent;
    private Album fCurrentAlbumOfMedia;
    final private JPopupMenu fPopupMenu;
    final private MediaTableModel fMtm;
    final private JTable fTable;
    @SuppressWarnings("unused")
    final private JMenuItem fAddMedia;
    final private JMenuItem fRemoveMedia;
    final private JMenuItem fEditMedia;
    final private JMenuItem fSlideShowMenu;
    private Boolean fEditRemoveSlideShowModeDisabled;
    private AddMediaDialog fAmd;
    int fNumRowsSelected;
    private String fVlcLocation;
    final private boolean fAudioVideoEnabled;
        
    public TableMouseListener(JFrame aParent, MediaTableModel aMtm, JTable aTable, boolean aAudioVideoEnabled, String afVlcLocation)   {
        this.fParent = aParent; 
        this.fMtm = aMtm;
        this.fTable = aTable;
        this.fAudioVideoEnabled = aAudioVideoEnabled;       
        this.fVlcLocation = afVlcLocation;
        
        fEditRemoveSlideShowModeDisabled = true;
        fPopupMenu = new JPopupMenu();
        fAddMedia =  createMenuItem("Add", "ADD", true);
        fRemoveMedia = createMenuItem("Remove", "REMOVE", false);
        fEditMedia = createMenuItem("Edit", "EDIT", false);
        fSlideShowMenu = createMenuItem("Slideshow", "SLIDESHOW", false);
    }
    
        public final void setMediaDialog(AddMediaDialog aAmd)  {
            this.fAmd = aAmd;
        }
        
        @Override
        public final void mouseClicked(MouseEvent e)    {
            final JTable source = (JTable) e.getSource();
            final int row = source.rowAtPoint(e.getPoint());        
                      
            if(row == -1) {
               fTable.clearSelection();
            } else  if( (fTable.isEnabled()) && (e.getClickCount() == 2) && (!(fCurrentAlbumOfMedia.isEmpty())) )   {
                  final MediaData viewMedia = fCurrentAlbumOfMedia.getMedia(row);
                  final String mediaType = viewMedia.getMediaType();
                  final String mediaPath = viewMedia.getFilePath();     
                  
                  if(mediaType.matches("JPG|GIF|PNG|BMP|WBMP"))   {
                      new DisplayImageWindow(fParent, viewMedia.getMediaFile(), mediaType);
                  } else  {
                      if(fAudioVideoEnabled) {
                          final ProcessBuilder pb = new ProcessBuilder(fVlcLocation, mediaPath);
                             try  {
                                pb.start();
                             }  catch (IOException e1)  {
                                e1.printStackTrace();
                             }    
                      } else {
                          JOptionPane.showMessageDialog(fParent, "VLC Media Player must be Installed to play " + mediaType + " File.", "Error", JOptionPane.ERROR_MESSAGE);
                      }              
                  }
             } 
        }
        
        @Override
        public final void mouseReleased(MouseEvent e)    {
            if( (e.isPopupTrigger()) && (fTable.isEnabled()))       {
                final JTable source = (JTable) e.getSource();
                final int row = source.rowAtPoint(e.getPoint());
                fNumRowsSelected =  fTable.getSelectedRowCount();
                            
                if ( row == -1 ) {
                    if(!(fEditRemoveSlideShowModeDisabled))   {
                        setEditRemoveMode(false, true);
                    }
                    fSlideShowMenu.setEnabled(false);
                    fPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                } else  {
                    if(fNumRowsSelected > 1) {
                        fSlideShowMenu.setEnabled(true);
                        setEditRemoveMode(false, true);
                    } else if(fEditRemoveSlideShowModeDisabled) {
                        setEditRemoveMode(true, false);
                    }                                  
                    fPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
        
        @Override
        public final void actionPerformed(ActionEvent e) {
              if(e.getActionCommand().equals("ADD"))  {
                fAmd.setCurrentAlbum(fCurrentAlbumOfMedia);
                fAmd.setVisible(true);
                
                   if(fEditRemoveSlideShowModeDisabled) {
                       setEditRemoveMode(true, false);
                   }
              } else if(e.getActionCommand().equals("REMOVE"))  {
                    int removeSelectedRowCount = fTable.getSelectedRowCount();
                    
                    if(removeSelectedRowCount != 0)         {
                        if(removeSelectedRowCount == fCurrentAlbumOfMedia.size()){
                            fMtm.clearTable();
                            fCurrentAlbumOfMedia.clear();
                        } else if(removeSelectedRowCount > 1)  {
                            int[] removeSelectedRows = fTable.getSelectedRows();
                            fMtm.removeMedia(removeSelectedRows);
                            fCurrentAlbumOfMedia.deleteMedia(removeSelectedRows);
                        }  else {
                          int selectedRowRemove = fTable.getSelectedRow(); 
                          fMtm.removeMedia(selectedRowRemove);
                          fCurrentAlbumOfMedia.deleteMedia(selectedRowRemove);
                        }
                        JOrganizerMain.checkMenuAccess();
                    } else  {
                        JOptionPane.showMessageDialog(fParent, "Must Select Media in fTable To Remove", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                        if(fCurrentAlbumOfMedia.isEmpty()) {
                            setEditRemoveMode(false, true);
                        } 
                    
              } else if(e.getActionCommand().equals("EDIT"))  {
                    int selectedEditNumber = fTable.getSelectedRow();
                    int selectedEditRowCount = fTable.getSelectedRowCount();
                    
                    if( (selectedEditNumber != -1) && (selectedEditRowCount == 1))  {
                        MediaData editData = (MediaData)fCurrentAlbumOfMedia.get(selectedEditNumber);
                        new EditMediaDialog(fParent, fCurrentAlbumOfMedia, selectedEditNumber, editData, true, fMtm);   
                    }   else    {
                            if(selectedEditNumber == -1)    {   
                                JOptionPane.showMessageDialog(fParent, "Select Media File in fTable To Edit", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(fParent, "Select Only One Media File in fTable To Edit", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                    }
             } else if(e.getActionCommand().equals("SLIDESHOW"))  {
                    int[] rowsSelected = new int[fNumRowsSelected];
                    rowsSelected = fTable.getSelectedRows();
                    MediaData imageSlideShow;
                    final List<File> imageFileNameList = new ArrayList<File>();
                    
                    if(checkValidImageFile(rowsSelected, fNumRowsSelected))  {              
                        try {
                            for(int i = 0; i < fNumRowsSelected; i++) {
                                imageSlideShow = fCurrentAlbumOfMedia.getMedia(fTable.getRowSorter().convertRowIndexToModel(rowsSelected[i]));
                                imageFileNameList.add(imageSlideShow.getMediaFile());
                            }   
                        } catch (IndexOutOfBoundsException iobe) {
                            iobe.printStackTrace();
                        }
                        new SlideShowDialog(fParent, true, imageFileNameList);     
                    } else {
                        JOptionPane.showMessageDialog(fParent, "Select Only Compatible Image Files For Slideshow.", "SlideShow Error", JOptionPane.ERROR_MESSAGE);
                    }
             }
     }
        private final boolean checkValidImageFile(int[] aRowsSelectedValidFile, int aNumRowsSelectedValid)   {
            MediaData checkValidFile;
            final List<String> validFileExt = Arrays.asList("JPG", "GIF", "PNG", "BMP", "WBMP");
            
            boolean isValidFile = true;
            for(int v = 0; (v < aNumRowsSelectedValid) && (isValidFile); v++)  {
                checkValidFile = fCurrentAlbumOfMedia.getMedia(fTable.getRowSorter().convertRowIndexToModel(aRowsSelectedValidFile[v]));
                if(!(validFileExt.contains(checkValidFile.getMediaType())))    {
                    isValidFile = false;
                }
            }
            return isValidFile;
        }    
        
        private final void setEditRemoveMode(Boolean aEnableMenu, Boolean aModeSwitch)    {
            fRemoveMedia.setEnabled(aEnableMenu);
            fEditMedia.setEnabled(aEnableMenu);
            fEditRemoveSlideShowModeDisabled = aModeSwitch;             
        }
        
        public final void setCurrentAlbum(Album aCurrentAlbum)      {
            this.fCurrentAlbumOfMedia = aCurrentAlbum;
        }
        
        private final JMenuItem createMenuItem(String aName, String aActionCommand, Boolean aEnabled)   {
            JMenuItem mi = new JMenuItem(aName);
            mi.addActionListener(this);
            mi.setActionCommand(aActionCommand);
            mi.setEnabled(aEnabled);
            fPopupMenu.add(mi);
            
            return mi;      
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
        }
}
