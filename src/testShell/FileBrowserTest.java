package testShell;

import java.io.File;
import java.util.LinkedList;

import android.os.Environment;
import android.util.Log;

import junit.framework.TestCase;

public class FileBrowserTest extends TestCase {

	public void testListing(){
		
		LinkedList<File> mediaFiles = new LinkedList<File>();
		LinkedList<File> files = new LinkedList<File>();
		 
		
		files.addLast(Environment.getExternalStorageDirectory());
		files.addLast(Environment.getDataDirectory());
		files.addLast(Environment.getDownloadCacheDirectory());
		
		Log.i("Files", "Starting scan...");
		long time = System.currentTimeMillis();
		while(!files.isEmpty()){
			File file = files.removeFirst();
		
			File children[] = file.listFiles();
			if(children != null){
				for (File child : children) {
					if(!child.isHidden()){
						if(child.isDirectory()){
							files.addLast(child);
						}
						else{
							String filename = child.getName().toLowerCase();
							if(filename.endsWith(".mp4") || filename.endsWith(".mp3")){
								mediaFiles.add(child);
								Log.i("Files", "Found file...");
							}
						}
					}
				}
			}
		}
		
		Log.i("Files", "Scan time: " + (System.currentTimeMillis()-time));
		for (File file : mediaFiles) {
			Log.i("Files", "Found: " + file.getAbsolutePath());
		}
		
		
	}
}
