package se.z_app.stb.test;

import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListMap;

import se.z_app.stb.Channel;
import se.z_app.stb.EPG;
import se.z_app.stb.STB;
import android.annotation.SuppressLint;
import android.test.AndroidTestCase;

/**
 * Test Class for EPG class. Could possibly need deeper tests for the iterators.
 * @author Emanuel Reneby
 *
 */
public class EPGTest extends AndroidTestCase {

	private EPG epg;
	private STB stb;
	private static final long dateOfCreation = 13;
	private static final String ip = "1.2.3.4";

	protected void setUp() throws Exception {
		super.setUp();
		epg = new EPG();
		epg.setDateOfCreation(dateOfCreation);
		stb = new STB();
		stb.setIP(ip);
		epg.setStb(stb);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testIterator() {
		assertNotNull(epg.iterator());
	}

	public void testIteratorByName() {
		assertNotNull(epg.iteratorByName());
	}

	public void testIteratorByNr() {
		assertNotNull(epg.iteratorByNr());
	}

	public void testGetDateOfCreation() {
		assertEquals(dateOfCreation, epg.getDateOfCreation());
	}

	public void testSetDateOfCreation() {
		long dateOfCreation2 = 14;
		epg.setDateOfCreation(dateOfCreation2);
		assertEquals(dateOfCreation2, epg.getDateOfCreation());
	}

	public void testGetStb() {
		assertEquals(stb, epg.getStb());
	}

	public void testSetStb() {
		STB stb2 = new STB();
		String ip2 = "5.6.7.8";
		stb2.setBoxName(ip2);
		epg.setStb(stb2);
		assertEquals(stb2, epg.getStb());
	}

	public void testAddChannel() {
		int channelCount = 5;
		Integer channelData =  channelCount + 1;
		Channel[] channels = new Channel[channelCount ];
		for (int i = 0; i < channelCount; i++) {
			Channel channel = new Channel();
			channel.setName(channelData.toString());
			channelData++;
			channel.setNr(channelData);
			channelData++;
			channels[i] = channel;
		}
		for (int i = 0; i < channels.length; i++) {
			epg.addChannel(channels[i]);
		}
		Iterator<Channel> iterator = epg.iterator();
		for (int i = 0; i < channels.length; i++) {
			assertEquals(channels[i], iterator.next());
		}
	}

	public void testGetChannelInt() {
		int channelCount = 5;
		int testNr = 3;
		Channel channelToFind = new Channel();
		for (int i = 0; i < channelCount ; i++) {
			Channel channel = new Channel();
			channel.setName(((Integer) (channelCount + i + 1)).toString());
			channel.setNr(2*(channelCount + i) + 1);
			if (i == testNr) channelToFind = channel;
			epg.addChannel(channel);
		}
		assertEquals(channelToFind, epg.getChannel(2*(channelCount + testNr) +1));
	}

	public void testGetChannelString() {
		int channelCount = 5;
		int testName = 2;
		Channel channelToFind = new Channel();
		for (int i = 0; i < channelCount ; i++) {
			Channel channel = new Channel();
			channel.setName(((Integer) (channelCount + i + 1)).toString());
			channel.setNr(2*(channelCount + i) + 1);
			if (i == testName) channelToFind = channel;
			epg.addChannel(channel);
		}
		assertEquals(channelToFind, epg.getChannel(((Integer) (channelCount + testName + 1)).toString()));
	}

}
