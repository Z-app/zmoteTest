package se.z_app.stb.api.test;


import se.z_app.stb.api.RCProxy;
import se.z_app.stb.api.STBState;
import se.z_app.stb.api.STBState.State;
import android.test.AndroidTestCase;

public class RCProxyTest extends AndroidTestCase {
	
	private RCProxy tester;	
	
	public void setUp() throws Exception {
		tester = new RCProxy();
		
	}
	
	public void testUp(){
		tester.setState(STBState.State.MENU); 
		assertTrue(tester.up().equals("pup"));
		
	}
	
	
	
	
	
	
	
}
