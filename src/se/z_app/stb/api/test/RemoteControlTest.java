package se.z_app.stb.api.test;

import java.io.File;
import java.util.Random;

import junit.framework.TestCase;
import se.z_app.httpserver.ZmoteHTTPD;
import se.z_app.stb.STB;
import se.z_app.stb.api.RemoteControl;
import se.z_app.stb.api.RemoteControl.Button;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.HTTPRequestHandlerTestContainer;
import se.z_app.test_utils.STBFactory;

public class RemoteControlTest extends TestCase {

	public int sleepTime = 200;

	ZmoteHTTPD httpd;
	HTTPRequestHandlerTestContainer container;
	STB stb;
	
	RemoteControl cmd;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		int port = 3000 + (int)(Math.random()*7000);
		httpd = new ZmoteHTTPD(port, new File(".").getAbsoluteFile());
		stb = STBFactory.getLocal(port);
		
		STBContainer.instance().setActiveSTB(stb);		
		cmd = RemoteControl.instance();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		httpd.stop();
	}

	public void testSendText() {
		container = new HTTPRequestHandlerTestContainer("/cgi-bin/writepipe_text");
		httpd.addHandler(container);
		
		for(int i = 0; i < 10; i++){
			Random r = new Random();
			String token = Long.toString(Math.abs(r.nextLong()), 36);
			
			cmd.sendText(token);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			String returnedPost = (String)container.getParms().get("raw");
			
			assertTrue(container.getMethod().equals("POST"));
			assertTrue(returnedPost.equals(token));
		}
	}

	public void testSendButton() {
		container = new HTTPRequestHandlerTestContainer("/cgi-bin/writepipe_key");
		httpd.addHandler(container);
		
		for(Button b : Button.values()){
			cmd.sendButton(b);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			String returnedPost = (String)container.getParms().get("raw");
			assertTrue(container.getMethod().equals("POST"));
			
			String button = b.toString().toLowerCase();
			
			//Log.i("ZmoteTestLog", "Button sent :" + button);
			//Log.i("ZmoteTestLog", "  recived :" + returnedPost);
			
			if(button.startsWith("p"))
				assertTrue(returnedPost.equals(button));
			else{
				
				assertTrue(returnedPost.equals("p" + button));
			}
		}	
	}

	public void testLaunch() {
		container = new HTTPRequestHandlerTestContainer("/mdio/launchurl");
		httpd.addHandler(container);
		
		for(int i = 0; i < 10; i++){
			Random r = new Random();
			String token = Long.toString(Math.abs(r.nextLong()), 36);
			
			cmd.launch(token);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			String returnedPost = (String)container.getParms().get("url");
			
			assertTrue(container.getMethod().equals("GET"));
			assertTrue(returnedPost.equals(token));
		}
	}

}
