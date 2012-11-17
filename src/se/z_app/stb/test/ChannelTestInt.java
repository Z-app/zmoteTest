package se.z_app.stb.test;
import java.util.Date;
import java.util.Iterator;

import se.z_app.stb.Channel;
import se.z_app.stb.Program;
import android.graphics.Bitmap;
import android.test.AndroidTestCase;

/**
 * 
 * @author Stefan Konac, Emanuel Reneby, Viktor Håkansson, Viktor von Zeipel
 * ID 2.1.2
 */


public class ChannelTestInt extends AndroidTestCase {
	
	private static final Bitmap icon = null;
	private static final String iconUrl = null;
	private static final String nameC = "4Music";
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
	// Dummy data for test
	private static final Date start = new Date(2012,11,15,11,11);
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

	/**
	 * Method that tests to add program and check if the added program is in the iterator.
	 * 
	 */
	
	public void testAddProgram() {
		channel.addProgram(program);
		Iterator<Program> iterator = channel.iterator();
		boolean isTrue = false;
		while(iterator.hasNext()){
			if(program==iterator.next()){
				isTrue=true;
			}

		}
	}
}

