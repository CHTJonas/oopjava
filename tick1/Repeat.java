package uk.ac.cam.chtj2.oopjava.tick1;

public class Repeat {
	public static void main(String[] args) {
		System.out.println(parseAndRep(args));
	}
	   /*
	    * Return the first string repeated by the number of times
	    * specified by the integer in the second string, for example
	    *
	    *    parseAndRep(new String[]{"one","3"})
	    *
	    * should return "one one one". Adjacent copies of the repeated
	    * string should be separated by a single space.
	    *
	    * Return a suitable error message in a string when there are
	    * not enough arguments in 'args' or the second argument is
	    * not a valid positive integer. For example:
	    *
	    *  - parseAndRep(new String[]{"one"}) should return
	    *    "Error: insufficient arguments"
	    *
	    *  - parseAndRep(new String[]{"one","five"}) should return
	    *    "Error: second argument is not a positive integer"
	    */
	public static String parseAndRep(String[] args) {
		//TODO
		if (args.length < 2) return "Error: insufficient arguments";
		int num;
		try {
			num = Integer.parseInt(args[1]);
			if (num < 1) return "Error: second argument is not a positive integer";
		} catch (NumberFormatException e) {
			return "Error: second argument is not a positive integer";
		}
		String str = args[0];
		for (int i=2; i<=num; i++) {
			str += " " + args[0];
		}
		return str;
	}
}
