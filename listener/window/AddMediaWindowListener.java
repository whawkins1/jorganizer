package listener.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import listener.action.AddResetListener;

public final class AddMediaWindowListener 
                          implements WindowListener {

    @Override public final void windowActivated(final WindowEvent event) {}

    
    @Override public void windowClosed(final WindowEvent event) {}

    
    @Override public final void windowClosing(final WindowEvent event) {
        AddResetListener.resetForm();    
    }

    
    @Override public final void windowDeactivated(WindowEvent arg0) {}

    
    @Override public final void windowDeiconified(WindowEvent arg0) {}

    
    @Override public final void windowIconified(WindowEvent arg0) {}

    
    @Override public final void windowOpened(WindowEvent arg0) {}
}