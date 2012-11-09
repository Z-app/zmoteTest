package se.z_app.zmote.epg;

import java.util.Date;

import se.z_app.stb.Channel;
import se.z_app.stb.Program;
import android.test.AndroidTestCase;

/**
 * Test class for the EPGQuery
 * @author Marcus Widegren
 *
 */
public class EPGQueryTest extends AndroidTestCase {
	EPGQuery theQuery;
	
	final String PROGRAM_NAME = "TopGear";
	final String PROGRAM2_NAME = "How I met your mother";
	final String PROGRAM3_NAME = "TopGearUS";
	private Channel channel1, channel2, channel3;
	private Program earlyProgram;
	
	/**
	 * Set up the channels and programs for the test
	 */
	public void setUp() {
		theQuery = new EPGQuery();
		
		channel1 = new Channel(); //create a channel
		channel1.setName("BBC1");
		channel1.setNr(1);
		channel1.setIconUrl("/res/icon.png");
		channel1.setOnid(1);
		channel1.setSid(2);
		channel1.setTsid(3);

		channel2 = new Channel(); //create a channel
		channel2.setName("SVT");
		channel2.setNr(2);
		channel2.setIconUrl("/res/icon.png");
		channel2.setOnid(10);
		channel2.setSid(11);
		channel2.setTsid(12);
		
		channel3 = new Channel(); //create a channel
		channel3.setName("TV4");
		channel3.setNr(3);
		channel3.setIconUrl("/res/icon.png");
		channel3.setOnid(21);
		channel3.setSid(22);
		channel3.setTsid(23);

		Program program = new Program(channel1);  //create a program
		program.setName(PROGRAM_NAME);
		program.setEventID(1337);
		@SuppressWarnings("deprecation")
		Date date = new Date(2012, 02, 23, 20, 00, 00); //should not actually be used, old method, use calendar instead or Joda time
		program.setStart(date);
		program.setDuration(60);
		program.setShortText("TopGear, start your engines");
		program.setLongText("The longrunner is back, featuring Clarkson, May and James, don's miss it");
		
		Program program2 = new Program(channel1);  //create a program
		program2.setName(PROGRAM2_NAME);
		program2.setEventID(1338);
		//		@SuppressWarnings("deprecation")
		//		Date date = new Date(2012, 02, 23, 20, 00, 00); //should not actually be used, old method, use calendar instead or Joda time
		program2.setStart(date);
		program2.setDuration(60);
		program2.setShortText("How I met your mother, nice show");
		program2.setLongText("Meet Ted and is infinite search for finding the one");

		Program program3 = new Program(channel1);
		program3.setName(PROGRAM3_NAME);
		program3.setEventID(1339);
		@SuppressWarnings("deprecation")
		Date dateUS = new Date(2012, 02, 23, 21, 00, 00); //should not actually be used, old method, use calendar instead or Joda time
		program3.setStart(dateUS);
		program3.setDuration(60);
		program3.setShortText("TopGear, start your engines");
		program3.setLongText("The longrunner is back, featuring Clarkson, May and James, don's miss it");
		
		earlyProgram = new Program(channel1);
		earlyProgram.setName("Early Program");
		earlyProgram.setStart(new Date(System.currentTimeMillis() - 3600 * 1000));
		earlyProgram.setDuration(120);
		
		Program currentProgram = new Program(channel2);
		currentProgram.setName("Current Program");
		currentProgram.setStart(new Date(System.currentTimeMillis() + 1000 * 1000));
		currentProgram.setDuration(60);
		
		Program lateProgram = new Program(channel3);
		lateProgram.setName("Late Program");
		lateProgram.setStart(new Date(System.currentTimeMillis() + 3600 * 2000));
		
		channel1.addProgram(program);
		channel1.addProgram(program3);
		channel1.addProgram(earlyProgram);
		channel2.addProgram(program2);
		channel2.addProgram(currentProgram);
		channel3.addProgram(lateProgram);
		
		EPGContentHandler.instance().getEPG().addChannel(channel1);
		EPGContentHandler.instance().getEPG().addChannel(channel2);
		EPGContentHandler.instance().getEPG().addChannel(channel3);
	}
	
	public void tearDown(){}
	
	/**
	 * Test if the search query using string works
	 */
	public void testStringSearchProgram() {
		assertTrue(theQuery.searchProgram(PROGRAM_NAME).length == 2);
		assertTrue(theQuery.searchProgram(PROGRAM3_NAME).length == 1);
		assertTrue(theQuery.searchProgram(PROGRAM2_NAME).length == 1);
		assertTrue(theQuery.searchProgram("This program does not exist.").length == 0);
	}
	
	/**
	 * Test if the search query using time intervals works
	 */
	public void testIntervalSearchProgram() {
		assertTrue(theQuery.searchProgram(new Date(System.currentTimeMillis()), 1000 * 750).length == 0);
		assertTrue(theQuery.searchProgram(new Date(System.currentTimeMillis()), 1000 * 1500).length == 1);
		assertTrue(theQuery.searchProgram(new Date(System.currentTimeMillis()), 3600 * 1500).length == 2);
		assertTrue(theQuery.searchProgram(new Date(System.currentTimeMillis()), 3600 * 2500).length == 3);
	}
	
	/**
	 * Test the getEPG function
	 */
	public void testGetEPG() {
		assertTrue(theQuery.getEPG() == theQuery.getEPG());
	}
	
	/**
	 * Test getting a channel by nr
	 */
	public void testGetChannelByNr() {
		assertTrue(theQuery.getChannel(1) == channel1);
		assertTrue(theQuery.getChannel(2) == channel2);
		assertTrue(theQuery.getChannel(3) == channel3);
	}
	
	/**
	 * Test getting active programs
	 */
	public void testGetActiveChannels() {
		assertTrue(theQuery.getActivePrograms().length == 1);
		assertTrue(theQuery.getActivePrograms()[0] == earlyProgram);
	}
}
