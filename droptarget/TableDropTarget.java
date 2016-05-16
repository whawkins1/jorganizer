package droptarget;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import mediadata.MediaData;

public final class TableDropTarget extends DropTarget {
    private static final long serialVersionUID = 1L;
    
    public TableDropTarget() {
        
    }
    
    public synchronized void drop(DropTargetDropEvent dtde)   {
        if((fTable.isEnabled()) && (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)))  {
            dtde.acceptDrop(DnDConstants.ACTION_COPY);
            final Transferable tran =  dtde.getTransferable();
            List<Object> fileList = null;
            Object value;
            File dropFile;
            MediaData dropFileData;
            SimpleDateFormat formatDropFileDate;
            String dropFileExt;
            String dropFilePath;
            String absoluteFilePath;
            String dropFileName;
            
            try {
                fileList = (List<Object>)tran.getTransferData(DataFlavor.javaFileListFlavor);
                int numFilesDropped = fileList.size();
                if(numFilesDropped == 1)  {
                    value = fileList.get(0);
                        
                    if( value instanceof File)   {
                        dropFile = (File) value;
                        absoluteFilePath = dropFile.getAbsolutePath().trim();
                        dropFileName = dropFile.getName();
                        dropFilePath = absoluteFilePath.substring(0, absoluteFilePath.lastIndexOf(File.separator)) + "\\" + dropFileName;
                                                            
                        if(noDuplicateMedia(dropFilePath))   {
                            fAmd.setCurrentAlbum(fCurrentAlbum);
                            fAmd.setMediaFile(dropFile);
                            fAmd.addFileInfo();
                            fAmd.setVisible(true);
                            fSaved = false;
                        } else {
                            JOptionPane.showMessageDialog(null, "The File " + dropFile.getName() + " Is already Being Used.", "Duplicate File", JOptionPane.ERROR_MESSAGE);
                        }
                    }                               
                } else {
                    formatDropFileDate = new SimpleDateFormat("MM/dd/yyyy");
                    for(int f = 0; f < numFilesDropped; f++)   {
                        value = fileList.get(f);
                        if( value instanceof File)  {
                            dropFile = (File) value;
                            absoluteFilePath = dropFile.getAbsolutePath().trim();
                            dropFileExt = parseDropFile(absoluteFilePath);
                            dropFileName = dropFile.getName();
                            dropFilePath = absoluteFilePath.substring(0, absoluteFilePath.lastIndexOf(File.separator)) + "\\" + dropFileName;
                            
                            if(noDuplicateMedia(dropFilePath)) {
                                dropFileData = new MediaData();
                                dropFileData.setFileName(dropFile.getName());
                                dropFileData.setmediaType(dropFileExt.toUpperCase());
                                dropFileData.setFilePath(dropFile.getAbsolutePath().trim());
                                Date dropFileDate = new Date();
                                dropFileData.setDate(formatDropFileDate.format(dropFileDate));
                                dropFileData.setDescription("N/A");
                                dropFileData.addTags(new ArrayList<String>());
                                fCurrentAlbum.addMedia(dropFileData);
                                fTableModel.addMedia(dropFileData);
                                fSaved = false;
                            } else {
                                JOptionPane.showMessageDialog(null, "The File " + dropFile.getName() + " Is already Being Used.", "Duplicate File", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }                               
                }
            } catch (UnsupportedFlavorException ufe)  {
                ufe.printStackTrace();
            } catch (IOException ioe)  {
                ioe.printStackTrace();
            } catch (InvalidDnDOperationException idndoe) {
                idndoe.printStackTrace();
            }
            
            private final String parseDropFile(String aAbsoluteFilePath)   {
                String dropFileParseExt = new String(aAbsoluteFilePath);
                final int index = dropFileParseExt.lastIndexOf('.');
                final int max = Math.max(dropFileParseExt.lastIndexOf('/'), dropFileParseExt.lastIndexOf('\\'));
                    if(index > max)   {
                        dropFileParseExt = dropFileParseExt.substring(index + 1);
                    }
                return dropFileParseExt;
            }
}