package carpentern.noteTaker.router;

import carpentern.noteTaker.file.FileIO;
import carpentern.noteTaker.file.FileSystem;
import carpentern.noteTaker.handler.NoteReaderHandler;
import carpentern.noteTaker.util.HttpMethods;

import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.response.ResponseBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RouteDictionary {
  HashMap<String, Handler> routes = new HashMap<>();
  FileIO fileIO;
  FileSystem fileSystem;
  ResponseBuilder responseBuilder;

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
    routes.put("/" + file, new NoteReaderHandler(fileIO, fileSystem, responseBuilder));
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