package carpentern.noteTaker.router;

import carpentern.noteTaker.handler.*;
import carpentern.noteTaker.file.FileIO;
import carpentern.noteTaker.file.FileSystem;
import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.ResponseBuilder;
import carpentern.coreServer.router.Router;

public class NoteTakerRouter implements Router {
  private FileSystem fileSystem;
  private FileIO fileIO;
  private ResponseBuilder responseBuilder;
  private RouteDictionary routeDict;

  public NoteTakerRouter(FileSystem fileSystem, FileIO fileIO, ResponseBuilder responseBuilder, RouteDictionary routeDict) {
    this.fileSystem = fileSystem;
    this.fileIO = fileIO;
    this.responseBuilder = responseBuilder;
    this.routeDict = routeDict;
  }

  @Override
  public Handler getRoute(HttpRequest request) {
    String method = request.getMethod();
    String uri = request.getUri();
    Handler handler = routeDict.routes.get(uri);

    if (isFileFound(handler)) {
      return handler;
    } else if (isWritingNewFile(handler, method)) {
      return new NoteRecorderHandler(fileIO, responseBuilder);
    } else if (isGetRequest(method)) {
      return new NotFoundHandler(fileIO, fileSystem, responseBuilder);
    } else {
      return new MethodNotAllowedHandler(responseBuilder);
    }
  }

  private boolean isFileFound(Handler handler) {
    return handler != null;
  }

  private boolean isWritingNewFile(Handler handler, String method) {
    return !isFileFound(handler) && isPostRequest(method);
  }

  private boolean isPostRequest(String method) {
    return method.equals("POST");
  }

  private boolean isGetRequest(String method) {
    return method.equals("GET");
  }

}