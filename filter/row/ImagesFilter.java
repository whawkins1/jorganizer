package filter.row;

import java.util.ArrayList;

import javax.swing.RowFilter;

import list.ImageTypeList;


public final class ImagesFilter extends RowFilter<Object, Object> {
    private final ArrayList<String> fTypeList;
    
    public ImagesFilter() {
        fTypeList = new ImageTypeList();
    }
    
        @Override public final boolean include(Entry<? extends Object, ? extends Object> entry) {
            final String imageType = ((String)entry.getValue(1));
            return fTypeList.contains(imageType);
        }
}