package carpentern.noteTaker.response;

import carpentern.coreServer.response.HttpResponse;

public interface ResponseBuilder {
  HttpResponse getResponse();
  void setStatusCode(String code);
  void setStatusMessage(String message);
  void setDefaultHeaders();
  void setHeader(String key, String value);
  void setBody(byte[] bodyContent);
  void buildOkResponse();
  void buildMethodNotAllowedResponse();
  void buildNotFoundResponse();
}