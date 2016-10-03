package carpentern.noteTaker.handler;

import carpentern.noteTaker.response.ResponseBuilder;
import carpentern.coreServer.handler.Handler;
import carpentern.coreServer.request.HttpRequest;
import carpentern.coreServer.response.HttpResponse;

public class MethodNotAllowedHandler implements Handler {
  private ResponseBuilder responseBuilder;

  public MethodNotAllowedHandler(ResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  public HttpResponse handleRoute(HttpRequest request) {
    byte[] emptyBody = "Invalid method.\nTo create a new note, use POST\nTo read an existing note, use GET\n\n".getBytes();
    responseBuilder.buildMethodNotAllowedResponse();
    responseBuilder.setBody(emptyBody);
    return responseBuilder.getResponse();
  }
}