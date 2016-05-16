package ui.button.regular;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class HeaderRendererButton extends JButton 
                                     implements TableCellRenderer     {

private static final long serialVersionUID = -4016574519552212252L;
int pushedColumn;

public HeaderRendererButton()       {
    pushedColumn   = -1;
    setMargin(new Insets(0,0,0,0));
}

@Override public final Component getTableCellRendererComponent(JTable aTable, 
                                                               Object aValue, 
                                                               boolean aIsSelected, 
                                                               boolean aHasFocus, 
                                                               int aRow, 
                                                               int aColumn)       {
        setText((aValue == null) ? "" : aValue.toString());
        boolean isPressed = (aTable.convertColumnIndexToModel(aColumn) == pushedColumn);
        getModel().setPressed(isPressed);
        getModel().setArmed(isPressed);
        return this;
    }

   public void setPressedColumn(final int col) {pushedColumn = col;}
}
