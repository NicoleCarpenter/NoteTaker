import carpentern.noteTaker.file.FileIO;
import java.io.File;

class MockHttpFileIO implements FileIO {
  private String stubbedResponseBody;
  Boolean getRootDirectoryCalled = false;
  Boolean writeToFileCalled = false;

  public byte[] getFileContents(String file) {
    return stubbedResponseBody.getBytes();
  }

  public void writeToFile(String filePath, String content) {
    writeToFileCalled = true;
  }

  public File getRootDirectory() {
    getRootDirectoryCalled = true;
    return new File("resources");
  }

  void stubResponseBody(String responseBody) {
    stubbedResponseBody = responseBody;
  }

}