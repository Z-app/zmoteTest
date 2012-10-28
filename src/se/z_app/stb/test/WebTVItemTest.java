package se.z_app.stb.test;

import se.z_app.stb.WebTVItem;
import se.z_app.stb.WebTVService;
import android.graphics.Bitmap;
import android.test.AndroidTestCase;

public class WebTVItemTest extends AndroidTestCase {
	
	
	private static final String webTVServiceId = "test_webTVService_id";
	private static final String id = "test_id";
	private static final String author = "test_author";
	private static final String title = "test_title";
	private static final String info = "test_info";
	private static final int duration = 486;
	private static final Bitmap icon = Bitmap.createBitmap(4,3,Bitmap.Config.ALPHA_8);
	private static final String iconURL = "test_icon_url";
	private WebTVItem webTVItem;
	private WebTVService webTVService;

	protected void setUp() throws Exception {
		super.setUp();
		webTVItem = new WebTVItem();
		webTVService = new WebTVService();
		webTVService.setID(webTVServiceId);
		webTVItem.setWebTVService(webTVService);
		webTVItem.setId(id);
		webTVItem.setTitle(title);
		webTVItem.setAuthor(author);
		webTVItem.setInfo(info);
		webTVItem.setDuration(duration);
		webTVItem.setIcon(icon);
		webTVItem.setIconURL(iconURL);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetWebTVService() {
		assertEquals(webTVService, webTVItem.getWebTVService());
	}

	public void testSetWebTVService() {
		WebTVService webTVService2 = new WebTVService();
		String webTVServiceId2 = "test_webTVService_id_2";
		webTVService2.setID(webTVServiceId2);
		webTVItem.setWebTVService(webTVService2);
		assertEquals(webTVService2, webTVItem.getWebTVService());
	}

	public void testGetId() {
		assertEquals(id, webTVItem.getId());
	}

	public void testSetId() {
		String id2 = "test_id_2";
		webTVItem.setId(id2);
		assertEquals(id2, webTVItem.getId());
	}

	public void testGetTitle() {
		assertEquals(title, webTVItem.getTitle());
	}

	public void testSetTitle() {
		String title2 = "test_title_2";
		webTVItem.setTitle(title2);
		assertEquals(title2, webTVItem.getTitle());
	}

	public void testGetAuthor() {
		assertEquals(author, webTVItem.getAuthor());
	}

	public void testSetAuthor() {
		String author2 = "test_author_2";
		webTVItem.setAuthor(author2);
		assertEquals(author2, webTVItem.getAuthor());
	}

	public void testGetInfo() {
		assertEquals(info, webTVItem.getInfo());
	}

	public void testSetInfo() {
		String info2 = "test_info_2";
		webTVItem.setInfo(info2);
		assertEquals(info2, webTVItem.getInfo());
	}

	public void testGetDuration() {
		assertEquals(duration, webTVItem.getDuration());
	}

	public void testSetDuration() {
		int duration2 = 147;
		webTVItem.setDuration(duration2);
		assertEquals(duration2, webTVItem.getDuration());
	}

	public void testGetIcon() {
		assertEquals(icon, webTVItem.getIcon());
	}

	public void testSetIcon() {
		Bitmap icon2 = Bitmap.createBitmap(1,2,Bitmap.Config.RGB_565);
		webTVItem.setIcon(icon2);
		assertEquals(icon2, webTVItem.getIcon());
	}

	public void testGetIconURL() {
		assertEquals(iconURL, webTVItem.getIconURL());
	}

	public void testSetIconURL() {
		String iconURL2 = "test_icon_url_2";
		webTVItem.setIconURL(iconURL2);
		assertEquals(iconURL2, webTVItem.getIconURL());
	}

}
