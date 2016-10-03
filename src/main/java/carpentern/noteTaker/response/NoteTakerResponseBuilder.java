package carpentern.noteTaker.response;

import carpentern.noteTaker.response.ResponseBuilder;
import carpentern.coreServer.response.HttpResponse;

public class NoteTakerResponseBuilder implements ResponseBuilder {
  private HttpResponse response;

  public NoteTakerResponseBuilder() {
    response = new HttpResponse();
  }

  @Override
  public HttpResponse getResponse() {
    return response;
  }

  public void setStatusCode(String code) {
    response.setStatusCode(code);
  }

  public void setStatusMessage(String message) {
    response.setStatusMessage(message);
  }

  public void setDefaultHeaders() {
    response.setHeader("Server", "Nicole's HTTP server");
  }

  public void setHeader(String key, String value) {
    response.setHeader(key, value);
  }

  public void setBody(byte[] bodyContent) {
    response.setBody(bodyContent);
  }

  public void buildOkResponse() {
    setStatusCode("200");
    setStatusMessage("OK");
    setDefaultHeaders();
  }

  public void buildMethodNotAllowedResponse() {
    setStatusCode("405");
    setStatusMessage("Method not allowed");
    setDefaultHeaders();
  }

  public void buildNotFoundResponse() {
    setStatusCode("404");
    setStatusMessage("Not Found");
    setDefaultHeaders();
  }

}