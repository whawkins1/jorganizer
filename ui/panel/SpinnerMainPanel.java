package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import utility.Utility;

public final class SpinnerMainPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public SpinnerMainPanel(final MonthSpinnerPanel aMonthPanel,
                            final DaySpinnerPanel aDayPanel,
                            final YearSpinnerPanel aYearPanel) {
        super(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(Utility.makeLabel("Date: "));
        add(aMonthPanel);
        add(aDayPanel);
        add(aYearPanel);
    }
}