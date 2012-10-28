package se.z_app.stb.test;

import java.util.Date;
import se.z_app.stb.Program;
import android.test.AndroidTestCase;

/**
 * Test class for Channel. Fully implemented.
 * @author Leonard & co
 *
 */
public class ProgramTest extends AndroidTestCase {

	private static final String name = "test_name";
	private static final int eventID = 4;
	@SuppressWarnings("deprecation")
	private static final Date start = new Date(12,12,12);
	private static final int duration = 8412;
	private static final String shortText = "test_short_text";
	private static final String longText = "test_long_text";
	private Program program;
	
	protected void setUp() throws Exception {
		super.setUp();
		program = new Program();
		program.setName(name);
		program.setEventID(eventID);
		program.setStart(start);
		program.setDuration(duration);
		program.setShortText(shortText);
		program.setLongText(longText);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetName() {
		assertEquals(name, program.getName());
	}

	public void testSetName() {
		String name2 = "test_name_2";
		program.setName(name2);
		assertEquals(name2, program.getName());
	}

	public void testGetEventID() {
		assertEquals(eventID, program.getEventID());
	}

	public void testSetEventID() {
		int eventID2 = 7;
		program.setEventID(eventID2);
		assertEquals(eventID2, program.getEventID());
	}

	public void testGetStart() {
		assertEquals(start, program.getStart());
	}

	public void testSetStart() {
		@SuppressWarnings("deprecation")
		Date start2 = new Date(12,11,14);
		program.setStart(start2);
		assertEquals(start2, program.getStart());
	}

	public void testGetDuration() {
		assertEquals(duration, program.getDuration());
	}

	public void testSetDuration() {
		int duration2 = 7658;
		program.setDuration(duration2);
		assertEquals(duration2, program.getDuration());
	}

	public void testGetShortText() {
		assertEquals(shortText, program.getShortText());
	}

	public void testSetShortText() {
		String shortText2 = "test_short_text_2";
		program.setShortText(shortText2);
		assertEquals(shortText2, program.getShortText());
	}

	public void testGetLongText() {
		assertEquals(longText, program.getLongText());
	}

	public void testSetLongText() {
		String longText2 = "test_long_test_2";
		program.setLongText(longText2);
		assertEquals(longText2, program.getLongText());
	}

}
