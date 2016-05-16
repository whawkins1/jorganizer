package ui.panel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;

public final class AddMediaInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public AddMediaInfoPanel(final JTextField aFileNameTextField,
                             final JTextField aDescriptionTextField,
                             final JTextField aTagsTextField,
                             final MonthSpinner aMonthSpinner,
                             final DaySpinner aDaySpinner,
                             final YearSpinner aYearSpinner) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 12)));
        
        add(new AddMediaFileNamePanel(aFileNameTextField));
        add(new AddMediaDescriptionPanel(aDescriptionTextField));
        add(new AddMediaTagsPanel(aTagsTextField));
        
        add(new SpinnerMainPanel(new MonthSpinnerPanel(aMonthSpinner), 
                                 new DaySpinnerPanel(aDaySpinner), 
                                 new YearSpinnerPanel(aYearSpinner)));         
    }
}