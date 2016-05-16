package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.spinner.DaySpinner;

public final class DaySpinnerPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    
    public DaySpinnerPanel(final DaySpinner aSpinner) {
        super(new FlowLayout(FlowLayout.LEFT));
        add(new JLabel("Day"));
        add(aSpinner);
    }
}