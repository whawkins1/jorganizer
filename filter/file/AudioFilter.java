package filter.file;
import javax.swing.filechooser.FileFilter; 
import java.io.File;
import java.util.Arrays;

public final class AudioFilter extends FileFilter     {
  private final String[] fOkFileExtensions = new String[] {".wav", ".mp3", ".aiff", ".aif", ".wma", ".ogg", ".ogm", ".dts", ".aac", ".ac3", ".flac", ".voc",};  
	  	
  @Override
  public final boolean accept(File aImageFileName)     {
	  
	  if(aImageFileName.isDirectory())	  {
		  return true;
	  }
	  
      for(int x = 0; x < fOkFileExtensions.length; x++)      {
    	  if(aImageFileName.getName().toLowerCase().endsWith(fOkFileExtensions[x]))    	  {
    		  return true;
    	  }
      }
      return false;
  }
  
  @Override
  public final String getDescription()  {
	  return Arrays.toString(fOkFileExtensions);
  }
} 

