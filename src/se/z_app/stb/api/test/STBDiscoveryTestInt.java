package se.z_app.stb.api.test;

import se.z_app.stb.STB;
import se.z_app.stb.api.STBDiscovery;
import se.z_app.stb.api.zenterio.Discovery;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * 
 * @author Stefan Konac and Emanuel Reneby
 * ID 1.2.5
 * Integration test for APIFactoryZenterio, Discovery and STBDiscovery.
 */
public class STBDiscoveryTestInt extends AndroidTestCase {

	private static final String subNetAddress = "130.236.248.";
	private Discovery discovery;
	private STBDiscovery stbDiscovery;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSTBDiscovery() {
		stbDiscovery = new STBDiscovery(subNetAddress);
		discovery = new Discovery(subNetAddress);
		STB[] stb1 = discovery.find();
		STB[] stb2 = stbDiscovery.find();
		Log.v("d", "Discovery: " + stb1.length + " STBDiscovery: " + stb2.length);
		assertEquals(stb1.length, stb2.length);
		for (int i = 0; i < stb2.length; i++) {
			Boolean found = false;
			for (int j = 0; j < stb2.length; j++) {
				if (stb1[i].equals(stb2[j])) found = true;
			}
			assertTrue(found);
		}
	}
	
}
