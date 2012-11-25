package se.z_app.zmote.epg;

import java.util.Iterator;

import se.z_app.stb.Channel;
import se.z_app.stb.STB;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.STBFactory;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * 
 * @author Leonard Jansson and Emanuel Reneby
 * Integration test for EPGdbHandler and EPGContentHandler.
 */
public class EPGContentHandlerTestInt extends AndroidTestCase {

	private Channel channel;
	private EPGdbHandler edh;

	protected void setUp() throws Exception {
		super.setUp();
		edh = new EPGdbHandler(this.getContext());

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		edh.close();
	}

	public void testEPGContentHandler() {
		STB stb = STBFactory.getProxy();
		STBContainer.instance().setActiveSTB(stb);
		EPGContentHandler ech = EPGContentHandler.instance();
		ech.setUpdateInterval(10000);

		edh.updateEPG(stb, ech.getEPG());
		Iterator<Channel> i = ech.getEPG().iterator();
		int c = 0;

		while (i.hasNext()) {
			channel = i.next();
			Log.v("ech testtesttest", channel.getName() + " " + channel.getNr());
			c++;
		}
		int d =0;
		i = edh.selectEPG(stb).iterator();
		while (i.hasNext()) {
			channel = i.next();
			Log.v("edh testtesttest", channel.getName() + " " + channel.getNr());
			d++;
		}
		assertEquals(c,d);
		Iterator<Channel> ic = ech.getEPG().iterator();
		Iterator<Channel> id = edh.selectEPG(stb).iterator();
		while (ic.hasNext()) {
			assertEquals(ic.next().getName(), id.next().getName());
		}
	}
}
