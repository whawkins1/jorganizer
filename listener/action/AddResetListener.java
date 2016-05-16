package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class AddResetListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        if( (fileNameReplace()) )   {
            createMediaData();
            JOrganizerMain.checkMenuAccess();
            JOrganizerMain.setSave(false);
            resetForm();
        }  else  {
            return;
        }
    }
    
        public final static void resetForm()   {
            fTextFieldfAdfDfMediaFileName.setText(""); 
            fTextFieldAddMediaDescription.setText("");
            fTextFieldTagsAdded.setText("");
            
            fTextFieldfAdfDfMediaFileName.setEditable(false);
            fTextFieldAddMediaDescription.setEditable(false);
            fTextFieldTagsAdded.setText("");
            
            fChooseFileButton.setEnabled(true);
            fAddSelectedMediaCloseButton.setEnabled(false);
            fAddSelectedMediaResetButton.setEnabled(false);
            fResetWindowButton.setEnabled(false);
            fAddTagButton.setEnabled(false);
            
            fSpinnerAddMediaMonth.setEnabled(false);
            fSpinnerAddMediaDay.setEnabled(false);
            fSpinnerAddMediaYear.setEnabled(false);
                                        
            fTagBuilder.setLength(0);       
            
            fChooseFileButton.isDefaultButton();
        }
    
    

}
