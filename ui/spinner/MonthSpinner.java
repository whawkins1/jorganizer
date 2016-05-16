package ui.spinner;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public final class MonthSpinner extends JSpinner {
    private static final long serialVersionUID = 1L;

    public MonthSpinner() {
        super(new SpinnerDateModel(new Date(), 1, 12, Calendar.MONTH));
        final DefaultEditor editor = ((JSpinner.DefaultEditor)getEditor());
        final JFormattedTextField tf = editor.getTextField();
        tf.setEditable(false);
        setEnabled(false);
    }
}