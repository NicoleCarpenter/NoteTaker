import carpentern.noteTaker.handler.MethodNotAllowedHandler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import java.util.HashMap;

public class MethodNotAllowedHandlerTest extends junit.framework.TestCase {
  private MockHttpResponseBuilder responseBuilder;

  protected void setUp() {
    responseBuilder = new MockHttpResponseBuilder();
    MethodNotAllowedHandler handler = new MethodNotAllowedHandler(responseBuilder);
    HttpRequest request = new HttpRequest("mockMethod", "mockUri", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "mockBody");
    handler.handleRoute(request);
  }

  public void testHandleRoute() {
    String responseBody = "Invalid method.\nTo create a new note, use POST\nTo read an existing note, use GET\n\n";

    assertTrue(responseBuilder.buildMethodNotAllowedResponseCalled);
    assertTrue(responseBuilder.setBodyCalled);
    assertEquals(responseBody, new String(responseBuilder.setBodyCalledWith));
  }
}
