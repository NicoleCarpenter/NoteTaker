import carpentern.noteTaker.handler.NoteReaderHandler;
import carpentern.noteTaker.file.HttpFileIO;
import carpentern.noteTaker.file.HttpFileSystem;
import carpentern.noteTaker.router.RouteDictionary;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import java.io.File;
import java.util.HashMap;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class NoteReaderHandlerTest extends junit.framework.TestCase {
  private NoteReaderHandler handler;
  private HttpFileIO fileIO;
  private HttpFileSystem fileSystem;
  private MockHttpResponseBuilder responseBuilder;
  private RouteDictionary routeDict;
  private HttpRequest request;
  private HttpResponse response;
  private Formatter formatter;
  private String responseBody;
  private String method;
  private HashMap<String, String> testHeaders;

  protected void setUp() {
    String rootPath = "/Users/foo/Desktop/coding/java/applications/NoteTaker/src/test/java/carpentern/noteTaker/testFiles";
    File testRoot = new File(rootPath);
    responseBuilder = new MockHttpResponseBuilder();
    fileSystem = new HttpFileSystem();
    fileIO = new HttpFileIO(testRoot, fileSystem);
    handler = new NoteReaderHandler(fileIO, fileSystem, responseBuilder);
    routeDict = new RouteDictionary(fileIO, fileSystem, responseBuilder);
  }

  private HttpResponse testHandlerResponse(String responseBody, String method) {
    HttpRequest request = new HttpRequest(method, "/file1.txt", new HashMap<>(), "HTTP/1.1", new HashMap<String, String>(), "");
    return handler.handleRoute(request);
  }

  public void testHandleRouteGetResponse() {
    method = "GET";

    response = testHandlerResponse(responseBody, method);
    assertTrue(responseBuilder.buildOkResponseCalled);
    assertEquals("file1 contents", new String(responseBuilder.setBodyCalledWith));
  }
}