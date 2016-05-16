package filter.row;

import javax.swing.RowFilter;

import list.AudioTypeList;

public final class AudioFilter extends RowFilter<Object, Object> {
    private final AudioTypeList fTypeList;
    
    public AudioFilter() {
      fTypeList = new AudioTypeList();
    }    

        @Override public final boolean include(final Entry<? extends Object, ? extends Object> entry) {
            final String audioType = ((String)entry.getValue(1));
            return fTypeList.contains(audioType);
        }
}