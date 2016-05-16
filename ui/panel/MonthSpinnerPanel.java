package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.spinner.MonthSpinner;

public final class MonthSpinnerPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    public MonthSpinnerPanel(final MonthSpinner aSpinner) {
        super(new FlowLayout(FlowLayout.LEFT, 5, 5));
        add(new JLabel("Month"));
        add(aSpinner);
    }
}