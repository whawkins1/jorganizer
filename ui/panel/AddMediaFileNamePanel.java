package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import utility.Utility;

public final class AddMediaFileNamePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public AddMediaFileNamePanel(final JTextField aTextField) {
        super(new FlowLayout(FlowLayout.LEFT, 10, 10));    

        add(Utility.makeLabel("File Name: "));
        add(aTextField);
    }
}