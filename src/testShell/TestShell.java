package testShell;

import se.z_app.stb.api.RCProxy; // This shall be changed to the file you will test
import android.test.AndroidTestCase;


public class TestShell extends AndroidTestCase {

	private RCProxy theProxy;

	public void setUp() throws Exception {
		theProxy = new RCProxy();
	}

	public void tearDown() throws Exception {
	}


	public void testUp(){ RCProxy tester = new RCProxy(); assertTrue("pup".equals(tester.up()));
	}
}
