import carpentern.noteTaker.file.HttpFileIO;
import carpentern.noteTaker.file.HttpFileSystem;
import carpentern.noteTaker.handler.NotFoundHandler;
import carpentern.coreServer.request.HttpRequest;
import java.io.File;
import java.util.HashMap;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class NotFoundHandlerTest extends junit.framework.TestCase {
  private MockHttpResponseBuilder responseBuilder;

  protected void setUp() {
    responseBuilder = new MockHttpResponseBuilder();
    String rootPath = "/Users/foo/Desktop/coding/java/applications/NoteTaker/src/test/java/carpentern/noteTaker/testFiles";
    File testRoot = new File(rootPath);
    HttpFileSystem fileSystem = new HttpFileSystem();
    HttpFileIO fileIO = new HttpFileIO(testRoot);
    NotFoundHandler handler = new NotFoundHandler(fileIO, fileSystem, responseBuilder);
    HttpRequest request = new HttpRequest("GET", "/notfounduri", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    handler.handleRoute(request);
  }

  public void testHandleRoute() {
    String responseBody = "Sorry, that file does not exist, try one of the following:\n\n/file1.txt\n/file2.txt\n/file3.txt\n";

    assertTrue(responseBuilder.setBodyCalled);
    assertTrue(responseBuilder.buildNotFoundResponseCalled);
    assertEquals(responseBody, new String(responseBuilder.setBodyCalledWith));
  }

}