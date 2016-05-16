package ui.dialog;
import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JDialog;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class SlideShowDialog extends JDialog 
                        implements ActionListener, ChangeListener     {
   
    private static final long serialVersionUID = 1L;
    private static final int DELAY_MIN = 0;
	private static final int DELAY_INIT = 5;
	private static final int DELAY_MAX = 10;
	private int fImageTracker;
	private int fDelaySec;
	private JButton fPlayButton;
	private JButton fPauseButton;
	private JButton fStopButton;
	private JButton fPreviousImageSlideButton;
	private JButton fNextImageSlideButton;
	private JButton fRestartSlideButton;	
	private List<File> fImageFileNameList = new ArrayList<File>();
	private BufferedImage fDisplayedImage;
	final private JPanel fImageSlideShowPanel = new JPanel(new BorderLayout());
	private JLabel fImageSlideShowLabel;
	private Timer fTimer;	    
		
	public SlideShowDialog(JFrame parent, Boolean modal, List<File> aImageFileNameList)   	{
		    super(parent, "Slideshow", modal);
	        this.fImageFileNameList = aImageFileNameList;
		    fImageTracker = 0;
	        
	        fPreviousImageSlideButton = makeButton("Go To Previous Slide", this, "Go to Previous Image in SlideShow", "/resources/br_prev.png", false);
		    fNextImageSlideButton = makeButton("Go To Next Slide", this, "Go To Next Image in SlideShow", "/resources/br_next_icon.png", true);
	        
	        fImageSlideShowPanel.add(fPreviousImageSlideButton, BorderLayout.WEST);
	        fImageSlideShowPanel.add(fNextImageSlideButton, BorderLayout.EAST);
	        	        
	        JSlider speedSlideShow = new JSlider(JSlider.HORIZONTAL, DELAY_MIN, DELAY_MAX, DELAY_INIT);
	        speedSlideShow.addChangeListener(this);
	        
	        speedSlideShow.setMajorTickSpacing(5);
	        speedSlideShow.setMinorTickSpacing(1);
	        speedSlideShow.setPaintTicks(true);
	        speedSlideShow.setPaintLabels(true);
	        speedSlideShow.setSnapToTicks(true);
	        	
	        fDelaySec = speedSlideShow.getValue();
	        
	        final JPanel sliderSpeedPanel = new JPanel();
	        sliderSpeedPanel.add(speedSlideShow, BorderLayout.CENTER);	        
	        	
	        final JPanel controlSlidePanel = new JPanel(new FlowLayout());
	        
	        fPlayButton = makeButton("Play SlideShow", this, "Plays SlideShow", "/resources/media_play.png", true);
	        fPauseButton = makeButton("Pause SlideShow", this, "Pauses SlideShow", "/resources/controls_pause_32.png",  false);
	        fStopButton = makeButton("Stop SlideShow", this, "Stops SlideShow", "/resources/media_stop.png", false);
		    fRestartSlideButton = makeButton("Restart Slide", this, "Will Start SlideShow From First Image", "/resources/playback_reload_icon.png", false);
		    
	        controlSlidePanel.add(fPlayButton);
	        controlSlidePanel.add(fPauseButton);
	        controlSlidePanel.add(fStopButton);
	        controlSlidePanel.add(fRestartSlideButton);
	        	        
	        Container cp = getContentPane();
	        
	        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
	        cp.add(fImageSlideShowPanel);
	        cp.add(sliderSpeedPanel);
	        cp.add(controlSlidePanel);
	        	        
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            readPanelImage();
            ImageIcon imgDialog = new ImageIcon(getClass().getResource("/resources/filecabinet.png")); 
            setIconImage(imgDialog.getImage());
            pack();
            setLocationRelativeTo(null);
            setVisible(true);            
	}

	    public final void actionPerformed(ActionEvent e)	    {
	    	if(e.getActionCommand().equals("Play SlideShow"))	  	{
		    		fTimer = new Timer( (1000 * fDelaySec), new fTimerAction());
		    		fTimer.start();
		    			
		    		fPlayButton.setEnabled(false);
		    		fPauseButton.setEnabled(true);
		    		fStopButton.setEnabled(true);
	    	}  else if(e.getActionCommand().equals("Go To Previous Slide"))	{
		    		if( fImageTracker == fImageFileNameList.size() - 1)	{
		    			fNextImageSlideButton.setEnabled(true);
		    		}
		    		fImageTracker--;		    			
		    		replaceImageLabel();
		    		
			    		if( fImageTracker == 0 )	    {
			    			fPreviousImageSlideButton.setEnabled(false);
			    		}	    		
	    	}   else if(e.getActionCommand().equals("Pause SlideShow"))  {
	    		    pauseAction();
	    	}	else if(e.getActionCommand().equals("Stop SlideShow"))	 {
		    		fImageTracker = 0;	    		
		    		pauseAction();
		    		
		    		fStopButton.setEnabled(false);
		    		fPreviousImageSlideButton.setEnabled(false);
		    		fNextImageSlideButton.setEnabled(true);
	    	}	else if(e.getActionCommand().equals("Go To Next Slide"))	{
		    		fImageTracker++;	    		
		    		replaceImageLabel();
		    		
		    		if(fImageTracker == fImageFileNameList.size() - 1)	{
		    			fNextImageSlideButton.setEnabled(false);
		    			pauseAction();
		    		}	    		
	    	}  else if(e.getActionCommand().equals("Restart Slide"))	{
		    		fImageTracker = 0;
		    		final int restartDelay = (1000 * fDelaySec);
		    		fTimer.setDelay(restartDelay);
		    		fTimer.setInitialDelay(restartDelay);
		    		setfTimer("Restart");
		    		replaceImageLabel();
		    		fPreviousImageSlideButton.setEnabled(false);
		    		fNextImageSlideButton.setEnabled(true);
		    		fPlayButton.setEnabled(false);
	    			fPauseButton.setEnabled(true);
	    			fRestartSlideButton.setEnabled(false);
	    		
	    	}	else if(e.getActionCommand().equals("Close Window")) {
	    		dispose();
	    	}	    	
	    }
	    
	    private final void setfTimer(String aMode)	    {
	    	if(aMode.equals("Play"))	    	{
	    		fTimer.start();
	    	} else if(aMode.equals("Pause"))	 {
	    		fTimer.stop();
	    	} else if(aMode.equals("Restart"))	{
	    		fTimer.restart();	    		
	    	}
	    }
	    
	    private final void pauseAction()	    {
	    	setfTimer("Pause");
	    	fPlayButton.setEnabled(true);
			fPauseButton.setEnabled(false);				    	
	    }
	    
	    private final void readPanelImage()	    {
	    	try  {
	    		fDisplayedImage = ImageIO.read(fImageFileNameList.get(fImageTracker));
	        }  catch (IOException ioe) 	{
	        	JOptionPane.showMessageDialog(this, "The Filename of The Image Is Incorrect", "File Image Error", JOptionPane.ERROR_MESSAGE);
				ioe.printStackTrace();
			}  catch(IllegalArgumentException iae)  {
				JOptionPane.showMessageDialog(this, "The Image Was Null or Empty" , "Error Reading Image", JOptionPane.ERROR_MESSAGE);
				iae.printStackTrace();
			}
	    	fImageSlideShowLabel = new JLabel(new ImageIcon(fDisplayedImage.getScaledInstance(1000, 870, Image.SCALE_SMOOTH)));
	    	fImageSlideShowPanel.add(fImageSlideShowLabel, BorderLayout.CENTER);       	
	    }
	    
	    private final void replaceImageLabel()	    {
	    	fImageSlideShowPanel.remove(fImageSlideShowLabel);
	    	readPanelImage();
	    	validate();
	    	setLocationRelativeTo(null);
	    	pack();
	    	repaint();
	    }
	
	    @Override
	    public final void stateChanged(ChangeEvent e)	    {
	    	final JSlider source = (JSlider)e.getSource();
	    	
	    	if(!source.getValueIsAdjusting())	    	{
	    		fDelaySec = (int)source.getValue();
	    		
	    		if( (fDelaySec == 0) && (fTimer.isRunning()))	            {
	    			pauseAction();
	    		}  else if( (fDelaySec <= 1) && (fTimer.isRunning()))  {
	 	    		fTimer.setDelay(1000 * fDelaySec);
	    		}
	    	}
	    }
	    
	    private final JButton makeButton(String aActionCommand, ActionListener aAl, String aToolTipText, String aIconPath, Boolean aEnabled)	    {
	    	JButton b;
	    	b = new JButton();
	    	b.setActionCommand(aActionCommand);
	        b.addActionListener(this);
	        b.setToolTipText(aToolTipText);
	        b.setIcon(new ImageIcon(getClass().getResource(aIconPath)));
	        
	        b.setEnabled(aEnabled);
	        
	        return b;
	    }
	    
	   private final class fTimerAction implements ActionListener	    {
	    	@Override
		   public void actionPerformed(ActionEvent e)	    	{
	    		fImageTracker++;
	    		
	    		if( (fImageFileNameList.size() == 2) || (fImageTracker == fImageFileNameList.size() - 1))	 {
	    			replaceImageLabel();
	                setfTimer("Pause");
	            	
	            	fPlayButton.setEnabled(false);
	    			fPauseButton.setEnabled(false);
	    			fStopButton.setEnabled(false);
	    			fRestartSlideButton.setEnabled(true);
	               	fNextImageSlideButton.setEnabled(false);
	            } else	{
	            	replaceImageLabel();
	            		
	            	if(fImageTracker == 1)	{
			            fPreviousImageSlideButton.setEnabled(true);
			            fRestartSlideButton.setEnabled(true);
			        }	            			            	
	         	}	    		
	    	}
	    }	    
}