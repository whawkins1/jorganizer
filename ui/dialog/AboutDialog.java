package ui.dialog;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Window;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import ui.panel.AboutRightPanel;
import utility.Utility;

public final class AboutDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public AboutDialog(final Window aParent)  {
		   super(aParent, "About");
           JLabel aboutLeftLabel = null;
		   
		   try {
            aboutLeftLabel = new JLabel((new ImageIcon(ImageIO.read(getClass().getResource("/resources/filecabinetlarge.png")))));
           } catch (final IOException exception) {
            Utility.showErrorMessage(aParent, "Error Retrieving filecabinetLarge.png");
            exception.printStackTrace();
           }
		   
		   final JPanel aboutLeftPanel = new JPanel();
		   aboutLeftPanel.add(aboutLeftLabel);
		
           final JPanel aboutRightPanel = new AboutRightPanel();
       
	       final JPanel aboutMainPanel = new JPanel(new BorderLayout());
	       aboutMainPanel.add(aboutLeftPanel, BorderLayout.WEST);
	       aboutMainPanel.add(aboutRightPanel, BorderLayout.EAST);
	       add(aboutMainPanel);       
		    
	       final Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		   setSize(screenDimensions.width/2, screenDimensions.height/2);
		   pack();
		   setLocationRelativeTo(null);
		   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   Image imageFrame = null;
           try {
              imageFrame = ImageIO.read(getClass().getResource("/resources/filecabinet.png"));
           } catch (IOException e) {
             Utility.showErrorMessage(aParent, "Error Retrieving filecabinet.png");
             e.printStackTrace();
           } 
		   setIconImage(imageFrame);
		   setTitle("JOrganizer");
		   setVisible(true);
	}	
}