package carpentern.noteTaker.router;

import carpentern.noteTaker.file.FileIO;
import carpentern.noteTaker.file.FileSystem;
import carpentern.noteTaker.handler.NoteReaderHandler;
import carpentern.noteTaker.response.ResponseBuilder;
import carpentern.coreServer.handler.Handler;
import java.util.HashMap;

public class RouteDictionary {
  private FileIO fileIO;
  private FileSystem fileSystem;
  private ResponseBuilder responseBuilder;
  HashMap<String, Handler> routes = new HashMap<>();

  public RouteDictionary(FileIO fileIO, FileSystem fileSystem, ResponseBuilder responseBuilder) {
    this.fileIO = fileIO;
    this.fileSystem = fileSystem;
    this.responseBuilder = responseBuilder;
    loadFileRoutes();
  }

  public HashMap<String, Handler> getRoutes() {
    return routes;
  }

  public void addRoute(String file) {
    routes.put("/" + file, new NoteReaderHandler(fileIO, responseBuilder));
  }

  private void loadFileRoutes() {
    String rootPath = findRootPath();
    System.out.println(rootPath);
    String[] files = fileSystem.list(rootPath);
    for (String file : files) {
      addRoute(file);
    }
  }

  private String findRootPath() {
    return fileSystem.getFileAbsolutePath(fileIO.getRootDirectory());
  }

}