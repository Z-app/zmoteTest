package se.z_app.stb.api.test;

import java.io.File;

import junit.framework.TestCase;
import se.z_app.httpserver.ZmoteHTTPD;
import se.z_app.stb.STB;
import se.z_app.stb.WebTVItem;
import se.z_app.stb.WebTVService;
import se.z_app.stb.api.STBContainer;
import se.z_app.stb.api.WebTVCommand;
import se.z_app.stb.api.zenterio.RCCommand;
import se.z_app.stb.api.zenterio.StandardCommand;
import se.z_app.test_utils.HTTPRequestHandlerTestContainer;
import se.z_app.test_utils.STBFactory;
import android.graphics.Bitmap;
import android.util.Log;

public class WebTVCommandTest extends TestCase{

	STB stb;// = STBFactory.getProxy();
	WebTVCommand cmd;// = WebTVCommand.instance();
	StandardCommand std;
	RCCommand RCmd;

	
	public int sleepTime = 200;

	ZmoteHTTPD httpd;
	HTTPRequestHandlerTestContainer container;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		int port = 3000 + (int)(Math.random()*7000);
		httpd = new ZmoteHTTPD(port, new File(".").getAbsoluteFile());
		stb = STBFactory.getProxy();
		
		STBContainer.instance().setActiveSTB(stb);		
		cmd = WebTVCommand.instance();
		
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		httpd.stop();
	}
	
	public void testgetService(){
		container = new HTTPRequestHandlerTestContainer("/mdio/webtv/services");
		httpd.addHandler(container);
		
		std = new StandardCommand(stb.getIP()); // puts ip in StdCmd
		WebTVService[] wts = cmd.getService();
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}
		Log.i("ZmoteTestLog", "Number of WebTVServices   " + wts.length);
		assertTrue(wts.length == 3); // There are tree WebTvServices youtube, spotify, ted 
		
	}
	
	public void testSearch(){
		container = new HTTPRequestHandlerTestContainer("/mdio/webtv/services");
		httpd.addHandler(container);
		
		WebTVService wts = new WebTVService();
		wts.setName("Youtube");
		wts.setID("youtube");
		
		std = new StandardCommand(stb.getIP()); // puts ip in StdCmd
		WebTVItem[] items = cmd.search("cat", wts); //Searches youtube after "cat"
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}
		
		Log.i("ZmoteTestLog", "Number of found webTVItems on cat " + items.length);
		assertTrue(items.length >= 1);
	}
	
	public void testPlayWebTV(){
		container = new HTTPRequestHandlerTestContainer("/mdio/webtv/play");
		httpd.addHandler(container);
		
		STB stbBox; 
		stbBox = STBFactory.get1();
		RCmd = new RCCommand(stbBox.getIP());	
		
		WebTVItem item = new WebTVItem();
		item.setId("youtube");
		
		cmd.play(item);
		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}
		
//		String returnedPost = (String)container.getParms().get("url");
//		//Log.i("ZmoteTestLog", "WebTVItem played  " + returnedPost);
//		assertTrue(container.getMethod().equals("GET"));
//		assertTrue(returnedPost.equals("youtube:media:ctJJrBw7e-c"));
	}
	public void testgeticonwebtvitem(){
		WebTVCommand cmd = WebTVCommand.instance();
		WebTVService wts = new WebTVService();
		wts.setID("youtube:media:lUjDq67nBCA");
		wts.setName("LolCatz Compilation");
		wts.setIconURL("http://i.ytimg.com/vi/lUjDq67nBCA/hqdefault.jpg");
		Bitmap icon;
		icon = cmd.getIcon(wts);

		assertTrue(icon != null);
	}
	public void testgeticonwebtvservice(){
	WebTVCommand cmd = WebTVCommand.instance();
	WebTVItem item = new WebTVItem();
	item.setId("youtube:media:lUjDq67nBCA");
	item.setTitle("LolCatz Compilation");
	item.setAuthor("");
	item.setDuration(400);
	item.setIconURL("http://i.ytimg.com/vi/lUjDq67nBCA/hqdefault.jpg");
	Bitmap icon;
	icon = cmd.getIcon(item);
	
	assertTrue(icon != null);
	}
}
