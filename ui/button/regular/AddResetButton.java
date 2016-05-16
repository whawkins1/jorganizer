package ui.button.regular;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;

public final class AddResetButton extends JButton {
    private static final long serialVersionUID = 1L;

    public AddResetButton() {
        super("Add/Reset");
        addActionListener(al);
        setPreferredSize(new Dimension(116, 30));
        setMinimumSize(new Dimension(116, 30));
        setMaximumSize(new Dimension(116, 30));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText("Confirms Media To be added To Current Album, Then Resets Window");
    }
}
