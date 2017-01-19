package uk.ac.cam.chtj2.oopjava.tick3;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CollectionTest {
	public static List<AuthorCount> processWithList(List<Pattern> patterns) {
		List<AuthorCount> list = new LinkedList<AuthorCount>();
		for(Pattern p : patterns) {
			//TODO: determine if a suitable AuthorCount objects exists in
			//'list' if so, increment the count for this author; if not add
			//a suitable object to the list
			AuthorCount acObj = new AuthorCount(p.getAuthor());
			boolean found = false;
			for(AuthorCount ac : list) {
				if (ac.equals(acObj)) {
					found = true;
					ac.inc();
				}
			}
			if (!found) {
				list.add(acObj);
			}
		}
		//TODO: sort "list" and return it to caller.
		Collections.sort(list);
		return list;
	}
	
	public static List<AuthorCount> processWithMap(List<Pattern> patterns) {
		Map<String,AuthorCount> map = new HashMap<String,AuthorCount>();
		for(Pattern p : patterns) {
			//TODO: determine if 'map' contains author name as key
			//if so, increment associated AuthorCount object; if not add one.
			if (map.containsKey(p.getAuthor())) {
				AuthorCount a = map.get(p.getAuthor());
				a.inc();
				map.put(p.getAuthor(), a);
			} else {
				map.put(p.getAuthor(), new AuthorCount(p.getAuthor()));
			}
		}
		//TODO: copy set of AuthorCount objects associated with 'map' into
		//an ArrayList.  Sort the list of AuthorCount objects and return it
		//to the caller.
		ArrayList<AuthorCount> lst = new ArrayList<AuthorCount>();
		for (AuthorCount val: map.values()) {
			lst.add(val);
		}
		Collections.sort(lst);
		return lst;
	}
	
	public static void main(String[] args) {
		//TODO: write code in here to test both above implementations on the
		//pattern file http://www.cl.cam.ac.uk/teaching/current/OOProg/life.txt
		List<Pattern> pList = null;
		try {
			pList = PatternLoader.loadFromURL("http://www.cl.cam.ac.uk/teaching/current/OOProg/life.txt");
			//pList = PatternLoader.loadFromDisk("/Users/charlie/Documents/Uni/Work/CompSci/Java/IO/lifeReduced.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<AuthorCount> sortedList = processWithMap(pList);
		for (AuthorCount ac : sortedList) {
			System.out.println(ac.toString());
		}
		System.out.println();
		System.out.println();
		sortedList = processWithList(pList);
		for (AuthorCount ac : sortedList) {
			System.out.println(ac.toString());
		}
	}
}