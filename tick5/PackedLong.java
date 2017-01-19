package uk.ac.cam.chtj2.oopjava.tick5;
		
public class PackedLong {
	public static boolean get(long packed, int position) {
		int leftShift = 63 - position;
		long withSuffixBits = packed << leftShift;
		long check = withSuffixBits >>> 63;
		return (check == 1);
	}
	
	public static long set(long packed, int position, boolean value) {
		if (value) {
			long lefty = packed >> position;
			long righty = packed ^ (lefty << position);
			lefty = lefty | 1;
			lefty = lefty << position;
			long val = lefty | righty;
			return val;
		}
		else {
			long lefty = packed >> position;
			long righty = packed ^ (lefty << position);
			lefty = lefty >> 1;
			lefty = lefty << 1;
			lefty = lefty << position;
			return lefty | righty;
		}
	}
}
