package carpentern.noteTaker.file;

import java.io.File;

public interface FileSystem {
  String[] list(String directory);
  String getFileAbsolutePath(File file);
}