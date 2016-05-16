package ui.panel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class AboutRightPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public AboutRightPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(20));
        add(new JLabel("Name: JOrganizer"));
        add(Box.createVerticalStrut(4));
        add(new JLabel("Version: 1.0.0"));
        add(Box.createVerticalStrut(4));
        add(new JLabel("Copyright: 2016"));
    }
}
