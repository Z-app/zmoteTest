package se.z_app.stb.test;

import java.util.Date;
import java.util.Iterator;

import se.z_app.stb.Channel;
import se.z_app.stb.Program;
import android.graphics.Bitmap;
import android.test.AndroidTestCase;

/**
 * Test class for Channel. Fully implemented.
 * @author Leonard & co
 *
 */
public class ChannelTest extends AndroidTestCase {

	private Channel channel;
	private static final int nr = 12;
	private static final int onid = 87;
	private static final int sid = 26;
	private static final int tsid = 95;
	private static final String url = "test_url";
	private static final String name = "test_name";
	private static final String iconUrl = "test_icon_url";
	private static final Bitmap icon = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_4444);

	protected void setUp() throws Exception {
		super.setUp();
		channel = new Channel();
		channel.setIcon(icon);
		channel.setIconUrl(iconUrl);
		channel.setName(name);
		channel.setNr(nr);
		channel.setOnid(onid);
		channel.setSid(sid);
		channel.setTsid(tsid);
		channel.setUrl(url);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetName() {
		assertEquals(name, channel.getName());
	}

	public void testSetName() {
		String name2 = "test_name_2";
		channel.setName(name2);
		assertEquals(name2, channel.getName());
	}

	public void testGetIcon() {
		assertEquals(icon, channel.getIcon());
	}

	public void testSetIcon() {
		Bitmap icon2 = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
		channel.setIcon(icon2);
		assertEquals(icon2, channel.getIcon());
	}

	public void testGetIconUrl() {
		assertEquals(iconUrl, channel.getIconUrl());
	}

	public void testSetIconUrl() {
		String iconUrl2 = "test_icon_url_2";
		channel.setIconUrl(iconUrl2);
		assertEquals(iconUrl2, channel.getIconUrl());
	}

	public void testGetUrl() {
		assertEquals(url, channel.getUrl());
	}

	public void testSetUrl() {
		String url2 = "test_url_2";
		channel.setUrl(url2);
		assertEquals(url2, channel.getUrl());
	}

	public void testGetNr() {
		assertEquals(nr, channel.getNr());
	}

	public void testSetNr() {
		int nr2 = nr + 3;
		channel.setNr(nr2);
		assertEquals(nr2, channel.getNr());
	}

	public void testGetOnid() {
		assertEquals(onid, channel.getOnid());
	}

	public void testSetOnid() {
		int onid2 = onid + 4;
		channel.setOnid(onid2);
		assertEquals(onid2, channel.getOnid());
	}

	public void testGetTsid() {
		assertEquals(tsid, channel.getTsid());
	}

	public void testSetTsid() {
		int tsid2 = tsid + 5;
		channel.setTsid(tsid2);
		assertEquals(tsid2, channel.getTsid());
	}

	public void testGetSid() {
		assertEquals(sid, channel.getSid());
	}

	public void testSetSid() {
		int sid2 = sid + 8;
		channel.setSid(sid2);
		assertEquals(sid2, channel.getSid());
	}

	@SuppressWarnings("deprecation")
	public void testAddProgram() {
		int numberOfProgams = 12;
		Program[] programArray = new Program[numberOfProgams];
		Program program;
		Integer programName = numberOfProgams;
		Date date;
		int j = 3;
		for (int i = 0; i < numberOfProgams; i++) {
			j++;
			date = new Date(12,9, j);
			program = new Program();
			program.setName(programName.toString());
			program.setStart(date);
			programArray[i] = program;
			programName--;
		}
		program = new Program();
		program.setName(programName.toString());
		for (int i = 0; i < numberOfProgams; i++) {
			channel.addProgram(programArray[i]);
		}
		Iterator<Program> iterator = channel.iterator();
		for (int i = 0; i < numberOfProgams; i++) {
			assertEquals(programArray[i], iterator.next());
		}
	}

	public void testIterator() {
		assertNotNull(channel.iterator());
	}
	
}