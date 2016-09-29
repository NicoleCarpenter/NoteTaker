package carpentern.noteTaker.file;

import carpentern.coreServer.request.HttpRequest;
import java.io.File;

public interface FileIO {
  public abstract byte[] getFileContents(String file);
  public abstract void writeToFile(String filePath, String content);
  public abstract File getRootDirectory();
}