package model.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import mediadata.MediaData;

public final class MediaTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 1L;

    public static final int FILE_NAME_INDEX = 0;
    public static final int MEDIA_TYPE_INDEX = 1;
    public static final int DATE_INDEX = 2;
    public static final int TAGS_INDEX = 3;    
    public static final int DESCRIPTION_INDEX = 4;
               
    private List<MediaData>  fDataList;
    final private String [] fColumnNames = {"Name", "Media Type", "Date", "Tags", "Description"};
            
      public MediaTableModel()    {
          fDataList = new ArrayList<MediaData>();
      }

        public final int getRowCount()      {
            return fDataList.size();
        }
        
        public final int getColumnCount()       {
            return fColumnNames.length;
        }
        
        public final String getColumnName(int col)      {
            return fColumnNames[col];
        }
        
        public final void addMedia(MediaData aMediaDataTable)       {
            fDataList.add(aMediaDataTable);
            fireTableDataChanged();
        }
        
        public final void removeMedia(int aSelectedRow)     {
            fDataList.remove(aSelectedRow);
            fireTableDataChanged();
        }
        
        public final void removeMedia(int[] aSelectedRows)      {
            Arrays.sort(aSelectedRows);
            
            for(int i = aSelectedRows.length - 1; i >= 0; i--) {
                fDataList.remove(aSelectedRows[i]); 
            }
            fireTableDataChanged();
        }
        
        public final List<MediaData> getDataList()      {
            return fDataList;
        }
        
        public final void setDataList(List<MediaData> aListToSet)       {
            this.fDataList = aListToSet;
        }
        
        public final void clearTable()      {
            fDataList.clear();
            fireTableDataChanged();
        }       
        
        public final Object getValueAt(int row, int column)     {
            final MediaData record = (MediaData) fDataList.get(row);
            
            switch(column)          {
                case FILE_NAME_INDEX:
                    return record.getFileName();
                case MEDIA_TYPE_INDEX:
                    return record.getMediaType();
                case DATE_INDEX:
                    return record.getDate();
                case TAGS_INDEX:
                    final List<String> listTags = record.getTags();
                    
                    final int tagSize = listTags.size();
                    
                    if(tagSize == 0)        {
                        return null;
                    }  else if(tagSize == 1)    {
                        return listTags.get(0).toString();
                    }  else     {   
                    
                        final StringBuilder sb = new StringBuilder();
                    
                        for(int x = 0; x < tagSize; x++)            {
                           sb.append(listTags.get(x).toString());
                           sb.append(", ");
                        }
                    
                        final String appendedTags = sb.deleteCharAt(sb.lastIndexOf(",")).toString().trim();
                        return appendedTags;
                    }       
                case DESCRIPTION_INDEX:
                    return record.getDescription();
                default:
                    return new Object();
            }
      }

}
