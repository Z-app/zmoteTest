package se.z_app.zmote.webtv;

import java.util.Random;

import se.z_app.stb.STB;
import se.z_app.stb.WebTVService;
import se.z_app.stb.STB.STBEnum;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * 
 * @author Sebastian Rauhala
 * 
 *
 */

public class WebTVdbHandelertest extends AndroidTestCase{

	WebTVdbHandler WebTVdb;
	WebTVService[] services;
	STB stb, stb1;

	public void setUp(){
		WebTVdb = new WebTVdbHandler(this.getContext());
		services = new WebTVService[3];

		for (int i = 0; i <= 2; i++){
			WebTVService service = new WebTVService();

			Random r = new Random();
			String id = Long.toString(Math.abs(r.nextLong()), 36);
//			Log.i("WebTVdbHandeler Test", "WebTv setUP = " + id);

			String name = Long.toString(Math.abs(r.nextLong()), 36);
//			Log.i("WebTVdbHandeler Test", "WebTv setUP = " + name);

			String iconURL = Long.toString(Math.abs(r.nextLong()), 36);
//			Log.i("WebTVdbHandeler Test", "WebTv setUP = " + iconURL);

			service.setID(" ID = " + id);
			service.setName(" Name = " + name);
			service.setIconURL(" IconURL = " + iconURL);

			services[i] = service;

		}
		
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

	}

	public void testupdateServices(){
		WebTVdb.updateServices(stb1, services);
	}
	
	public void testselectServices(){
		WebTVService test = new WebTVService();
		
		test.setID(" ID = " + "MYIDIS");
		test.setName(" Name = " + "MYNAMEIS");
		test.setIconURL(" IconURL = " + "MYICONURLIS");
		
		
		WebTVdb.updateServices(stb, test);
		WebTVService[] dbServices = WebTVdb.selectServices(stb);
		assertEquals(test.getID(), dbServices[0].getID());
		for(int i = 0; i<dbServices.length; i++){
			Log.i("WebTVdbHandeler Test", "WebTv dbServices ID = " + dbServices[i].getID());
			Log.i("WebTVdbHandeler Test", "WebTv dbServices ID = " + dbServices[i].getName());
			Log.i("WebTVdbHandeler Test", "WebTv dbServices ID = " + dbServices[i].getIconURL());
		}
		
	}

}
