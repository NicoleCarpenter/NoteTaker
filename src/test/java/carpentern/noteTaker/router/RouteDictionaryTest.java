import carpentern.noteTaker.file.HttpFileIO;
import carpentern.noteTaker.file.HttpFileSystem;
import carpentern.noteTaker.router.RouteDictionary;
import carpentern.noteTaker.util.HttpMethods;
import carpentern.coreServer.handler.Handler;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class RouteDictionaryTest extends junit.framework.TestCase {
  RouteDictionary routeDict;

  protected void setUp() {
    String rootPath = "/Users/foo/Desktop/coding/java/applications/NoteTaker/src/test/java/carpentern/noteTaker/testFiles";
    File testRoot = new File(rootPath);
    HttpFileSystem fileSystem = new HttpFileSystem();
    HttpFileIO fileIO = new HttpFileIO(testRoot, fileSystem);
    MockHttpResponseBuilder responseBuilder = new MockHttpResponseBuilder();
    routeDict = new RouteDictionary(fileIO, fileSystem, responseBuilder);
  }

  public void testAddRoute() {
    routeDict.addRoute("index");
    HashMap<String, Handler> routes = routeDict.getRoutes();
    assertTrue(routes.containsKey("/index") && routes.get("/index") != null);
  }

}