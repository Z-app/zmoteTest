package se.z_app.social.zchat.test;

import java.sql.Date;
import java.util.ArrayList;

import se.z_app.social.Feed;
import se.z_app.social.Post;
import se.z_app.social.zchat.ZChatAdapter;
import se.z_app.stb.Channel;
import se.z_app.stb.EPG;
import se.z_app.stb.Program;
import se.z_app.stb.STB;
import se.z_app.stb.api.EPGData;
import se.z_app.stb.api.STBContainer;
import se.z_app.test_utils.STBFactory;
import se.z_app.zmote.epg.EPGContentHandler;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Unit test for the ZChatAdapter class
 * @author Marcus Widegren
 *
 */
public class ZChatAdapterTest extends AndroidTestCase{
	public void setUp() {
		STB theSTB = STBFactory.getProxy();
		STBContainer.instance().setActiveSTB(theSTB);
		EPGContentHandler.setContext(this.getContext());
	}
	
	public void testGetFeed() {
		Feed theFeed = new Feed(new Program(new Channel()));
		ZChatAdapter theZAdapter = new ZChatAdapter();
		EPG theEPG = EPGData.instance().getEPG();
		ArrayList<Feed> theFeeds = new ArrayList<Feed>();
		assertTrue(theEPG != null);
		for(Channel theChannel : theEPG) {
			//Log.e("In for", "In for");
			for(Program theProgram : theChannel) {
				Feed theNewFeed = new Feed(theProgram);
				
				Post thePost = new Post();
				thePost.setContent("Some random post about " + theProgram.getName());
				thePost.setUserName("Marcus");
				thePost.setLastUpdate(Date.valueOf("2012-11-17"));
				
				theNewFeed.addPost(thePost);
				theFeeds.add(theNewFeed);
				theZAdapter.commitPost(theNewFeed, thePost);
				//Log.e("Program name: ", theProgram.getName());
			}
		}
		for(Feed theFeedOut: theFeeds) {
			Log.e(theFeedOut.getProgram().getName(), theFeedOut.iterator().next().getContent());
			Feed serverFeed = theZAdapter.getFeed(theFeedOut.getProgram());
			if(serverFeed != null)
			Log.e(serverFeed.getProgram().getName(), serverFeed.iterator().next().getContent());
		}
		//Log.e("After test", "After test");
	}
}
