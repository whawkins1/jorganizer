package listener.focus;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public final class MediaDescriptionTextFieldFocusListener 
                                        implements FocusListener {
    
    final JTextField fTextFieldAddMediaDescription;
    
    public MediaDescriptionTextFieldFocusListener(final JTextField aTextFieldAddMediaDescription) {
        fTextFieldAddMediaDescription = aTextFieldAddMediaDescription;
    }
    
    
        @Override public final void focusGained(final FocusEvent event)        {
            final String description = fTextFieldAddMediaDescription.getText().trim();
            final boolean hasEnterDescription = description.equals("Enter Description"); 
            
            if(hasEnterDescription)        
              fTextFieldAddMediaDescription.setText("");
        }
        
            
        @Override public final void focusLost(final FocusEvent event)      {
            final String description = fTextFieldAddMediaDescription.getText().trim();
            final boolean hasNoDescription = description.isEmpty();
            
            if(hasNoDescription)    
                fTextFieldAddMediaDescription.setText("Enter Description");
        }
}