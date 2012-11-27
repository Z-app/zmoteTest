package se.z_app.zmote.webtv;

import java.io.File;

import se.z_app.httpserver.ZmoteHTTPD;
import se.z_app.stb.STB;
import se.z_app.stb.WebTVItem;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.HTTPRequestHandlerTestContainer;
import se.z_app.test_utils.STBFactory;
import android.test.AndroidTestCase;
import android.util.Log;

public class WebTVQueryTestInt extends AndroidTestCase {
	
	private static final String uri = "/mdio/webtv/play";
	private ZmoteHTTPD httpd;
	private HTTPRequestHandlerTestContainer container;
	private STB stb;
	private WebTVItem item;

	protected void setUp() throws Exception {
		item = new WebTVItem();
		item.setId("This_is_an_id");
		super.setUp();
		int port = 3000 + (int)(Math.random()*7000);
		httpd = new ZmoteHTTPD(port, new File(".").getAbsoluteFile());
		stb = STBFactory.getLocal(port);
		STBContainer.instance().setActiveSTB(stb);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testWebTVCommand() {
		container = new HTTPRequestHandlerTestContainer(uri);
		httpd.addHandler(container);
		WebTVQuery query = new WebTVQuery();
		query.play(item);
		while (container.getParms() == null) { 
		}
		String returnedPost = (String)container.getParms().get("url");
		assertEquals(container.getMethod(), "GET");
		assertEquals("webtv:This_is_an_id", returnedPost);
	}

}
