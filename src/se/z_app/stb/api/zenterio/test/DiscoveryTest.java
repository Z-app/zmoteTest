package se.z_app.stb.api.zenterio.test;
import se.z_app.stb.STB;
import se.z_app.stb.api.zenterio.Discovery;
import android.test.AndroidTestCase;
import android.util.Log;

public class DiscoveryTest extends AndroidTestCase {
	private Discovery tester;
	
	public void setUp() throws Exception {
		tester = new Discovery("130.236.248.");
	}	

	public void tearDown() throws Exception {
	}


	public void testFind() { 
		STB [] stbs = tester.find();
		Log.e("STBTest", "lenght: " + stbs.length);
		System.out.println("STBTest lenght: " + stbs.length);
		assertTrue(stbs.length!=56);
		assertTrue(tester.find()[0].getIP().equals("130.236.248.226") || tester.find()[1].getIP().equals("130.236.248.226") || tester.find()[2].getIP().equals("130.236.248.226"));
	}
}
