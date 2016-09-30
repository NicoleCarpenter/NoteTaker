package carpentern.noteTaker.handler;

import carpentern.noteTaker.file.FileIO;
import carpentern.noteTaker.file.FileSystem;
import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import carpentern.coreServer.response.ResponseBuilder;
import java.io.File;

public class NoteReaderHandler implements Handler {
  private FileIO fileIO;
  private FileSystem fileSystem;
  private ResponseBuilder responseBuilder;

  public NoteReaderHandler(FileIO fileIO, FileSystem fileSystem, ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
    this.fileIO = fileIO;
    this.fileSystem = fileSystem;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    String path = findPath(request);
    File file = new File(path);
    
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