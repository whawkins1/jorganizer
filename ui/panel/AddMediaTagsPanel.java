package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class AddMediaTagsPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public AddMediaTagsPanel(final JTextField aTextField) {
        super(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        add(new JLabel("Tags: "));
        add(aTextField);
    }
}