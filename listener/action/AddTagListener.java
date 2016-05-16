package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ui.dialog.AddMediaDialog;
import utility.Utility;

public final class AddTagListener 
                        implements ActionListener {
    private final AddMediaDialog fAddMediaDialog;
    private final StringBuilder fTagsBuilder;
    private final JTextField fTagsTextField;
    
    public AddTagListener(final AddMediaDialog aAddMediaDialog,
                          final JTextField aTagsTextField) {
          this.fAddMediaDialog = aAddMediaDialog;
          this.fTagsTextField = aTagsTextField;
          fTagsBuilder = new StringBuilder();
    }
    
    @Override public final void actionPerformed(final ActionEvent event) {
        String userAddedTag;
        
        do  {
           userAddedTag = JOptionPane.showInputDialog(this, "Enter Tag (char limit 10)");
           
           if(userAddedTag == null)  {
               return;
           } else if(userAddedTag.trim().length() > 10)  {
               Utility.showErrorMessage(fAddMediaDialog, "The Entered Tag " + userAddedTag + " has more than 10 chars, Please enter another tag");
           }               
           
       }  while (userAddedTag.trim().length() > 10);
       
           if(userAddedTag != null)    {   
               final boolean noDuplicateTag = checkDuplicateTag(userAddedTag);         
               final String stringCheckChars = checkIllegalCharacters(userAddedTag);
               
               if( (noDuplicateTag) && (stringCheckChars.isEmpty()))   {   
                   fTagsTextField.setText(fTagsBuilder.toString() + userAddedTag);
                   fTagsBuilder.append(userAddedTag + ", ");
                   return;
               } 
                   if(!(noDuplicateTag))   {
                       Utility.showErrorMessage(fAddMediaDialog, "The Entered Tag Is already Being Used");
                       return;
                   } 
                   
                    final boolean isEnteredTag = !(stringCheckChars.isEmpty()); 
                    if(isEnteredTag)  
                        Utility.showErrorMessage(fAddMediaDialog, "The Entered Tag Contains The Illegal Character " + " + stringCheckChars + ");
           }     
    }
        
    

        private final boolean checkDuplicateTag(String userAddedTag)        {
            final List<String> tagList =  ( Arrays.asList( fTagsBuilder.toString().trim().split(",") ) );
                                                
            for(int x = 0; x < tagList.size(); x++)         {
                if(tagList.get(x).toString().trim().equalsIgnoreCase(userAddedTag))             {
                    return false;
                }
            }
            return true;
        }

        
        private final String checkIllegalCharacters(String userEnterTagCheck)       {
            final Pattern patternToCheck = Pattern.compile("[\\/\\\\;:(),]");
            final Matcher matchChars = patternToCheck.matcher(userEnterTagCheck);
            
            if(matchChars.find())           {
                return matchChars.group();
            }  else  {
                return "";              
            }
        }
}