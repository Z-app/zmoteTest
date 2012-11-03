package se.z_app.stb.api.test;

import java.io.File;

import se.z_app.httpserver.ZmoteHTTPD;
import se.z_app.stb.STB;
import se.z_app.stb.api.RCProxy;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.HTTPRequestHandlerTestContainer;
import se.z_app.test_utils.STBFactory;
import android.test.AndroidTestCase;

/**
 * 
 * @author Viktor Håkansson, Leonard Jansson
 *
 */

public class RCProxyTestInt extends AndroidTestCase {
	public int sleepTime = 500;
	ZmoteHTTPD httpd;
	HTTPRequestHandlerTestContainer container;
	STB stb;

	RCProxy cmd;

	public void setUp() throws Exception {
//Creating a random port.
		int port = 3000 + (int)(Math.random()*7000);
// 
		httpd = new ZmoteHTTPD(port, new File(".").getAbsoluteFile());
// Setting the stb to one generated one.
		stb = STBFactory.getLocal(port);
		STBContainer.instance().setActiveSTB(stb);	
		cmd = RCProxy.instance();	
		container = new HTTPRequestHandlerTestContainer("/cgi-bin/writepipe_key");
		httpd.addHandler(container);
	}

	public void tearDown() throws Exception {
		super.tearDown();
		httpd.stop();
	}

	public void testUp() { 
// Switching channel through the RCProxy
		cmd.up();	
//time delay so that all the actions have time to be performed
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}
// getting the content in the HTTP
		String returnedPost = (String)container.getParms().get("raw");
		assertEquals(container.getMethod(),"POST");
		assertEquals(returnedPost,"pup");
	}
	public void testDown() { 
		cmd.down();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals("pdown",returnedPost);
	}
	public void testRight() { 
		cmd.right();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals(returnedPost,"pright");
	}
	public void testLeft() { 
		cmd.left();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals(returnedPost,"pleft");
	}
	public void testOk() { 
		cmd.ok();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals(returnedPost,"pok");
	}
	public void testBack() { 
		cmd.back();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals("pback",returnedPost);
	}
	public void testMute() { 
		cmd.mute();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals("pmute", returnedPost);
	}
	public void testInfo() { 
		cmd.info();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals("pinfo", returnedPost);
	}
	public void testMenu() { 
		cmd.menu();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals("pmenu", returnedPost);
	}
	public void testExit() { 
		cmd.exit();	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}

		String returnedPost = (String)container.getParms().get("raw");

		assertEquals(container.getMethod(),"POST");
		assertEquals("pexit", returnedPost);
	}
}
