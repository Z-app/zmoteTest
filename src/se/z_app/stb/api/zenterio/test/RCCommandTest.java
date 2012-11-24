package se.z_app.stb.api.zenterio.test;


import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import android.util.Log;


import se.z_app.httpserver.ZmoteHTTPD;
import se.z_app.stb.STB;
import se.z_app.stb.WebTVItem;
import se.z_app.stb.api.RemoteControl.Button;
import se.z_app.stb.api.zenterio.RCCommand;
import se.z_app.test_utils.HTTPRequestHandlerTestContainer;
import se.z_app.test_utils.STBFactory;

import junit.framework.TestCase;
/**
 * 
 * @author Rasmus Holm
 *
 */

public class RCCommandTest extends TestCase {

	public int sleepTime = 2000;

	ZmoteHTTPD httpd;
	HTTPRequestHandlerTestContainer container;
	STB stb;
	
	RCCommand cmd;

	
	
	protected void setUp() throws Exception {
		super.setUp();
		int port = 3000 + (int)(Math.random()*7000);
		httpd = new ZmoteHTTPD(port, new File(".").getAbsoluteFile());
		stb = STBFactory.getLocal(port);
		cmd = new RCCommand(stb.getIP());
		
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

	public void testPlayWebTV() {
		container = new HTTPRequestHandlerTestContainer("/mdio/webtv/play");
		httpd.addHandler(container);
		
		for(int i = 0; i < 10; i++){
			Random r = new Random();
			String token = Long.toString(Math.abs(r.nextLong()), 36);
			
			WebTVItem item = new WebTVItem();
			item.setId(token);
			
			cmd.playWebTV(item);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			String returnedPost = (String)container.getParms().get("url");
			
			assertTrue(container.getMethod().equals("GET"));
			assertTrue(returnedPost.equals(token));
		}
	}

	public void testQueueWebTV() {
		container = new HTTPRequestHandlerTestContainer("/mdio/webtv/play");
		httpd.addHandler(container);
		
		for(int i = 0; i < 10; i++){
			Random r = new Random();
			String token = Long.toString(Math.abs(r.nextLong()), 36);
			
			WebTVItem item = new WebTVItem();
			item.setId(token);
			
			cmd.queueWebTV(item);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			//Log.i("ZmoteTestLog", container.getParms().toString());
			String postUrl = (String)container.getParms().get("url");
			String postQueue = (String)container.getParms().get("queue");
			
			assertTrue(container.getMethod().equals("GET"));
			assertTrue(postUrl.equals(token));
			assertTrue(postQueue.equals("1"));
		}
	}

	public void testFacebookAuth() {
		container = new HTTPRequestHandlerTestContainer("/mdio/facebook");
		httpd.addHandler(container);
		
		for(int i = 0; i < 10; i++){
			Random r = new Random();
			String accesstoken = Long.toString(Math.abs(r.nextLong()), 36);
			r = new Random();
			String expires = Long.toString(Math.abs(r.nextLong()), 36);
			r = new Random();
			String uid = Long.toString(Math.abs(r.nextLong()), 36);
			
						
			cmd.facebookAuth(accesstoken, expires, uid);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			//Log.i("ZmoteTestLog", container.getParms().toString());
			String postAccesstoken = (String)container.getParms().get("accesstoken");
			String postExpires = (String)container.getParms().get("expires");
			String postUid = (String)container.getParms().get("uid");
			
			assertTrue(container.getMethod().equals("GET"));
			assertTrue(postAccesstoken.equals(accesstoken));
			assertTrue(postExpires.equals(expires));
			assertTrue(postUid.equals(uid));
		}
	}

	public void testRawPost() {
		for(int i = 0; i < 10; i++){
			Random r = new Random();
			String uri = "/"+Long.toString(Math.abs(r.nextLong()), 36);
			
			container = new HTTPRequestHandlerTestContainer(uri);
			httpd.addHandler(container);
			
			r = new Random();
			String paramName = Long.toString(Math.abs(r.nextLong()), 36);
			r = new Random();
			String param = Long.toString(Math.abs(r.nextLong()), 36);
			String rawPost = Long.toString(Math.abs(r.nextLong()), 36);
						
			cmd.rawPost(rawPost, uri+"?"+paramName+"="+param);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			assertTrue(container.getMethod().equals("POST"));
			assertTrue(container.getParms().containsKey(paramName));
			assertTrue(container.getParms().get(paramName).equals(param));
			assertTrue(container.getParms().containsKey("raw"));
			assertTrue(container.getParms().get("raw").equals(rawPost));
				
			httpd.removeHandler(container);
		}
	}

	public void testRawGet() {
				
		for(int i = 0; i < 10; i++){
			Random r = new Random();
			String uri = "/"+Long.toString(Math.abs(r.nextLong()), 36);
			
			container = new HTTPRequestHandlerTestContainer(uri);
			httpd.addHandler(container);
			
			r = new Random();
			String paramName = Long.toString(Math.abs(r.nextLong()), 36);
			r = new Random();
			String param = Long.toString(Math.abs(r.nextLong()), 36);
			
						
			cmd.rawGet(uri+"?"+paramName+"="+param);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			assertTrue(container.getMethod().equals("GET"));
			assertTrue(container.getParms().containsKey(paramName));
			assertTrue(container.getParms().get(paramName).equals(param));
				
			httpd.removeHandler(container);
		}
	}

}
