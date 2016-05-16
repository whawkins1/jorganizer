package ui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.menubar.MenuBar;
import ui.panel.MainTablePanel;
import utility.Utility;
import jorganizerpackage.JOrganizerMain;
import list.Album;

public final class OrganizerMainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private boolean fWindowNotActive;
     
    public OrganizerMainFrame(final String aVlcLocation, final boolean aEnableAudioVideo) {
        super("JOrganizer");
        fWindowNotActive = true;
        final MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
        
        JPanel mediaPanel = new JPanel(new BorderLayout());
        mediaPanel.add(new MainTablePanel(aVlcLocation, aEnableAudioVideo), BorderLayout.EAST);
        setfTableFilters();
        
        add(mediaPanel, BorderLayout.CENTER);
        final Toolkit tk = Toolkit.getDefaultToolkit();
        final Dimension d = tk.getScreenSize();
        setSize(d.width/2, d.height/2);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon imgFrame = new ImageIcon(getClass().getResource("/resources/filecabinet.png"));
        setIconImage(imgFrame.getImage());
        fSearchCurrentAlbumTextField.setText("Search...");
        fFileMenu.requestFocusInWindow();
        
        fAmd = new AddMediaDialog(frame, true, fTableModel);
        fTml.setMediaDialog(fAmd);         
            
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)   {
                final boolean saved = JOrganizerMain.isSaved();
                if(saved)  {
                    int resultOption = JOptionPane.showConfirmDialog(null, 
                                                                    "The Current Album Is Not fSaved, do you want to save?", 
                                                                    "Save file", 
                                                                    JOptionPane.YES_NO_OPTION);
                    switch(resultOption) {
                        case (JOptionPane.YES_OPTION):
                            final File currentFile = JOrganizerMain.getCurrentFile();
                            if(currentFile == null) {
                                saveFileAs();
                                return;
                            } 
                                saveFile(currentFile);
                        case (JOptionPane.NO_OPTION):
                            return; 
                        }
                }               
                dispose();
                System.exit(0);
            }
        });
        setJMenuBar(makeMenuBar());
    }
    
    private final void enableGui(final Album aSetCurrentAlbum)      {
        fCurrentAlbum = aSetCurrentAlbum;
        fTml.setCurrentAlbum(fCurrentAlbum);
        fSearchCurrentAlbumTextField.setText("Search...");
        if(fWindowNotActive)            {
            modifyAccessGui(true);
            fAlbumComboBox.setEnabled(true);
            fWindowNotActive = false;
        }
    }
    
    public final static void modifyAccessGUI(final boolean aAccess)        {
        fSearchAlbumButton.setEnabled(aAccess);
        fSearchCurrentAlbumTextField.setEditable(aAccess);
        fSearchTagsRadBut.setEnabled(aAccess);
        fSearchDescrRadBut.setEnabled(aAccess);
        fTable.setEnabled(aAccess);         
        checkMenuAccess();
    }
    
    public final void setWindowActive(final boolean aActive) { this.fWindowNotActive = aActive; }
    
    public final boolean windowIsNotActive() { return this.fWindowNotActive; }
    
    public static void main(String[] args)        {
        try     {
              UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }   catch(UnsupportedLookAndFeelException ulfe)  {
                ulfe.printStackTrace();
                System.out.println("Unsupported Look And Feel");                
            }   catch(IllegalAccessException iae)   {
                iae.printStackTrace();
                System.out.println("Unsupported Look And Feel");
            }   catch(InstantiationException ie)    {
                ie.printStackTrace();
                System.out.println("Unsupported Look And Feel");
            }   catch(ClassNotFoundException cnfe)  {
                cnfe.printStackTrace();
                System.out.println("Unsupported Look And Feel");
            }
        final File vlcCheckFile = new File("C:\\Program Files\\vlc.exe");
        boolean enableAudioVideo = false;
        String vlcLocation = "";
        
        if(vlcCheckFile.exists())  {
            vlcLocation = vlcCheckFile.getAbsolutePath();
            enableAudioVideo = true;
        } else {
            String[] options = {"Locate VLC", "Not Now"};
            final int resultVlc = JOptionPane.showOptionDialog(null, "To Allow the User to Play Audio and Video VLC Media Player(http://www.videolan.org/vlc/download-windows.hfTml) Must be Installed." + "\n" +
                                               "Further, this File Could not be Found in its Default Directory(C:\\Program Files\\vlc.exe) or the Mobile Version is Being used." + "\n" +
                                               "In Either Case Select 'Locate VLC' to Find the vlc.exe or 'Not Now' to Just View Images",
                                               "Vlc not Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,options, options[0]);
        
            if(resultVlc == JOptionPane.YES_OPTION)  {
                boolean notCorrectFile = true;
                int resultChooseLocation;
                do {
                    JFileChooser chooseVlcLocation = new JFileChooser();
                    chooseVlcLocation.setCurrentDirectory(new File("C:\\Users\\Bill\\Desktop"));
                    resultChooseLocation = chooseVlcLocation.showOpenDialog(null);
                                        
                    if(resultChooseLocation == JFileChooser.APPROVE_OPTION)  {
                        final File vlcSelectedFile = chooseVlcLocation.getSelectedFile();
                        final String vlcSelectedFileName = vlcSelectedFile.getName();
                        
                        if(vlcSelectedFileName.equals("vlc.exe"))  {
                            vlcLocation = vlcSelectedFile.getAbsolutePath();    
                            enableAudioVideo = true;
                            notCorrectFile = false;                         
                        } else {
                            JOptionPane.showMessageDialog(null, "The Selected File is not the VLC Mediaplayer, Please Select Another File");
                        }                   
                    } else if (resultChooseLocation == JFileChooser.CANCEL_OPTION) {
                        notCorrectFile = false;
                    }
                }while((notCorrectFile));
            }
        }
        //inner class for thread
        class threadMain implements Runnable {
            final Boolean fEnableAudioVideo;
            final String fVlcLocation;
            threadMain(Boolean aEnableAudioVideo, String aVlcLocation) {
                this.fEnableAudioVideo = aEnableAudioVideo;
                this.fVlcLocation = aVlcLocation;
            }
            public void run()               {
                new OrganizerMainFrame(fVlcLocation, fEnableAudioVideo);        
            }
        }
        final Runnable thread = new threadMain(enableAudioVideo, vlcLocation);
        SwingUtilities.invokeLater(thread);
    }       
    
    
    
    
}
