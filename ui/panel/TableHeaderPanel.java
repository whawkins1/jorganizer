package ui.panel;
import java.awt.BorderLayout;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.JPanel;

import ui.button.regular.HeaderRendererButton;
import list.Album;
import listener.mouse.HeaderMouseListener;
import model.table.MediaTableModel;

public final class TableHeaderPanel extends JPanel  {
	
	
    private static final long serialVersionUID = 1L;

    public TableHeaderPanel (TableColumn aColumn, 
	                             JTableHeader aHeader, 
	                             Album aCurrentAlbum, 
	                             MediaTableModel aTableModel)    {
		  	super(new BorderLayout());
		  
		  HeaderRendererButton renderer = new HeaderRendererButton();
		  aColumn.setHeaderRenderer(renderer);
		  aHeader.addMouseListener(new HeaderMouseListener(aHeader, 
		                                                   renderer, 
		                                                   aCurrentAlbum, 
		                                                   aTableModel, 
		                                                   this));
    }  
}