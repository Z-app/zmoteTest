package se.z_app.stb.api.test;

import java.util.ArrayList;

import se.z_app.stb.STB;
import se.z_app.stb.api.AbstractAPIFactory;
import se.z_app.stb.api.DiscoveryInterface;
import se.z_app.stb.api.STBDiscovery;
import se.z_app.stb.api.zenterio.Discovery;
import se.z_app.test_utils.STBFactory;
import android.test.AndroidTestCase;
import android.util.Log;

public class STBDiscoveryTestInt extends AndroidTestCase {

	private static final String subNetAddress = "130.236.248.";
	private DiscoveryInterface discovery;
	private STBDiscovery stbDiscovery;
	private STB stb;

	protected void setUp() throws Exception {
		super.setUp();
		stb = STBFactory.get1();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSTBDiscovery() {
		discovery = AbstractAPIFactory.getFactory(stb).getDiscovery();
		stbDiscovery = new STBDiscovery(subNetAddress);
		Discovery disc = new Discovery(subNetAddress);
		STB[] stb2 = stbDiscovery.find();
		STB[] stb1 = discovery.find();
		STB[] stb3 = disc.find();
		Log.v("d", "Discovery: " + stb1.length + " STBDiscovery: " + stb2.length + "Discoverynew: " + stb3.length);
		assertEquals(stb1.length, stb2.length);
		ArrayList<STB> stbA1 = new ArrayList<STB>();
		ArrayList<STB> stbA2 = new ArrayList<STB>();
		for (int i = 0; i < stb1.length; i++) {
			stbA1.add(stb1[i]);
			stbA2.add(stb2[i]);
		}
		for (int i = 0; i < stb1.length; i++) {
			assertTrue(stbA2.contains(stbA1.get(i)));
		}
	}
	
}
