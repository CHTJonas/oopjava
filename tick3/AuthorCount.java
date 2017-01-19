package uk.ac.cam.chtj2.oopjava.tick3;

public class AuthorCount implements Comparable<AuthorCount> {
	private String author;
	private int count;
	
	public AuthorCount(String author) {
		this.author = author;
		this.count = 1;
	}
	
	public void inc() {
		count++;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		for (int i = 0; i < author.length(); i++) {
		    hash = hash*31 + author.charAt(i);
		}
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof AuthorCount && ((AuthorCount) obj).author.equals(this.author);
	}
	
	@Override
	public int compareTo(AuthorCount o) {
		//      return -1 if 'this' rank orders before 'o', 
		//      return 0 if 'this' and 'o' are equal,
		//      return 1 if 'this' rank orders after 'o'
		//      to do so order first by 'count' (largest first), and if counts
		//      are equal, then order by 'name' as a tie-breaker.
		int cmp = o.count > this.count ? +1 : o.count < this.count ? -1 : 0;
		if (cmp == 0) {
			cmp = - o.author.compareTo(this.author);
		}
		return cmp;
	}
	
	@Override
	public String toString() {
		String prerelease = author;
		while (prerelease.length() < 20) {
			prerelease = prerelease + " ";
		}
		String postrelease = Integer.toString(count);
		while (postrelease.length() != 3) {
			postrelease = " " + postrelease;
		}
		return prerelease + postrelease;
	}
}