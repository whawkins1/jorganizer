package list;

import java.util.ArrayList;

public class AudioTypeList extends ArrayList<String> {
    private static final long serialVersionUID = 1L;

    public AudioTypeList() {
        super(11);
        
        add("MP3");
        add("WAV");
        add("AIFF");
        add("AIF");
        add("WMA");
        add("OGG");
        add("OGM");
        add("DTS");
        add("AC3");
        add("FLAC");
        add("VOC");
    }
}