package carpentern.noteTaker.handler;

import carpentern.noteTaker.file.FileIO;
import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import carpentern.coreServer.response.ResponseBuilder;
import java.io.File;

public class NoteRecorderHandler implements Handler {
  private FileIO fileIO;
  private ResponseBuilder responseBuilder;

  public NoteRecorderHandler(FileIO fileIO, ResponseBuilder responseBuilder) {
    this.fileIO = fileIO;
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    String path = findPath(request);
    byte[] emptyBody = new byte[0];

    fileIO.writeToFile(path, request.getBody());

    responseBuilder.buildOkResponse();
    responseBuilder.setBody(emptyBody);
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