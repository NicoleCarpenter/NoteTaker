package carpentern.noteTaker.handler;

import carpentern.noteTaker.file.FileIO;
import carpentern.noteTaker.response.ResponseBuilder;
import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import java.io.File;

public class NoteReaderHandler implements Handler {
  private FileIO fileIO;
  private ResponseBuilder responseBuilder;

  public NoteReaderHandler(FileIO fileIO, ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
    this.fileIO = fileIO;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    String path = findPath(request);
    responseBuilder.buildOkResponse();
    responseBuilder.setBody(fileIO.getFileContents(path));
    return responseBuilder.getResponse();
  }

  private String findPath(HttpRequest request) {
    String uri = request.getUri();
    File rootDirectory = fileIO.getRootDirectory();
    String rootPath = rootDirectory.getAbsolutePath();
    String requestPath = uri.replace(rootDirectory.getName(), "");
    return rootPath + requestPath;
  }
}