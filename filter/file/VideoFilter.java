package filter.file;
import javax.swing.filechooser.FileFilter; 
import java.io.File;
import java.util.Arrays;

public final class VideoFilter extends FileFilter    {
  private final String[] fOkFileExtensions = new String[] { ".fxm", ".flv", ".m3u8", ".mp4", ".m4a", ".m4v", ".avi", ".mkv", ".divx", ".asf", ".wmv", ".es", 
		                                                   ".ps", ".ts", ".pva", ".mov", ".mxf", ".rm", ".dv", ".voc", ".mpg", ".mpeg", ".webm", ".theora", ".xvid"};  
  @Override
  public final boolean accept(File aImageFileName)    {
	  if(aImageFileName.isDirectory())	  {
		  return true;
	  }
	  
      for(int x = 0; x < fOkFileExtensions.length; x++)      {
    	  if(aImageFileName.getName().toLowerCase().endsWith(fOkFileExtensions[x]))  {
    		  return true;
    	  }
      }
      return false;
  }
  
  @Override
  public final String getDescription()    {
	  return Arrays.toString(fOkFileExtensions);
  }
} 

