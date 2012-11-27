package se.z_app.zmote.epg;

import java.util.Iterator;

import se.z_app.stb.Channel;
import se.z_app.stb.EPG;
import se.z_app.stb.STB;
import se.z_app.stb.api.EPGData;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.STBFactory;
import android.annotation.TargetApi;
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

	public void testEPGdbHandler() {
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

	@TargetApi(12)
	public void testEPGData(){
		STB stb = STBFactory.getProxy();
		STBContainer.instance().setActiveSTB(stb);
		EPGContentHandler.setContext(null);
		EPGContentHandler ech = EPGContentHandler.instance();
		EPG epg = EPGData.instance().getEPG();
		EPGData.instance().populateAbsentChannelIcon(epg);
		Iterator<Channel> it = epg.iterator();
		EPG epgUnderTest = ech.getEPG();
		Iterator<Channel> itut = epgUnderTest.iterator();
		while(it.hasNext()){			
			if(itut.hasNext()) {
				Channel channel_a = it.next();
				Channel channel_b = itut.next();
				if (channel_a.getIcon() == null || channel_b.getIcon() == null) assertTrue(channel_a.getIcon() == null && channel_b.getIcon() == null);
				else assertTrue(channel_a.getIcon().sameAs(channel_b.getIcon()));
			}
			else assertTrue(false);
		}
	}
}
