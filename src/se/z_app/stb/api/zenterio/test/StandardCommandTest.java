package se.z_app.stb.api.zenterio.test;

import java.util.Iterator;

import junit.framework.TestCase;
import se.z_app.stb.Channel;
import se.z_app.stb.EPG;
import se.z_app.stb.Program;
import se.z_app.stb.WebTVItem;
import se.z_app.stb.WebTVService;
import se.z_app.stb.api.zenterio.StandardCommand;
import android.graphics.Bitmap;
import android.util.Log;

public class StandardCommandTest extends TestCase {

	public void testGetCurrentChannel(){
		StandardCommand cmd = new StandardCommand("130.236.248.226");
		assertTrue(cmd.getCurrentChannel().getName() != null);
		assertTrue(cmd.getCurrentChannel().getUrl() != null);
	}
	public void testGetEPG(){


		StandardCommand cmd = new StandardCommand("130.236.248.226");

		long time = System.currentTimeMillis();
		EPG epg = cmd.getEPG();
		Log.i("ZmoteTestLog", "Total Featching and parsing EPG: " + (System.currentTimeMillis() - time) + "ms");


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

	public void testGetWebTVServices(){
		StandardCommand cmd = new StandardCommand("130.236.248.226");
		WebTVService sevices[] = cmd.getWebTVServices();
		assertTrue(sevices.length == 3);

		for(int i = 0; i <3; i++){
			assertTrue(sevices[i].getName() != null);
			assertTrue(sevices[i].getID() != null);
			assertTrue(sevices[i].getIconURL() != null);
		}

	}

	public void testSearchWebTvService(){
		StandardCommand cmd = new StandardCommand("130.236.248.226");
		WebTVService sevices[] = cmd.getWebTVServices();

		assertTrue(sevices.length > 0);

		for(int i = 0; i < sevices.length; i++){
			Log.i("SearchTest", "Sevice: " + sevices[i].getName() + ", num: " + i);
			WebTVItem items[] = cmd.searchWebTVService("bill", sevices[i]);
			assertTrue(items != null);
			assertTrue(items.length > 0);
			for(int j = 0; j < items.length; j++){
				assertTrue(items[j].getAuthor() != null);
				assertTrue(items[j].getTitle() != null);
				assertTrue(items[j].getId() != null);
				assertTrue(items[j].getDuration() != -1);
				assertTrue(items[j].getWebTVService() != null);
			}
		}


	}

	public void testGetChannelImage(){
		StandardCommand cmd = new StandardCommand("130.236.248.57");
		Channel ch = new Channel();
		Bitmap icon;
		ch.setNr(1);
		ch.setOnid(1);
		ch.setSid(1000);
		ch.setTsid(1);
		
		icon = cmd.getChannelIcon(ch);
		
		assertTrue(icon != null);

	}
}
