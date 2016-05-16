package filter.row;



import javax.swing.RowFilter;

import list.VideoTypeList;


public final class VideosFilter extends RowFilter<Object, Object>{
 private final VideoTypeList fTypeList;
    
    public VideosFilter() {
        fTypeList = new VideoTypeList();
    }
    
        @Override public final boolean include(Entry<? extends Object, ? extends Object> entry) {
            final String videoType = ((String)entry.getValue(1));
            return fTypeList.contains(videoType);
        }
}