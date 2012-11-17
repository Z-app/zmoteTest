package se.z_app.stb.test;

import se.z_app.stb.WebTVItem;
import se.z_app.stb.WebTVService;
import android.graphics.Bitmap;
import android.test.AndroidTestCase;

/**
 * 
 * @author Emanuel Reneby, Viktor von Zeipel and Stefan Konac
 * ID 2.1.1
 * Integration test for the classes WebTVService and WebTVItem.
 */
public class WebTVItemTestInt extends AndroidTestCase {

	private static final String author = "authorString";
	private static final int duration = 800;
	private static final Bitmap iconI = Bitmap.createBitmap(1,1,Bitmap.Config.ALPHA_8);
	private static final String iconURLI = "iconURLI";
	private static final String idI = "idI";
	private static final String info = "info";
	private static final String title = "title";
	private static final Bitmap iconS = Bitmap.createBitmap(1,1,Bitmap.Config.ALPHA_8);
	private static final String iconURLS = "iconURLS";
	private static final String idS = "idS";
	private static final String name = "name";
	private WebTVItem wti;
	private WebTVService wts;

	protected void setUp() throws Exception {
		super.setUp();
		wti = new WebTVItem();
		wti.setAuthor(author);
		wti.setDuration(duration);
		wti.setIcon(iconI);
		wti.setIconURL(iconURLI);
		wti.setId(idI);
		wti.setInfo(info);
		wti.setTitle(title);
		wts = new WebTVService();
		wts.setIcon(iconS);
		wts.setIconURL(iconURLS);
		wts.setID(idS);
		wts.setName(name);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testWebTVItemTestInt() {
		wti.setWebTVService(wts);
		assertEquals(wts, wti.getWebTVService());
		assertEquals(wts.getIconURL(), wti.getWebTVService().getIconURL());
		assertEquals(wts.getID(), wti.getWebTVService().getID());
		assertEquals(wts.getName(), wti.getWebTVService().getName());
		assertEquals(wts.getIcon(), wti.getWebTVService().getIcon());
	}

}
