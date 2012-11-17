package se.z_app.stb.api.test;

import java.io.File;

import se.z_app.httpserver.ZmoteHTTPD;
import se.z_app.stb.STB;
import se.z_app.stb.api.RCProxy;
import se.z_app.stb.api.RCProxyState;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.HTTPRequestHandlerTestContainer;
import se.z_app.test_utils.STBFactory;
import android.test.AndroidTestCase;

public class DefaultStateTestInt extends AndroidTestCase {
	
	private static final String uri = "/cgi-bin/writepipe_key";
	private ZmoteHTTPD httpd;
	private HTTPRequestHandlerTestContainer container;
	private STB stb;

	protected void setUp() throws Exception {
		super.setUp();
		int port = 3000 + (int)(Math.random()*7000);
		httpd = new ZmoteHTTPD(port, new File(".").getAbsoluteFile());
		stb = STBFactory.getLocal(port);
		STBContainer.instance().setActiveSTB(stb);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDefaultStateTestInt() {
		container = new HTTPRequestHandlerTestContainer(uri);
		httpd.addHandler(container);
		RCProxyState ds = RCProxy.instance().getState();
		ds.up();
		assertTrue(check().equals("pup"));
		ds.down();
		assertTrue(check().equals("pdown"));
		ds.right();
		assertTrue(check().equals("pright"));
		ds.left();
		assertTrue(check().equals("pleft"));
		ds.ok();
		assertTrue(check().equals("pok"));		
		ds.back();	
		assertTrue(check().equals("pback"));
		ds.mute();
		assertTrue(check().equals("pmute"));
		ds.info();
		assertTrue(check().equals("pinfo"));
		ds.menu();
		assertTrue(check().equals("pmenu"));
		ds.exit();
		assertTrue(check().equals("pexit"));
	}
	
	private String check() {
		while (container.getParms() == null) { }
		String returnedPost = (String)container.getParms().get("raw");
		assertTrue(container.getMethod().equals("POST"));
		return returnedPost;
	}
	
}
