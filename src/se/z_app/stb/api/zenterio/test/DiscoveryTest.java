package se.z_app.stb.api.zenterio.test;
import se.z_app.stb.STB;
import se.z_app.stb.api.zenterio.DiscoveryOld;
import android.test.AndroidTestCase;
import android.util.Log;

public class DiscoveryTest extends AndroidTestCase {
	private DiscoveryOld tester;
	
	public void setUp() throws Exception {
		tester = new DiscoveryOld("130.236.248.");
	}	

	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests if the stb result array is empty and if the proxy stb is in the array.
	 */
	
	public void testFind() { 
		STB [] stbs = tester.find();
		Log.e("STBTest", "lenght: " + stbs.length);
		System.out.println("STBTest lenght: " + stbs.length);

		assertEquals(4,stbs.length);
		
		boolean proxySTBCheck = false;
		boolean unique = true;
		for(int i = 0 ; i<stbs.length; i++) {
			
			if(stbs[i].getIP().equals("130.236.248.226"))
				proxySTBCheck = true;
			
			for(int j = 0; j < stbs.length ; j++ ) {
				if (i!=j && stbs[i].getIP().equals(stbs[j].getIP()))
					unique =false;					
			}			
		}
		assertTrue(unique);
		assertTrue(proxySTBCheck);
		Log.e("STBTest", "hej");
	}		
}
