package se.z_app.stb.api.test;

import java.util.Iterator;

import android.graphics.Bitmap;
import android.util.Log;
import se.z_app.stb.Channel;
import se.z_app.stb.EPG;
import se.z_app.stb.Program;
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

		Iterator<Channel> epgIterator = epg.iterator();
		assertTrue(epgIterator != null);
		int i = 0;
		while(epgIterator.hasNext()){
			i++;
			Channel channel = epgIterator.next();
			assertTrue(channel != null);
			assertTrue(channel.getName() != null);
			assertTrue(channel.getNr() != -1);
			assertTrue(channel.getOnid() != -1);
			assertTrue(channel.getSid() != -1);
			assertTrue(channel.getTsid() != -1);
			Iterator<Program> programIterator = channel.iterator();
			int j = 0;
			while(programIterator.hasNext()){
				j++;
				Program program = programIterator.next();
				assertTrue(program.getName() != null);
				assertTrue(program.getDuration() != -1);
				assertTrue(program.getStart() != null);
			}
			Log.i("ZmoteTestLog", "Number of programs: " + j);
			assertTrue(j > 1);
		}
		Log.i("ZmoteTestLog", "Number of Channels: " + i);
		assertTrue(i > 10);
		
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
		EPG epg = epgdata.getEPG();
		Channel ch1 = epg.getChannel(5);
		Channel ch2 = epg.getChannel(3);
		Bitmap icon1;
		Bitmap icon2;
		
		icon1 = epgdata.getChannelIcon(ch1);
		icon2 = epgdata.getChannelIcon(ch2);
		
		assertTrue(icon1 != null);
		assertTrue(icon2 != null);
		assertTrue(!icon1.equals(icon2));
	}

	public void testAddChannelIcon(){
		EPG epg = epgdata.getEPG();
		Channel ch1 = epg.getChannel(5);
		Channel ch2 = epg.getChannel(3);
				
		epgdata.populateWithChannelIcon(ch1);
		epgdata.populateWithChannelIcon(ch2);
		
		assertTrue(ch1.getIcon() != null);
		assertTrue(ch2.getIcon() != null);
		assertTrue(!ch1.getIcon().equals(ch2.getIcon()));
	}
	
	public void testAddChannelIcons(){
		EPG epg = epgdata.getEPG();
		epgdata.populateWithChannelIcon(epg);
		Iterator<Channel> channels = epg.iterator();
		while(channels.hasNext()){
			assertTrue(channels.next().getIcon() != null);
		}
	}
	
}
