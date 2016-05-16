package filter.file;
import javax.swing.filechooser.FileFilter; 
import java.io.File;

public final class AlbumFilter extends FileFilter   {
  public boolean accept (File aAlbumFileName)     {
    return aAlbumFileName.getName().toLowerCase().endsWith (".dat") || aAlbumFileName.isDirectory ();
  }
  
  public final String getDescription()  {
	  return "Date File (*.dat)";
  }
}