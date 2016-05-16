package list;

import java.util.ArrayList;

public final class ImageTypeList extends ArrayList<String> {
    private static final long serialVersionUID = 1L;

    public ImageTypeList() {
        super(5);
        
        add("JPG");
        add("PNG");
        add("GIF");
        add("BMP");
        add("WBMP");
    }
}