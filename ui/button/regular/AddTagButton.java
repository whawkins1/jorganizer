package ui.button.regular;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;

import listener.action.AddTagListener;
import ui.dialog.AddMediaDialog;

public final class AddTagButton extends JButton {
    private static final long serialVersionUID = 1L;

    public AddTagButton(final AddMediaDialog aMediaDialog, final JTextField aTagTextField) {
        super("Add Tag");
        addActionListener(new AddTagListener(aMediaDialog, aTagTextField));
        setPreferredSize(new Dimension(116, 30));
        setMinimumSize(new Dimension(116, 30));
        setMaximumSize(new Dimension(116, 30));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText("Add Tag To Current Media");
    }
}
