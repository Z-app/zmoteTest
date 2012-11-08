package se.z_app.zmote.epg;

import java.util.Date;

import se.z_app.stb.Channel;
import se.z_app.stb.EPG;
import se.z_app.stb.Program;
import se.z_app.stb.STB;
import se.z_app.stb.STB.STBEnum;
import android.test.AndroidTestCase;

public class EPGdbHandlerTest extends AndroidTestCase {

	EPGdbHandler db;
	Channel channel;
	Channel channel1;
	Program program;
	Program program2;
	STB stb;
	STB stb1;
	/**
	 * The setup function, initiate the objects used later
	 */
	public void setUp(){
		channel = new Channel(); //create a channel
		channel.setName("BBC1");
		channel.setNr(1);
		channel.setIconUrl("/res/icon.png");
		channel.setOnid(1);
		channel.setSid(2);
		channel.setTsid(3);

		channel1 = new Channel(); //create a channel
		channel1.setName("SVT");
		channel1.setNr(2);
		channel1.setIconUrl("/res/icon.png");
		channel1.setOnid(10);
		channel1.setSid(11);
		channel1.setTsid(12);


		program = new Program(channel);  //create a program
		program.setName("TopGear");
		program.setEventID(1337);
		@SuppressWarnings("deprecation")
		Date date = new Date(2012, 02, 23, 20, 00, 00); //should not actually be used, old method, use calendar instead or Joda time
		program.setStart(date);
		program.setDuration(60);
		program.setShortText("TopGear, start your engines");
		program.setLongText("The longrunner is back, featuring Clarkson, May and James, don's miss it");

		program2 = new Program(channel1);  //create a program
		program2.setName("How I met your mother");
		program2.setEventID(1338);
		//		@SuppressWarnings("deprecation")
		//		Date date = new Date(2012, 02, 23, 20, 00, 00); //should not actually be used, old method, use calendar instead or Joda time
		program2.setStart(date);
		program2.setDuration(60);
		program2.setShortText("How I met your mother, nice show");
		program2.setLongText("Meet Ted and is infinite search for finding the one");

		stb = new STB();
		stb.setBoxName("awesomebox");
		stb.setIP("127.0.0.1");
		stb.setMAC("83827:827AF:8282GH");
		stb.setType(STBEnum.DEFAULT);

		stb1 = new STB();
		stb1.setBoxName("not_so_awesome_box");
		stb1.setIP("192.168.1.100");
		stb1.setMAC("1337:1337:1337");
		stb1.setType(STBEnum.DEFAULT);
		db = new EPGdbHandler(this.getContext());



	}
	/**
	 * Dont actually tests anything, remove?
	 */
	/*public void testCreateChannel(){
		db.updateChannel(stb, channel);
		//		Program[] returnPrograms = db.selectPrograms(stb, channel);
		//System.out.println(returnProgram[1].getName());
		//		System.out.println(returnPrograms.length);
		//		assertEquals(program.getName(), returnPrograms[0].getName());
		//		System.out.println(returnPrograms[0].getName());
		//		System.out.println(program.getName());
		//		//System.out.println(returnProgram[1].getName());
	}*/
	/**
	 * Tests if the updateChannels function is working, and the selectProgram, what to test?
	 */
	public void testCreateChannels(){
		Channel[] channels = new Channel[2];
		channels[0]=channel;
		channels[1]=channel1;
		db.updateChannels(stb, channels);

	}
	/**
	 * Tests that the updateProgram works, including test if the return from
	 * selectPrograms is returning the same value as inserted
	 */
	public void testInsertProgram(){
		db.updateProgram(stb, channel, program);
		Program[] programs = db.selectPrograms(stb, channel);
		assertEquals(program.getName(), programs[0].getName());		
	}
	/*public void testDontDuplicate(){
		Program[] programs = db.selectPrograms(stb1,channel);  //should fail, no programs on this stb
		assertEquals(program.getName(), programs[0].getName());
	}*/
	/**
	 * A test that tests the selectEPG function in the EPGdbHandler, more tests needed?
	 */
	public void testSelectEPG(){
		EPG epg = new EPG();
		epg.addChannel(channel);
		epg.addChannel(channel1);
		epg.setStb(stb);
		assertEquals(epg.getStb(), db.selectEPG(stb).getStb());
	}
	public void testSelectChannals(){
		Channel[] channels = new Channel[2];
		channels[0]=channel;
		channels[1]=channel1;
		db.updateChannels(stb, channels);
		assertEquals(channels[1].getName(), db.selectChannals(stb)[0].getName());
	}
}
