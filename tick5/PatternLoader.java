package uk.ac.cam.chtj2.oopjava.tick5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

public class PatternLoader {
	public static List<Pattern> load(Reader r) throws IOException {
		List<Pattern> resultList = new LinkedList<Pattern>();
		BufferedReader buff = new BufferedReader(r);
		String readString = buff.readLine();
		while (readString != null) {
			Pattern p;
			try {
				p = new Pattern(readString);
				resultList.add(p);
			} catch (PatternFormatException e) {
				// Ignore any patterns in the list which are incorrectly formatted
				resultList.add(null);
				continue;
			}
			finally {
				readString = buff.readLine();
			}
		}
		return resultList;
	}
	
	public static List<Pattern> loadFromURL(String url) throws IOException {
		URL destination = new URL(url);
		URLConnection conn = destination.openConnection();
		return load(new InputStreamReader(conn.getInputStream()));
	}
	
	public static List<Pattern> loadFromDisk(String filename) throws IOException {
		return load(new FileReader(filename));
	}
}