package filter.file;
import javax.swing.filechooser.FileFilter; 
import java.io.File;
import java.util.Arrays;

public final class ImageFilter extends FileFilter   {	
  private final String[] fOkFileExtensions = new String[] {".jpg", ".gif", ".png", ".bmp", ".wbmp"};  
	  	
  @Override
  public final boolean accept(File aImageFileName)  {
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
  public final String getDescription()    {
	  return Arrays.toString(fOkFileExtensions);
  }  
} 