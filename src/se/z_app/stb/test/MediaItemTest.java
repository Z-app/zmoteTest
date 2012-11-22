package se.z_app.stb.test;

import se.z_app.stb.MediaItem;
import android.test.AndroidTestCase;

/**
 * Test class for MediaItem class. Fully implemented.
 * @author Leonard Jansson, Viktor von Zeipel
 *
 */

public class MediaItemTest extends AndroidTestCase {
	private static final String name = "test_name";
	private static final String iconURL = "test_iconURL";
	private static final String url = "test_url";
	private MediaItem mediaItem;

	protected void setUp() throws Exception {
		super.setUp();
		mediaItem = new MediaItem();
		mediaItem.setName(name);
		mediaItem.setIconURL(iconURL);
		mediaItem.setUrl(url);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetUrl() {
		assertEquals(url, mediaItem.getUrl());
	}

	public void testSetUrl() {
		String url2 = "test_url_2";
		mediaItem.setUrl(url2);
		assertEquals(url2, mediaItem.getUrl());
	}
	
	public void testGetName() {
		assertEquals(name, mediaItem.getName());
	}

	public void testSetName() {
		String name2 = "test_name_2";
		mediaItem.setName(name2);
		assertEquals(name2, mediaItem.getName());
	}
	
	public void testGetIconURL() {
		assertEquals(iconURL, mediaItem.getIconURL());
	}

	public void testSetIconURL() {
		String iconURL2 = "test_iconUrl_2";
		mediaItem.setIconURL(iconURL2);
		assertEquals(iconURL2, mediaItem.getIconURL());
	}
}