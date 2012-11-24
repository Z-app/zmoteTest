package se.z_app.stb.api.test;

import java.util.Iterator;
import java.util.LinkedList;

import junit.framework.TestCase;
import se.z_app.stb.STB;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.STBFactory;
import android.util.Log;

/**
 * 
 * @author Viktor Håkansson, Viktor von Zeipel
 *
 */
public class STBContainerTest extends TestCase {
	STB stb1 = STBFactory.get1();
	STB stb2 = STBFactory.get2();
	private LinkedList<STB> stbs = new LinkedList<STB>();
	
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		STBContainer.instance().reset();
	}

	public void testSetActiveSTB() {
		STBContainer.instance().setActiveSTB(stb1);
		assertEquals(stb1, STBContainer.instance().getActiveSTB());
	}

	public void testIsActiveSTB() {
		STBContainer.instance().setActiveSTB(stb1);
		assertTrue(STBContainer.instance().isActiveSTB(stb1));

	}

	public void testRemoveSTB() {
		STBContainer.instance().addSTB(stb1);
		STBContainer.instance().addSTB(stb2);
		stbs.add(stb1);
		stbs.add(stb2);
		assertTrue(stbs.contains(stb1));
		assertTrue(stbs.contains(stb2));
		STBContainer.instance().removeSTB(stb1);
		assertTrue(STBContainer.instance().getSTBs().length==1);
	}

	public void testContainsSTB() {
		STBContainer.instance().addSTB(stb1);
		STBContainer.instance().addSTB(stb2);
		assertTrue(STBContainer.instance().containsSTB(stb1));
		assertTrue(STBContainer.instance().containsSTB(stb2));
	}

	public void testIterator() {
		STBContainer.instance().addSTB(stb1);
		STBContainer.instance().addSTB(stb2);
		Iterator stbsa = STBContainer.instance().iterator();
		stbs.add(stb1);
		stbs.add(stb2);
		assertTrue(stbs.iterator().next()==stbsa.next());
	}

	public void testGetSTBs() {
		STB[] stbsa = new STB[2];
		stbsa[0]=stb1;
		stbsa[1]=stb2;
		STBContainer.instance().addSTB(stb1);
		STBContainer.instance().addSTB(stb2);
		Log.i("dd", "viktor"+STBContainer.instance().getSTBs());
		Log.i("cc", "viktor"+STBContainer.instance().getSTBs());
		assertTrue(STBContainer.instance().getSTBs()[0]==stbsa[0]);
		assertTrue(STBContainer.instance().getSTBs()[1]==stbsa[1]);
	}

	public void testReset() {
		STBContainer.instance().addSTB(stb1);
		STBContainer.instance().addSTB(stb2);
		assertTrue(STBContainer.instance().containsSTB(stb1));
		assertTrue(STBContainer.instance().containsSTB(stb2));
		STBContainer.instance().reset();
		int l = STBContainer.instance().getSTBs().length;
		System.out.print(l);	
		assertTrue(STBContainer.instance().getSTBs().length==0);
	}

}
