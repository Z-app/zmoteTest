package se.z_app.stb.test;

import se.z_app.stb.STBEvent;
import android.test.AndroidTestCase;

/**
 * Test class for STBEVent class. Fully implemented.
 * @author Emanuel Reneby, Leonard Jansson
 *
 */
public class STBEventTest extends AndroidTestCase {
	private static final String type = "test_type";
	private static final String label = "test_label";
	private static final String url = "test_url";
	private static final int value = 29;
	private STBEvent stbEvent;

	protected void setUp() throws Exception {
		super.setUp();
		stbEvent = new STBEvent();
		stbEvent.setType(type);
		stbEvent.setLabel(label);
		stbEvent.setUrl(url);
		stbEvent.setValue(value);		
		stbEvent.setState(true);		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetType() {
		assertEquals(type, stbEvent.getType());
	}

	public void testSetType() {
		String type2 = "test_type_2";
		stbEvent.setType(type2);
		assertEquals(type2, stbEvent.getType());
	}

	public void testGetLabel() {
		assertEquals(label, stbEvent.getLabel());
	}

	public void testSetLabel() {
		String label2 = "test_label_2";
		stbEvent.setLabel(label2);
		assertEquals(label2, stbEvent.getLabel());
	}

	public void testGetUrl() {
		assertEquals(url, stbEvent.getUrl());
	}

	public void testSetUrl() {
		String url2 = "test_url_2";
		stbEvent.setUrl(url2);
		assertEquals(url2, stbEvent.getUrl());
	}
	
	public void testGetValue() {
		assertEquals(value, stbEvent.getValue());
	}

	public void testSetValue() {
		int value2 = 78;
		stbEvent.setValue(value2);
		assertEquals(value2, stbEvent.getValue());
	}
	
	public void testGetState() {
		assertTrue(stbEvent.getState());
	}

	public void testSetState() {
		stbEvent.setState(false);
		assertFalse(stbEvent.getState());
	}
}
