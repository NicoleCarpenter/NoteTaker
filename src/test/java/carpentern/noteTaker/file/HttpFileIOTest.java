import carpentern.noteTaker.file.HttpFileIO;
import carpentern.noteTaker.file.FileSystem;
import java.io.File;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class HttpFileIOTest extends junit.framework.TestCase {
  private String testRoot;
  private String applicationRoot;
  private HttpFileIO fileIO;

  protected void setUp() {
    testRoot = "/Users/foo/Desktop/coding/java/applications/NoteTaker/src/test/java/carpentern/noteTaker/testFiles";
    MockHttpFileSystem fileSystem = new MockHttpFileSystem();
    fileIO = new HttpFileIO(new File(testRoot), fileSystem);
  }

  public void testGetFileContents() {
    String filePath = testRoot + "/file1.txt";
    assertEquals("file1 contents", new String(fileIO.getFileContents(filePath)));
  }

  public void testWriteToFile() {
    String filePath = testRoot + "/file2.txt";
    String content = "Hello World";
    fileIO.writeToFile(filePath, content);
    assertEquals("Hello World\n", new String(fileIO.getFileContents(filePath)));
  }

}