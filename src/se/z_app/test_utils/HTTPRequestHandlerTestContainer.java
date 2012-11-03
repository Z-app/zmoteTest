package se.z_app.test_utils;

import java.util.Properties;

import se.z_app.httpserver.NanoHTTPD.Response;
import se.z_app.httpserver.ZmoteHTTPD;
import se.z_app.httpserver.ZmoteHTTPDRequestHandler;

public class HTTPRequestHandlerTestContainer implements ZmoteHTTPDRequestHandler{
	String uri;
	String method;
	Properties header;
	Properties parms;
	Properties files;

	
	public HTTPRequestHandlerTestContainer(String uri){
		this.uri = uri;
	}
	
	@Override
	public String getURI() {
		return uri;
	}

	@Override
	public Response serve(String uri, String method, Properties header, Properties parms, Properties files, ZmoteHTTPD httpd) {
		
		this.method = method;
		this.header = header;
		this.parms = parms;
		this.files = files;
		return httpd.new Response(httpd.HTTP_OK , httpd.MIME_PLAINTEXT, "");
	}
	
	@Override
	public ZmoteHTTPDRequestHandler clone(){
		return this;
	}


	public String getMethod() {
		return method;
	}

	public Properties getHeader() {
		return header;
	}

	public Properties getParms() {
		return parms;
	}

	public Properties getFiles() {
		return files;
	}
	

}
