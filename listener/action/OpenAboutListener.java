package listener.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.dialog.AboutDialog;

public final class OpenAboutListener 
                           implements ActionListener {
    private final Window fParent;  
    
    public OpenAboutListener(final Window aWindow) {
        this.fParent = aWindow;
    }
    
        @Override public final void actionPerformed(final ActionEvent event) {
            new AboutDialog(fParent);
        }
}