package carpentern.noteTaker.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;

public class HttpFileIO implements FileIO {
  private File rootDirectory;

  public HttpFileIO(File rootDirectory) {
    this.rootDirectory = rootDirectory;
  }

  @Override
  public byte[] getFileContents(String filePath) {
    File file = new File(filePath);
    FileInputStream fileInputStream;
    byte[] fileContent = new byte[getFileLength(file)];
    try {
      fileInputStream = new FileInputStream(file);
      fileInputStream.read(fileContent);
      fileInputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileContent;
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

  private int getFileLength(File file) {
    return (int) file.length();
  }

}