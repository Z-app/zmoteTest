package se.z_app.stb.test;

import se.z_app.stb.WebTVService;
import android.graphics.Bitmap;
import android.test.AndroidTestCase;

/**
 * Test class for webTVService class. Fully implemented.
 * @author Emauel Reneby
 *
 */
public class WebTVServiceTest extends AndroidTestCase {
	
	private static final String id = "test_id";
	private static final String name = "test_name";
	private static final Bitmap icon = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_4444);
	private static final String iconURL = "test_icon_url";
	private WebTVService webTVService;

	protected void setUp() throws Exception {
		super.setUp();
		webTVService = new WebTVService();
		webTVService.setID(id);
		webTVService.setName(name);
		webTVService.setIcon(icon);
		webTVService.setIconURL(iconURL);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetID() {
		assertEquals(id, webTVService.getID());
	}

	public void testSetID() {
		String id2 = "test_id_2";
		webTVService.setID(id2);
		assertEquals(id2, webTVService.getID());
	}

	public void testGetName() {
		assertEquals(name, webTVService.getName());
	}

	public void testSetName() {
		String name2 = "test_name_2";
		webTVService.setName(name2);
		assertEquals(name2, webTVService.getName());
	}

	public void testGetIcon() {
		assertEquals(icon, webTVService.getIcon());
	}

	public void testSetIcon() {
		Bitmap icon2 = Bitmap.createBitmap(4, 5, Bitmap.Config.ALPHA_8);
		webTVService.setIcon(icon2);
		assertEquals(icon2, webTVService.getIcon());
	}

	public void testGetIconURL() {
		assertEquals(iconURL, webTVService.getIconURL());
	}

	public void testSetIconURL() {
		String iconURL2 = "test_icon_url_2";
		webTVService.setIconURL(iconURL2);
		assertEquals(iconURL2, webTVService.getIconURL());
	}

}
