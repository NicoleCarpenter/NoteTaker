package carpentern.noteTaker.file;

import java.io.File;

public interface FileIO {
  byte[] getFileContents(String file);
  void writeToFile(String filePath, String content);
  File getRootDirectory();
}