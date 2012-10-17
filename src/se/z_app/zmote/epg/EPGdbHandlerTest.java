package se.z_app.zmote.epg;

import java.util.Date;

import se.z_app.stb.Channel;
import se.z_app.stb.Program;
import se.z_app.stb.STB;
import se.z_app.stb.STB.STBEnum;
import android.test.AndroidTestCase;

public class EPGdbHandlerTest extends AndroidTestCase {

	EPGdbHandler db;
	Channel channel;
	Program program;
	STB stb;
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
		
		program = new Program();  //create a program
		program.setName("TopGear");
		program.setEventID(1337);
		@SuppressWarnings("deprecation")
		Date date = new Date(2012, 02, 23, 20, 00, 00); //should not actually be used, old method, use calendar instead or Joda time
		program.setStart(date);
		program.setDuration(60);
		program.setShortText("TopGear, start your engines");
		program.setLongText("The longrunner is back, featuring Clarkson, May and James, don's miss it");
		
		stb = new STB();
		stb.setBoxName("awesomebox");
		stb.setIP("127.0.0.1");
		stb.setMAC("83827:827AF:8282GH");
		stb.setType(STBEnum.DEFAULT);
		db = new EPGdbHandler(this.getContext());
		
		
		
	}
	public void testCreateChannel(){
		db.updateChannel(stb, channel);
		Program[] returnPrograms = db.selectPrograms(stb, channel);
		//System.out.println(returnProgram[1].getName());
		System.out.println(returnPrograms.length);
		assertEquals(channel.getName(), returnPrograms[0].getName());
		//System.out.println(returnProgram[1].getName());
	}
}
