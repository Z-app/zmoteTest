package se.z_app.stb.api.zenterio.test;


import java.net.InetAddress;
import java.net.UnknownHostException;


import se.z_app.httpserver.HTTPServer;
import se.z_app.httpserver.HTTPSessionHandler;
import se.z_app.stb.api.RemoteControl.Button;
import se.z_app.stb.api.zenterio.RCCommand;
import se.z_app.test_utils.HTTPRequestHandlerDummy;
import junit.framework.TestCase;

public class RCCommandTest extends TestCase {

	HTTPServer server;
	HTTPSessionHandler session;
	RCCommand cmd;
	HTTPRequestHandlerDummy request;
	
	
	protected void setUp() throws Exception {
		
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSendText() {
		fail("Not yet implemented");
	}

	public void testSendButton() {
	
		server = new HTTPServer();
		try {
			server.setLocalAddress(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		session = server.getHandler();
		server.start();
		
		cmd = new RCCommand("127.0.0.1");
		
		
		request = new HTTPRequestHandlerDummy("/cgi-bin/writepipe_key");
		session.addHandler(request);
		
		cmd.sendButton(Button.UP);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		assertTrue(request.getHttpRequest().getRawArguments().contains("pup"));
		
		
	}

	public void testLaunch() {
		fail("Not yet implemented");
	}

	public void testPlayWebTV() {
		fail("Not yet implemented");
	}

	public void testQueueWebTV() {
		fail("Not yet implemented");
	}

	public void testFacebookAuth() {
		fail("Not yet implemented");
	}

	public void testRawPost() {
		fail("Not yet implemented");
	}

	public void testRawGet() {
		fail("Not yet implemented");
	}

}
