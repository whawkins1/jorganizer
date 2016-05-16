package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.frame.OrganizerMainFrame;

public final class ExitListener 
                    implements ActionListener {
    private final JFrame fMainFrame;
    
    public ExitListener(final OrganizerMainFrame aMainFrame) {
        this.fMainFrame = aMainFrame;
    }
    
        @Override public final void actionPerformed(final ActionEvent event) {
            fMainFrame.dispose();
            System.exit(0);     
        }
}