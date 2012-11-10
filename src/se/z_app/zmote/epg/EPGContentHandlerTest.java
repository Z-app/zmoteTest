package se.z_app.zmote.epg;

import java.util.Iterator;

import se.z_app.stb.Channel;
import se.z_app.stb.EPG;
import se.z_app.stb.STB;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.STBFactory;
import android.test.AndroidTestCase;

/**
 * Test class for the EPGContentHandler
 * @author Marcus Widegren
 *
 */
public class EPGContentHandlerTest extends AndroidTestCase {
	public void setUp(){
		STB theSTB = STBFactory.getProxy();
		STBContainer.instance().setActiveSTB(theSTB);
	}
	public void tearDown(){}
	
	/**
	 * Make sure only one instance is created
	 */
	public void testInstance() {
		assertTrue(EPGContentHandler.instance() == EPGContentHandler.instance());
	}
	
	/**
	 * Check if all channels have gotten icons and that it updates when it should
	 */
	public void testChannelIcons() {
		Iterator<Channel> theIter = EPGContentHandler.instance().getEPG().iterator();
		while(theIter.hasNext()) {
			assertTrue(theIter.next().getIcon() != null);
		}
	}
	
	/**
	 * Test the buildEPG function, make sure it doesn't crash
	 */
	public void testBuildEPG() {
		EPGContentHandler.setContext(this.getContext());
		
		EPGContentHandler.instance().setUpdateInterval(1);
		EPG theEarlyEPG = EPGContentHandler.instance().getEPG(); 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(EPGContentHandler.instance().getEPG() != theEarlyEPG);
		
		
		EPGContentHandler.instance().setUpdateInterval(1000000000);
		theEarlyEPG = EPGContentHandler.instance().getEPG();
		assertTrue(EPGContentHandler.instance().getEPG() == theEarlyEPG);
		
	}
	
}
