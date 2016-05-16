package listener.item;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class ViewAudioItemListener 
                            implements ItemListener {
    
    private final TableRowSorter<TableModel> fTableSorter;
    
    public ViewAudioItemListener(final TableRowSorter<TableModel> aTableSorter) {
        this.fTableSorter = aTableSorter;
    }

    @Override public final void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}
