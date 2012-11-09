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
	
	/**
	 * Set up the channels and programs for the test
	 */
	public void setUp()
	{
		theQuery = new EPGQuery();
		
		Channel channel = new Channel(); //create a channel
		channel.setName("BBC1");
		channel.setNr(1);
		channel.setIconUrl("/res/icon.png");
		channel.setOnid(1);
		channel.setSid(2);
		channel.setTsid(3);

		Channel channel1 = new Channel(); //create a channel
		channel1.setName("SVT");
		channel1.setNr(2);
		channel1.setIconUrl("/res/icon.png");
		channel1.setOnid(10);
		channel1.setSid(11);
		channel1.setTsid(12);

		Program program = new Program(channel);  //create a program
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

		Program program3 = new Program(channel);
		program3.setName(PROGRAM3_NAME);
		program3.setEventID(1339);
		@SuppressWarnings("deprecation")
		Date dateUS = new Date(2012, 02, 23, 21, 00, 00); //should not actually be used, old method, use calendar instead or Joda time
		program3.setStart(dateUS);
		program3.setDuration(60);
		program3.setShortText("TopGear, start your engines");
		program3.setLongText("The longrunner is back, featuring Clarkson, May and James, don's miss it");
		
		channel.addProgram(program);
		channel.addProgram(program3);
		channel1.addProgram(program2);
		
		EPGContentHandler.instance().getEPG().addChannel(channel);
		EPGContentHandler.instance().getEPG().addChannel(channel1);
	}
	
	public void tearDown(){}
	
	/**
	 * Test if the search query works
	 */
	public void testSearchProgram()
	{
		assertTrue(theQuery.searchProgram(PROGRAM_NAME).length == 2);
		assertTrue(theQuery.searchProgram(PROGRAM3_NAME).length == 1);
		assertTrue(theQuery.searchProgram(PROGRAM2_NAME).length == 1);
		assertTrue(theQuery.searchProgram("This program does not exist.").length == 0);
	}
}
