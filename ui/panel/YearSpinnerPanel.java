package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.spinner.YearSpinner;

public final class YearSpinnerPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public YearSpinnerPanel(final YearSpinner aSpinner) {
        super(new FlowLayout(FlowLayout.LEFT));
        add(new JLabel("Year"));
        add(aSpinner);
    }
}
