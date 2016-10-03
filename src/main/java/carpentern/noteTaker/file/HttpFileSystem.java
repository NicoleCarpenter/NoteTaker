package carpentern.noteTaker.file;

import java.io.File;

public class HttpFileSystem implements FileSystem {

  @Override
  public String[] list(String directory) {
    File d = new File(directory);
    return d.list();
  }

  @Override
  public String getFileAbsolutePath(File file) {
    return file.getAbsolutePath();
  }

}