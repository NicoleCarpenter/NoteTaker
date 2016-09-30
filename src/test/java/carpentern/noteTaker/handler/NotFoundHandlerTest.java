import carpentern.noteTaker.file.HttpFileIO;
import carpentern.noteTaker.file.HttpFileSystem;
import carpentern.noteTaker.handler.NotFoundHandler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import carpentern.coreServer.response.HttpResponseBuilder;
import java.io.File;
import java.util.HashMap;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class NotFoundHandlerTest extends junit.framework.TestCase {
  private Formatter formatter;
  private HttpFileIO fileIO;
  private HttpFileSystem fileSystem;
  private MockHttpResponseBuilder responseBuilder;
  private HttpResponse response;
  private NotFoundHandler handler;

  protected void setUp() {
    formatter = new Formatter();
    String rootPath = "/Users/foo/Desktop/coding/java/applications/NoteTaker/src/test/java/carpentern/noteTaker/testFiles";
    File testRoot = new File(rootPath);
    responseBuilder = new MockHttpResponseBuilder();
    fileSystem = new HttpFileSystem();
    fileIO = new HttpFileIO(testRoot, fileSystem);
    handler = new NotFoundHandler(fileIO, fileSystem, responseBuilder);
    HttpRequest request = new HttpRequest("GET", "/notfounduri", new HashMap<>(), "HTTP/1.1", new HashMap<String, String>(), "");
    response = handler.handleRoute(request);
  }

  public void testHandleRoute() {
    String responseBody = "Sorry, that file does not exist, try one of the following:\n\n/file1.txt\n/file2.txt\n/file3.txt\n";
    HashMap<String, String> testHeaders = new HashMap<>();
    testHeaders.put("Server", "Nicole's HTTP server");

    assertTrue(responseBuilder.buildNotFoundResponseCalled);
    assertEquals(responseBody, new String(responseBuilder.setBodyCalledWith));
  }

}