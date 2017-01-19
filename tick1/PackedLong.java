package uk.ac.cam.chtj2.oopjava.tick1;
		
public class PackedLong {
			
   /*
    * Unpack and return the nth bit from the packed number at index position;
    * position counts from zero (representing the least significant bit)
    * up to 63 (representing the most significant bit).
    */
   public static boolean get(long packed, int position) {
   // set "check" to equal 1 if the "position" bit in "packed" is set to 1
   if ((packed < 0) & (position == 63)) {
   	return true;
   }
   /*		OLD BIT
   int num = 62 - position;
   long check = packed << num;
   check = check >> 62;
   return (check == 1);
   */
   else {
		int num = 63 - position;
		long check = packed << num;
		check = check >>> 63;
		return (check == 1);
   }
   }

   /*
    * Set the nth bit in the packed number to the value given
    * and return the new packed number
    */
   public static long set(long packed, int position, boolean value) {
      if (value) {
         // TODO: complete this
         // update the value "packed" with the bit at "position" set to 1
         long lefty = packed >> position;
         long righty = packed ^ (lefty << position);
         lefty = lefty | 1;
         lefty = lefty << position;
         return lefty | righty;
      }
      else {
         // TODO: complete this
         // update the value "packed" with the bit at "position" set to 0
         long lefty;
         if (position == 63) {
         	lefty = 0;
         }
         else {
         	lefty = packed >>> (position+1);
         	lefty = lefty << (position+1);
         	}
         if (position == 0) {
         	return lefty;
         }
         else {
         	long righty = packed << (64 - position);
         	righty = righty >>> (64 - position);
         	return lefty | righty;
         }
      }
      //return packed;
   }
}
