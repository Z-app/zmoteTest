package se.z_app.stb.api.zenterio.test;
import se.z_app.stb.api.zenterio.Discovery;
import android.test.AndroidTestCase;

public class DiscoveryTest extends AndroidTestCase {
	private Discovery tester;
	
	public void setUp() throws Exception {
		tester = new Discovery("130.236.248.");
	}	

	public void tearDown() throws Exception {
	}


	public void testFind() { 
		assertTrue(tester.find().length==3);
		assertTrue(tester.find()[0].getIP().equals("130.236.248.226") || tester.find()[1].getIP().equals("130.236.248.227") || tester.find()[1].getIP().equals("130.236.248.228"));
	}

}
