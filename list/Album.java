package list;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.Arrays;

import mediadata.MediaData;

public final class Album extends ArrayList<MediaData> {
	
    private static final long serialVersionUID = 1110843633306264167L;
	private String title;
    
	public Album(String aSetTitle)    {
        super();
    	this.title = aSetTitle;
    }

        public final void addMedia(MediaData aAddToAlbum)  throws ArrayIndexOutOfBoundsException   {
            add(aAddToAlbum);
        }
    
        public final void deleteMedia(int aIndexOfDeletingMedia) throws ArrayIndexOutOfBoundsException   {
            remove(aIndexOfDeletingMedia);
        }
        
        public void deleteMedia(int[] aIndexsOfDeletingMedia)    {
        	Arrays.sort(aIndexsOfDeletingMedia);    	
            for(int i = aIndexsOfDeletingMedia.length - 1; i >= 0; i--)  {
        	    	   remove(aIndexsOfDeletingMedia[i]);
            }    	   
        }
    
        public final void updateAlbum(MediaData aUpdateMedia, int aIndexOfMediaUpdate) throws ArrayIndexOutOfBoundsException    {
            set(aIndexOfMediaUpdate, aUpdateMedia);
        }
        
        public final MediaData getMedia(int aIndex) throws ArrayIndexOutOfBoundsException    {
        	return get(aIndex);    	
        }
        
        public final void setSortModeMedia(String aSortModeText)    {
        	for(int i = 0; i < size(); i++)    	{
        		MediaData mediaDataSetSort = getMedia(i);
        		mediaDataSetSort.setSortMode(aSortModeText);
        	}
        }
        
        public final void store(DataOutputStream aDos) throws IOException    {
            final int numItemsStore = size();
                            
            aDos.writeUTF(title);
            aDos.writeInt(numItemsStore);
            
            for(int s = 0; s < numItemsStore; s++)        {
                ((MediaData)(get(s))).store(aDos);
            }
        }
    
        public final void load(DataInputStream aDis) throws IOException    {
            title = aDis.readUTF();
            final int numItemsLoad = aDis.readInt();
            
            if(numItemsLoad > 0)        {
                for(int i = 0; i < numItemsLoad; i++)            {
                   add(new MediaData(aDis));
                }
            }
        }
        
        public final String getTitle()    {    	
        	return this.title;
        }
}