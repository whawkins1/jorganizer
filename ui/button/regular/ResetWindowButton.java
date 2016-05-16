package ui.button.regular;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;

public final class ResetWindowButton extends JButton {
    private static final long serialVersionUID = 1L;
    
    public ResetWindowButton() {
        super("Reset Window");
        addActionListener(al);
        setPreferredSize(new Dimension(116, 30));
        setMinimumSize(new Dimension(116, 30));
        setMaximumSize(new Dimension(116, 30));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText("Resets Window To Its Default State");
    }
}
