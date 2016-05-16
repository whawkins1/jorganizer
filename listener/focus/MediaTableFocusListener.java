package listener.focus;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.UIManager;

import ui.textfield.SearchAlbumTextField;


public final class MediaTableFocusListener 
                                implements FocusListener {
    final SearchAlbumTextField fSearchTF;
    
    
    public MediaTableFocusListener(final SearchAlbumTextField aSearchTF) {
        this.fSearchTF = aSearchTF;
    }
    
        public final void focusGained(final FocusEvent event)      {
            final boolean isSearchTF = (event.getSource() == fSearchTF); 
            if (isSearchTF)  {
               setPropertiesTF("", 
                               UIManager.getColor("TextField.foreground"), 
                               new Font("Dialog", Font.PLAIN, 12)); 
            }           
        }
        
        public final void focusLost(final FocusEvent event)   {
            final boolean isSearchTF = (event.getSource().equals(fSearchTF));
            if (isSearchTF)    {
                final boolean tfIsEmpty = fSearchTF.getText().trim().isEmpty(); 
                if (tfIsEmpty) 
                    setPropertiesTF("Search...", 
                                    Color.LIGHT_GRAY, 
                                    new Font("Dialog", Font.ITALIC, 11));
            }           
        }
        
        private final void setPropertiesTF(final String aText, 
                                           final Color aColor, 
                                           final Font aFont) {
            fSearchTF.setText(aText);
            fSearchTF.setForeground(aColor);
            fSearchTF.setFont(aFont);
        }
}