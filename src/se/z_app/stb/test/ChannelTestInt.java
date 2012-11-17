package se.z_app.stb.test;

import java.util.Date;

import se.z_app.stb.Channel;
import se.z_app.stb.Program;
import android.graphics.Bitmap;
import android.test.AndroidTestCase;

/**
 * 
 * @author Stefan Konac and Emanuel Reneby
 * ID 2.1.2
 */
public class ChannelTestInt extends AndroidTestCase {
	
	private static final Bitmap icon = null;
	private static final String iconUrl = null;
	private static final String nameC = null;
	private static final int nr = 0;
	private static final int onid = 0;
	private static final int sid = 0;
	private static final int tsid = 0;
	private static final String url = null;
	private static final int duration = 0;
	private static final int eventID = 0;
	private static final String longText = null;
	private static final String nameP = null;
	private static final String shortText = null;
	private static final Date start = null;
	private Program program;
	private Channel channel;

	protected void setUp() throws Exception {
		super.setUp();
		channel = new Channel();
		channel.setIcon(icon);
		channel.setIconUrl(iconUrl);
		channel.setName(nameC);
		channel.setNr(nr);
		channel.setOnid(onid);
		channel.setSid(sid);
		channel.setTsid(tsid);
		channel.setUrl(url);
		program = new Program(channel);
		program.setDuration(duration);
		program.setEventID(eventID);
		program.setLongText(longText);
		program.setName(nameP);
		program.setShortText(shortText);
		program.setStart(start);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAddProgram() {
		fail("Not yet implemented");
	}

}
