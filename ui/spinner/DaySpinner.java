package ui.spinner;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public final class DaySpinner extends JSpinner {
    private static final long serialVersionUID = 1L;

    public DaySpinner() {
        final Calendar cal = Calendar.getInstance();
        final int lastDayMonth = cal.getMaximum(Calendar.DAY_OF_MONTH);
        setModel(new SpinnerDateModel(new Date(), 1, lastDayMonth, Calendar.DAY_OF_MONTH));
        final DefaultEditor editor = ((JSpinner.DefaultEditor)getEditor());
        final JFormattedTextField tf = editor.getTextField();
        tf.setEditable(false);
        setEnabled(false);
    }
}