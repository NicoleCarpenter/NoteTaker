import carpentern.noteTaker.handler.NoteReaderHandler;
import carpentern.noteTaker.file.HttpFileIO;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import java.io.File;
import java.util.HashMap;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class NoteReaderHandlerTest extends junit.framework.TestCase {
  private NoteReaderHandler handler;
  private MockNoteTakerResponseBuilder responseBuilder;
  private HttpFileIO fileIO;

  protected void setUp() {
    String rootPath = "/Users/foo/Desktop/coding/java/applications/NoteTaker/src/test/java/carpentern/noteTaker/testFiles";
    File testRoot = new File(rootPath);
    responseBuilder = new MockNoteTakerResponseBuilder();
    fileIO = new HttpFileIO(testRoot);
    handler = new NoteReaderHandler(fileIO, responseBuilder);
  }

  public void testHandleRouteGetResponse() {
    HttpRequest request = new HttpRequest("GET", "/file1.txt", new HashMap<>(), "HTTP/1.1", new HashMap<>(), "");
    handler.handleRoute(request);

    assertTrue(responseBuilder.buildOkResponseCalled);
    assertTrue(responseBuilder.setBodyCalled);
    assertEquals("file1 contents", new String(responseBuilder.setBodyCalledWith));
  }
}