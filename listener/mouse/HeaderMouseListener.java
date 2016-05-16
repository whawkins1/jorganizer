package listener.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.table.JTableHeader;

import ui.button.regular.HeaderRendererButton;
import list.Album;
import model.table.MediaTableModel;

public final class HeaderMouseListener implements MouseListener    {
    final JTableHeader fHeader;
    final HeaderRendererButton renderer;
    final Album fCurrentAlbum;
    final JPanel fHeaderPanel;
    final MediaTableModel fTableModel;

    public HeaderMouseListener(JTableHeader aHeader, 
                               HeaderRendererButton aRenderer, 
                               Album afCurrentAlbum,
                               final MediaTableModel aTableModel,
                               final JPanel aPanel)        {
         this.fHeader   = aHeader;
         this.renderer = aRenderer;
         this.fCurrentAlbum = afCurrentAlbum;
         this.fHeaderPanel = aPanel;
         this.fTableModel = aTableModel;
    }

    @Override  public final void mouseClicked(MouseEvent me)       {
            final int fColumnIndex = fHeader.columnAtPoint(me.getPoint());
         
            if (fColumnIndex != -1)           {
                switch(fColumnIndex)            {
                  case 0: 
                       fCurrentAlbum.setSortModeMedia("Sort By File Name");
                       break;
                  case 1:
                   fCurrentAlbum.setSortModeMedia("Sort By Type");
                       break;                 
                  case 2:
                       fCurrentAlbum.setSortModeMedia("Sort By Date");
                       break;
                 case 3:
                       fCurrentAlbum.setSortModeMedia("Sort By Tags");
                       break;
                 case 4:
                       fCurrentAlbum.setSortModeMedia("Sort By Description");
                       break;    
                 default:
                       break;
                }                
                  Collections.sort(fCurrentAlbum);
                  fTableModel.setDataList(fCurrentAlbum);
                  fTableModel.fireTableDataChanged();
            }
        };
     
         @Override public final void mousePressed(MouseEvent me)       {
             renderer.setPressedColumn(fHeader.getTable().convertColumnIndexToModel(fHeader.columnAtPoint(me.getPoint())));
             fHeaderPanel.repaint();        
         }
     
         @Override
         public final void mouseReleased(MouseEvent me)      {
             renderer.setPressedColumn(-1);
             fHeaderPanel.repaint();   
         }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }   
  }

 
