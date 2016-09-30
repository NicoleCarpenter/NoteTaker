import carpentern.noteTaker.handler.*;
import carpentern.noteTaker.file.HttpFileIO;
import carpentern.noteTaker.file.HttpFileSystem;
import carpentern.noteTaker.router.NoteTakerRouter;
import carpentern.noteTaker.router.RouteDictionary;

import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponseBuilder;
import java.util.HashMap;
import java.io.File;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class NoteTakerRouterTest extends junit.framework.TestCase {
  private HttpRequest request;
  private NoteTakerRouter router;
  private HttpFileIO fileIO;
  private HttpFileSystem fileSystem;
  private MockHttpResponseBuilder responseBuilder;
  private RouteDictionary routeDict;

  protected void setUp() {
    String rootPath = "/Users/foo/Desktop/coding/java/applications/NoteTaker/src/test/java/carpentern/noteTaker/testFiles";
    File testRoot = new File(rootPath);
    fileSystem = new HttpFileSystem();
    fileIO = new HttpFileIO(testRoot, fileSystem);
    responseBuilder = new MockHttpResponseBuilder();
    routeDict = new RouteDictionary(fileIO, fileSystem, responseBuilder);

    router = new NoteTakerRouter(fileSystem, fileIO, responseBuilder, routeDict);
  }

  public void testGetRouteFileExists() {
    request = new HttpRequest("GET", "/file1.txt", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof NoteReaderHandler);
  }

  public void testGetRouteFileNotFound() {
    request = new HttpRequest("GET", "/nonExistantFile", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof NotFoundHandler);
  }

  public void testGetRoutePost() {
    request = new HttpRequest("POST", "/", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof NoteRecorderHandler);
  }

  public void testGetRouteOptions() {
    request = new HttpRequest("OPTIONS", "/method_options", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof MethodNotAllowedHandler);
  }

  public void testGetRouteHead() {
    request = new HttpRequest("HEAD", "/something", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof MethodNotAllowedHandler);
  }

  public void testGetRoutePut() {
    request = new HttpRequest("PUT", "/file1", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof MethodNotAllowedHandler);
  }

  public void testGetRouteNotAMethod() {
    request = new HttpRequest("BADMETHOD", "/file1", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    Handler handler = router.getRoute(request);
    assertTrue(handler instanceof MethodNotAllowedHandler);
  }

}