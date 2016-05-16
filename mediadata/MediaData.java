package mediadata;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.lang.String;

public final class MediaData 
                     implements Comparable<MediaData>    {
	private String fFileName;
	private File fMediaFile;
	private String fFilePath;
	private String fDate;
	private String fDescription;
	private String fMediaType;
	private List<String> fTags;
	private int fTagSize;
	private int fRelevancy;
	private String fCurrentSortMode;
    
	  public  MediaData()    {
	    	super();
	        fFileName = "";
	        fMediaFile = null;
	        fFilePath = "";
	        fDate = "";
	        fDescription = "";
	        fMediaType = "";
	        fTags = new ArrayList<String>();
	        fRelevancy = 0;
	        fCurrentSortMode = "";
	    }
	
	  public  MediaData(DataInputStream dis) throws IOException    {
	    	fTags = new ArrayList<String>();
	    	
	        this.fFileName = dis.readUTF();
	        this.fDate= dis.readUTF();
	        this.fFilePath = dis.readUTF();
	        this.fMediaFile = new File(this.fFilePath);
	        this.fMediaType = dis.readUTF();
	        this.fDescription = dis.readUTF();
	        this.fTagSize = dis.readInt();
	        for(int n = 0; n < fTagSize; n++)
	        {
	          fTags.add(n, dis.readUTF());
	        }
	    }
	    
	    public final void store(DataOutputStream dos) throws IOException    {
	    	dos.writeUTF(this.fFileName);
	    	dos.writeUTF(this.fDate);
	    	dos.writeUTF(this.fFilePath);
	    	dos.writeUTF(this.fMediaType);
	        dos.writeUTF(this.fDescription);
	        dos.writeInt(fTags.size());
	        for(int x = 0; x < fTags.size(); x++)        {
	          dos.writeUTF( (String)fTags.get(x) );
	        }
	    }
	    
	    public final void setFileName(String aFileName)    {
	    	this.fFileName = aFileName;
	    }
	    
	    public final String getFileName()    {
	    	return this.fFileName;
	    }
	    
	    public final void setmediaType(String aMediaType)    {
	    	this.fMediaType = aMediaType;
	    }
	    
	    public final String getMediaType()    {
	    	return this.fMediaType;
	    }
	    
	    public final void setMediaFile(File f)    {
	    	this.fMediaFile = f;
	    }
	    
	    public final File getMediaFile()    {
	    	return this.fMediaFile;
	    }
	    
	    public final void setFilePath(String aMediaPath)    {
	    	this.fFilePath = aMediaPath;    	
	    }
	    
	    public final String getFilePath()    {
	    	return this.fFilePath;
	    }
	    
	    public final void setDate(String fDate)    {
	    	this.fDate = fDate;
	    }
	    
	    public final String getDate()    {
	    	return fDate;
	    }
	    
	    public final List<String> getTags()    {
	    	return fTags;
	    }
	    
	    public final void setDescription(String aDescription)    {
	    	this.fDescription = aDescription;
	    }
	    
	    public final String getDescription()    {
	    	return fDescription;
	    }
	    
	    public final void addTags(List<String> aTagList)    {
	    	fTags = aTagList;
	    }
	    
	    public final void setRelevancy(Integer aComputedRelevancy)    {
	    	fRelevancy = aComputedRelevancy;
	    }
	    
	    public final Integer getRelevancy()    {
	    	return fRelevancy;
	    }
	    
	    public final void setSortMode(String aSortMode)    {
	    	fCurrentSortMode = aSortMode;
	    }
	    
	    @Override
	    public final int compareTo(MediaData aCompareData)    {
	         int calculatedComparison = 0;
	    	
	    	 switch (fCurrentSortMode)    	 {
	    	 case "Sort By Relevancy":
	             calculatedComparison = ( ( (MediaData) aCompareData).getRelevancy() ) - getRelevancy();
	            break;
	    	 case "Sort By File Name":
	    		 calculatedComparison = getFileName().compareToIgnoreCase( aCompareData.getFileName() );
	            break;        
	    	 case "Sort By Type":
	    		 calculatedComparison = getMediaType().compareToIgnoreCase(aCompareData.getMediaType());
	    	 case "Sort By fDate":
	    		 calculatedComparison = getDate().compareToIgnoreCase(aCompareData.getDate());
	            break;
	    	 case "Sort By Tags":
	    		 calculatedComparison = getTags().get(0).compareToIgnoreCase(aCompareData.getTags().get(0));   		
	    		break;
	    	 case  "Sort By fDescription":
	    		 calculatedComparison = getDescription().compareToIgnoreCase(aCompareData.getDescription());
	             break;
	         default:
	        	 calculatedComparison = 0;
	             break;    
	    	 }   
	         return calculatedComparison;
	    }
	}