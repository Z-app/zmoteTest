package se.z_app.stb.api.zenterio.test;
import se.z_app.stb.api.zenterio.*;
import se.z_app.stb.STB;
import se.z_app.stb.api.*;
import se.z_app.test_utils.STBFactory;
import junit.framework.TestCase;

/**
 * 
 * @author Viktor Håkansson, Stefan Konac
 * 
 */


public class APIFactoryZenterioTestInt extends TestCase {
// Creating an STB as the STB1
	STB stb = STBFactory.get1();
	APIFactoryZenterio factory = new APIFactoryZenterio(stb);
	

	public void testGetDiscovery() {
		//Testing the factory procedure getDiscovery
		Discovery discovery = (Discovery) factory.getDiscovery();
		assertTrue(discovery instanceof Discovery);
	}

	public void testGetBiDirectional() {
		//Testing the factory procedure getBiDirectional
		StandardCommand standardCommand = (StandardCommand) factory.getBiDirectional();
		assertTrue(standardCommand instanceof BiDirectionalCmdInterface);
	}

	public void testGetMonoDirectional() {
		//Testing the factory procedure getMonoDirectional
		RCCommand monoCommand = (RCCommand) factory.getMonoDirectional();
		assertTrue(monoCommand instanceof MonoDirectionalCmdInterface);
	}

	public void testGetEventListner() {
		//Testing the factory procedure getEventListener
		EventListener listner = (EventListener) factory.getEventListner();
		assertTrue(listner instanceof EventListnerInterface);
	}

}
