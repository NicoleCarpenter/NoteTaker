package carpentern.noteTaker.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

public class HttpFileIO implements FileIO {
  private File rootDirectory;
  private FileSystem fileSystem;
  private FileInputStream fileInputStream = null;
  private FileOutputStream fileOutputStream = null;

  public HttpFileIO(File rootDirectory, FileSystem fileSystem) {
    this.rootDirectory = rootDirectory;
    this.fileSystem = fileSystem;
  }

  @Override
  public byte[] getFileContents(String filePath) {
    File file = new File(filePath);
    byte[] fileContent = new byte[getFileLength(file)];
    try {
      fileInputStream = new FileInputStream(file);
      fileInputStream.read(fileContent);
      fileInputStream.close();
    } catch (FileNotFoundException  e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileContent;
  }

  private int getFileLength(File file) {
    return (int) file.length();
  }

  public void writeToFile(String filePath, String content) {
    try {
      PrintWriter writer = new PrintWriter(filePath, "UTF-8");
      writer.println(content);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public File getRootDirectory() {
    return rootDirectory;
  }

}