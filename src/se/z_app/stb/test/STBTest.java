package se.z_app.stb.test;

import se.z_app.stb.STB;
import se.z_app.stb.STB.STBEnum;
import android.test.AndroidTestCase;

/**
 * Test class for STB class. Unclear what the use of toString() is.
 * @author Emanuel Reneby
 *
 */
public class STBTest extends AndroidTestCase {
	
	private STB stb;
	private static final STBEnum type = STBEnum.DEFAULT ;
	private static final String mac = "test_mac";
	private static final String boxName = "test_box_name";
	private static final String ip = "112.113.114.87";

	protected void setUp() throws Exception {
		super.setUp();
		stb = new STB();
		stb.setType(type);
		stb.setMAC(mac);
		stb.setBoxName(boxName);
		stb.setIP(ip);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetType() {
		assertEquals(type, stb.getType());
	}

	public void testSetType() {
		STBEnum type2 = STBEnum.ZENTERIO;
		stb.setType(type2);
		assertEquals(type2, stb.getType());
	}

	public void testGetMAC() {
		assertEquals(mac, stb.getMAC());
	}

	public void testSetMAC() {
		String mac2 = "test_mac_2";
		stb.setMAC(mac2);
		assertEquals(mac2, stb.getMAC());
	}

	public void testGetIP() {
		assertEquals(ip, stb.getIP());
	}

	public void testGetBoxName() {
		assertEquals(boxName, stb.getBoxName());
	}

	public void testSetBoxName() {
		String boxName2 = "test_box_name_2";
		stb.setBoxName(boxName2);
		assertEquals(boxName2, stb.getBoxName());
	}

	// What is the use of toString()? Does the same as getBoxName. 
	public void testToString() {
		assertEquals(stb.getBoxName(), stb.toString());
	}

	public void testSetIP() {
		String ip2 = "223.224.225.98";
		stb.setIP(ip2);
		assertEquals(ip2, stb.getIP());
	}

}
