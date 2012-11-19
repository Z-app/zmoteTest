package se.z_app.stb.api.test;

import se.z_app.stb.STB;
import se.z_app.stb.api.AbstractAPIFactory;
import se.z_app.stb.api.BiDirectionalCmdInterface;
import se.z_app.stb.api.DiscoveryInterface;
import se.z_app.stb.api.EventListnerInterface;
import se.z_app.stb.api.MonoDirectionalCmdInterface;
import se.z_app.stb.api.zenterio.DiscoveryOld;
import se.z_app.stb.api.zenterio.EventListener;
import se.z_app.stb.api.zenterio.RCCommand;
import se.z_app.stb.api.zenterio.StandardCommand;
import se.z_app.test_utils.STBFactory;
import junit.framework.TestCase;

public class APIFactoryZenterioTest extends TestCase {

	STB stb;
	AbstractAPIFactory factory;
	protected void setUp() throws Exception {
		super.setUp();
		stb = STBFactory.getProxy();
		factory = AbstractAPIFactory.getFactory(stb);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetDiscovery() {
		assertTrue(factory != null);
		DiscoveryInterface di = factory.getDiscovery();
		assertTrue(di.getClass() == DiscoveryOld.class);
	}

	public void testGetBiDirectional() {
		assertTrue(factory != null);
		BiDirectionalCmdInterface di = factory.getBiDirectional();
		assertTrue(di.getClass() == StandardCommand.class);
	}

	public void testGetMonoDirectional() {
		assertTrue(factory != null);
		MonoDirectionalCmdInterface di = factory.getMonoDirectional();
		assertTrue(di.getClass() == RCCommand.class);
	}

	public void testGetEventListner() {
		assertTrue(factory != null);
		EventListnerInterface di = factory.getEventListner();
		assertTrue(di.getClass() == EventListener.class);
	}

}
