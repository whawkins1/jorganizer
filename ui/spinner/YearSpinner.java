package ui.spinner;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public final class YearSpinner extends JSpinner {
    private static final long serialVersionUID = 1L;

    public YearSpinner() {
        final Calendar cal = Calendar.getInstance();
        final int currentYear = cal.get(Calendar.YEAR);
        setModel(new SpinnerDateModel(new Date(), currentYear, (currentYear - 10), Calendar.YEAR));
        final DefaultEditor editor = ((JSpinner.DefaultEditor)getEditor());
        final JFormattedTextField tf = editor.getTextField();
        tf.setEditable(false);
        setEnabled(false);
    }
}