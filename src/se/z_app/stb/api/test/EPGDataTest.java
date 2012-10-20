package se.z_app.stb.api.test;

import se.z_app.stb.Channel;
import se.z_app.stb.EPG;
import se.z_app.stb.STB;
import se.z_app.stb.STBProxy;
import se.z_app.stb.api.EPGData;
import se.z_app.stb.api.STBContainer;
import junit.framework.TestCase;

public class EPGDataTest extends TestCase {

	public EPGData epgdata;
	
	protected void setUp() throws Exception {
		super.setUp();
		STB stb = STBProxy.getProxy();

		STBContainer.instance().setSTB(stb);		
		epgdata = EPGData.instance();
		
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetEPG() {epgdata = EPGData.instance();
	
		EPG epg = epgdata.getEPG();
			
		assertTrue(epg != null);
		
		assertTrue(epg.iterator().hasNext());
		assertTrue(epg.iterator().next().getName() != null);
		assertTrue(epg.iterator().next().iterator().hasNext());
		assertTrue(epg.iterator().next().iterator().next().getStart() != null);
		
		Channel channel = epg.getChannel(4);
		assertTrue(channel.getNr() == 4);
		
		channel = epg.getChannel(10);
		assertTrue(channel.getNr() == 10);
		
	}

	public void testGetCurrentChannel() {
		assertTrue(epgdata.getCurrentChannel().getName() != null);
		assertTrue(epgdata.getCurrentChannel().getUrl() != null);
	}

	public void testGetChannelIcon() {
		fail("Not yet implemented");
	}

}
