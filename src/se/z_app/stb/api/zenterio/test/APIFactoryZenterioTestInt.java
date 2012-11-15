package se.z_app.stb.api.zenterio.test;
import se.z_app.stb.api.zenterio.*;
import se.z_app.stb.STB;
import se.z_app.stb.api.*;
import se.z_app.test_utils.STBFactory;
import junit.framework.TestCase;


public class APIFactoryZenterioTestInt extends TestCase {
	STB stb = STBFactory.get1();
	APIFactoryZenterio factory = new APIFactoryZenterio(stb);
	

	public void testGetDiscovery() {
		Discovery discovery = (Discovery) factory.getDiscovery();
		assertTrue(discovery instanceof Discovery);
	}

	public void testGetBiDirectional() {
		StandardCommand standardCommand = (StandardCommand) factory.getBiDirectional();
		assertTrue(standardCommand instanceof BiDirectionalCmdInterface);
	}

	public void testGetMonoDirectional() {
		RCCommand monoCommand = (RCCommand) factory.getMonoDirectional();
		assertTrue(monoCommand instanceof MonoDirectionalCmdInterface);
	}

	public void testGetEventListner() {
		EventListener listner = (EventListener) factory.getEventListner();
		assertTrue(listner instanceof EventListnerInterface);
	}

}
