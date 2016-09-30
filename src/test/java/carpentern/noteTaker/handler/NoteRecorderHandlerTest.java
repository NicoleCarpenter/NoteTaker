import carpentern.noteTaker.handler.NoteRecorderHandler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import java.util.HashMap;

public class NoteRecorderHandlerTest extends junit.framework.TestCase {
  private NoteRecorderHandler handler;
  private MockHttpFileIO fileIO;
  private MockHttpFileSystem fileSystem;
  private MockHttpResponseBuilder responseBuilder;
  private HttpRequest request;
  private HttpResponse response;
  private Formatter formatter;
  private String responseBody;
  private String uri;
  private HashMap<String, String> testHeaders;


  protected void setUp() {
    responseBuilder = new MockHttpResponseBuilder();
    fileSystem = new MockHttpFileSystem();
    fileSystem.stubGetFileAbsolutePath("/");
    fileSystem.stubGetFileName("root");
    fileIO = new MockHttpFileIO();
    handler = new NoteRecorderHandler(fileIO, fileSystem, responseBuilder);
  }

  private HttpResponse testHandlerResponse(String uri, String responseBody) {
    fileIO.stubResponseBody(responseBody);
    request = new HttpRequest("POST", uri, new HashMap<>(), "HTTP/1.1", new HashMap<String, String>(), "");
    return handler.handleRoute(request);
  }

  public void testHandleRoutePostResponse() {
    responseBody = "";
    uri = "/";

    response = testHandlerResponse(uri, responseBody);

    assertTrue(fileIO.getRootDirectoryCalled);
    assertTrue(fileIO.writeToFileCalled);
    assertTrue(responseBuilder.buildOkResponseCalled);
    assertEquals(new String(responseBuilder.setBodyCalledWith), responseBody);
  }

}