import carpentern.noteTaker.handler.NoteRecorderHandler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import java.util.HashMap;

public class NoteRecorderHandlerTest extends junit.framework.TestCase {
  private NoteRecorderHandler handler;
  private MockHttpFileIO fileIO;
  private MockNoteTakerResponseBuilder responseBuilder;

  protected void setUp() {
    responseBuilder = new MockNoteTakerResponseBuilder();
    fileIO = new MockHttpFileIO();
    handler = new NoteRecorderHandler(fileIO, responseBuilder);
  }

  private HttpResponse testHandlerResponse(String uri, String responseBody) {
    fileIO.stubResponseBody(responseBody);
    HttpRequest request = new HttpRequest("POST", uri, new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    return handler.handleRoute(request);
  }

  public void testHandleRoutePostResponse() {
    String responseBody = "";
    String uri = "/";

    testHandlerResponse(uri, responseBody);

    assertTrue(fileIO.getRootDirectoryCalled);
    assertTrue(fileIO.writeToFileCalled);
    assertTrue(responseBuilder.buildOkResponseCalled);
    assertTrue(responseBuilder.setBodyCalled);
    assertEquals(new String(responseBuilder.setBodyCalledWith), responseBody);
  }

}