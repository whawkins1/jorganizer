package listener.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ui.combobox.AlbumComboBox;
import utility.Utility;
import filter.file.AlbumFilter;
import list.Album;

public final class OpenAlbumActionListener
                            implements ActionListener {
    private final Window fParent;
    private final List<Album> fAlbumList;
    private final AlbumComboBox fAlbumComboBox;
    
    public OpenAlbumActionListener(final Window aParent, 
                                   final List<Album> aAlbumList,
                                   final AlbumComboBox aAlbumComboBox) {
        this.fParent = aParent;
        this.fAlbumList = aAlbumList;
        this.fAlbumComboBox = aAlbumComboBox;
    }

    @Override public final void actionPerformed(final ActionEvent event) {
        final JFileChooser chooseAlbum = new JFileChooser(new File("C:/Users/Bill/Desktop"));
        chooseAlbum.setFileFilter(new AlbumFilter());
        
        final int status = chooseAlbum.showOpenDialog(fParent);

            if(status == JFileChooser.APPROVE_OPTION)  {
                File albumFile = chooseAlbum.getSelectedFile();
               
                Album loadAlbum = null;
                final DefaultComboBoxModel<String> comboBoxModel = (DefaultComboBoxModel<String>)fAlbumComboBox.getModel();
               try(final DataInputStream dis = new DataInputStream(new FileInputStream(albumFile)))  {
                   for(int a = 0; a < dis.readInt(); a++)  {
                       loadAlbum = new Album(dis.readUTF());
                       loadAlbum.load(dis);
                       fAlbumList.add(loadAlbum);
                       comboBoxModel.addElement(loadAlbum.getTitle());
                   }
               } catch(final FileNotFoundException exception1)  {
                   JOptionPane.showMessageDialog(fParent, "Error Opening File");
                   exception1.printStackTrace();
                   return;
               }  catch(final IOException exception2)   {
                   Utility.showInfoMessage(fParent, "Error Loading File");
                   exception2.printStackTrace();
                   return;
               }
               enableGui(loadAlbum);
               checkMenuAccess();
                   }
            }        
    }

