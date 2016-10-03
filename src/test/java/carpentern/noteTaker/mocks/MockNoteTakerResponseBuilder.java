import carpentern.noteTaker.response.ResponseBuilder;
import carpentern.coreServer.response.HttpResponse;

public class MockNoteTakerResponseBuilder implements ResponseBuilder {
  boolean setStatusCodeCalled;
  boolean setStatusMessageCalled;
  boolean setDefaultHeadersCalled;
  boolean setHeaderCalled;
  String setHeaderCalledWith;
  boolean setBodyCalled;
  byte[] setBodyCalledWith;
  boolean getResponseCalled;
  boolean buildOkResponseCalled;
  boolean buildPartialFileResponseCalled;
  boolean buildUnauthorizedResponseCalled;
  boolean buildMethodNotAllowedResponseCalled;
  boolean buildNotFoundResponseCalled;
  boolean buildPatchedContentResponseCalled;
  boolean buildRedirectResponseCalled;
  boolean buildCoffeeResponseCalled;

  public MockNoteTakerResponseBuilder() {
    setStatusCodeCalled = false;
    setStatusMessageCalled = false;
    setDefaultHeadersCalled = false;
    setHeaderCalled = false;
    setBodyCalled = false;
    setBodyCalledWith = new byte[0];
    getResponseCalled = false;
    buildOkResponseCalled = false;
    buildPartialFileResponseCalled = false;
    buildUnauthorizedResponseCalled = false;
    buildMethodNotAllowedResponseCalled = false;
    buildNotFoundResponseCalled = false;
    buildPatchedContentResponseCalled = false;
    buildRedirectResponseCalled = false;
    buildCoffeeResponseCalled = false;
  }

  public void setStatusCode(String code) {
    setStatusCodeCalled = true;
  }

  public void setStatusMessage(String message) {
    setStatusMessageCalled = true;
  }

  public void setDefaultHeaders() {
    setDefaultHeadersCalled = true;
  }

  public void setHeader(String key, String value) {
    setHeaderCalledWith = key + ": " + value;
    setHeaderCalled = true;
  }

  public void setBody(byte[] bodyContent) {
    setBodyCalledWith = bodyContent;
    setBodyCalled = true;
  }

  public HttpResponse getResponse() {
    getResponseCalled = true;
    return new HttpResponse();
  }

  public void buildOkResponse() {
    buildOkResponseCalled = true;
  }
  public void buildMethodNotAllowedResponse() {
    buildMethodNotAllowedResponseCalled = true;
  }

  public void buildNotFoundResponse() {
    buildNotFoundResponseCalled = true;
  }

}
