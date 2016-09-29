package carpentern.noteTaker.handler;

import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;
import carpentern.coreServer.response.ResponseBuilder;
import java.util.HashMap;

public class MethodNotAllowedHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public MethodNotAllowedHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = new String("Invalid method.\n" +
                                  "To create a new note, use POST\n" +
                                  "To read an existing note, use GET\n\n").getBytes();
    responseBuilder.buildMethodNotAllowedResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}