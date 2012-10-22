package se.z_app.test_utils;

import se.z_app.httpserver.HTTPRequest;
import se.z_app.httpserver.HTTPRequestHandlerIntf;
import se.z_app.httpserver.HTTPResponse;

public class HTTPRequestHandlerDummy implements HTTPRequestHandlerIntf {

	String uri;
	HTTPRequest httpRequest;
	public HTTPRequestHandlerDummy(String uri){
		this.uri = uri;
	}
	
	@Override
	public String getTargetUri() {
		return uri;
	}

	@Override
	public HTTPResponse handle(HTTPRequest request) {
		httpRequest = request;
		
		return new HTTPResponse();
	}

	
	public HTTPRequest getHttpRequest() {
		return httpRequest;
	}

	public Object clone(){
		return this;
	}
}
