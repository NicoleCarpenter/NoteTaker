package carpentern.noteTaker;

import carpentern.noteTaker.file.HttpFileSystem;
import carpentern.noteTaker.file.HttpFileIO;
import carpentern.noteTaker.router.NoteTakerRouter;
import carpentern.noteTaker.response.NoteTakerResponseBuilder;
import carpentern.noteTaker.router.RouteDictionary;
import carpentern.noteTaker.parser.ArgumentParser;
import carpentern.coreServer.io.HttpServerOutput;
import carpentern.coreServer.parser.HttpParamParser;
import carpentern.coreServer.request.HttpRequestParser;
import carpentern.coreServer.router.Router;
import carpentern.coreServer.server.HttpServer;
import carpentern.coreServer.socket.HttpServerSocket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.File;

public class Main {

  public static void main(String args[]) {
    ArgumentParser argsParser = new ArgumentParser(args);
    Integer port = argsParser.getPort();
    ServerSocket serverSocket;

    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    HttpServerSocket httpServerSocket = new HttpServerSocket(serverSocket);
    HttpServerOutput httpServerOutput = new HttpServerOutput();
    HttpParamParser httpParamParser = new HttpParamParser();
    HttpRequestParser httpRequestParser = new HttpRequestParser(httpParamParser);

    File rootDirectory = new File(argsParser.getRootDirectory());
    HttpFileSystem fileSystem = new HttpFileSystem();
    HttpFileIO fileIO = new HttpFileIO(rootDirectory);
    NoteTakerResponseBuilder noteTakerResponseBuilder = new NoteTakerResponseBuilder();
    RouteDictionary routeDict = new RouteDictionary(fileIO, fileSystem, noteTakerResponseBuilder);

    Router noteTakerRouter = new NoteTakerRouter(fileSystem, fileIO, noteTakerResponseBuilder, routeDict);

    HttpServer server = new HttpServer(httpServerSocket, httpRequestParser, noteTakerRouter, httpServerOutput);

    server.start();
  }
}