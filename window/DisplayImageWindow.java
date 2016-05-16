package window;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.io.File;
import java.io.IOException;

public final class DisplayImageWindow extends JWindow     {
    private static final long serialVersionUID = 1L;
    final JLabel ffImageLabel;
	Image fImage;
	final JFrame fParent;
	
	public DisplayImageWindow(JFrame aParent, File aImageFile, String aFileType)     	{
		this.fParent = aParent;						
		final File fImageFileDisplay = aImageFile;
		final String fImageFileType = aFileType;
		
		if(fImageFileType.equals("GIF"))		{	
			 final ImageIcon fImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(fImageFileDisplay.getAbsolutePath()));
		     ffImageLabel = new JLabel(fImageIcon);
		     setSize(fImageIcon.getIconWidth(), fImageIcon.getIconHeight());
		}  else	  {
			try	 {
				fImage = ImageIO.read(fImageFileDisplay);
			}  catch(IOException ioe)	{
				JOptionPane.showMessageDialog(this, "There Was an Error Reading fImage File" , "Error Reading fImage", JOptionPane.ERROR_MESSAGE);
				ioe.printStackTrace();
			}	catch(IllegalArgumentException iae)	{
				JOptionPane.showMessageDialog(this, "The fImage Was Null or Empty" , "Error Reading fImage", JOptionPane.ERROR_MESSAGE);
				iae.printStackTrace();
			}
			
			ffImageLabel = new JLabel(new ImageIcon(fImage));
			setSize(fImage.getWidth(null), fImage.getHeight(null));
		}
			setupDisplay();
	}
	
	
	public final void setupDisplay()   	{
		ffImageLabel.addMouseListener(new MouseAdapter()		{
			public final void mouseClicked(MouseEvent e)			{
				dispose();
			}
		});
        
		getContentPane().add(ffImageLabel);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLocationRelativeTo(fParent);
		setVisible(true);
	}
		
	public final void mouseClicked(final MouseEvent event)  	{
		if(event.getSource().equals(ffImageLabel))		{
			dispose();			
		}
	}
}