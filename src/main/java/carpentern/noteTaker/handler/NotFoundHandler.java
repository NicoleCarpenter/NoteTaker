package carpentern.noteTaker.handler;

import carpentern.noteTaker.file.FileIO;
import carpentern.noteTaker.file.FileSystem;
import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import carpentern.coreServer.response.ResponseBuilder;

public class NotFoundHandler implements Handler {
  private FileIO fileIO;
  private FileSystem fileSystem;
  private ResponseBuilder responseBuilder;

  public NotFoundHandler(FileIO fileIO, FileSystem fileSystem, ResponseBuilder responseBuilder) {
    this.fileIO = fileIO;
    this.fileSystem = fileSystem;
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    String availableNotes = getNoteList();
    byte[] bodyMessage = ("Sorry, that file does not exist, try one of the following:\n\n" + availableNotes).getBytes();
    responseBuilder.buildNotFoundResponse();
    responseBuilder.setBody(bodyMessage);
    return responseBuilder.getResponse();
  }

  private String getNoteList() {
    String rootPath = findRootPath();
    String[] files = fileSystem.list(rootPath);
    StringBuilder builder = new StringBuilder();
    for (String file : files) {
      if (!file.equals(".DS_Store")) {
        builder.append("/");
        builder.append(file);
        builder.append("\n");
      }
    }
    return builder.toString();
  }

  private String findRootPath() {
    return fileSystem.getFileAbsolutePath(fileIO.getRootDirectory());
  }

}