public class HelperFunctions {

	/**
	 * Integer log2(n). For example, log2(10) = 3.
	 * @param n
	 * @return
	 */
	public static int log2(int n) {
		if(n <= 0) throw new IllegalArgumentException();
		return 31 - Integer.numberOfLeadingZeros(n);
	}

	public static int powerOfTwo(int power) {
		return 1 << power;
	}

	/**
	 * The largest power of 2 that is <= n
	 * @param n
	 * @return
	 */
	public static int floorPowerOfTwo(int n) {
		if (n <= 0) throw new IllegalArgumentException();
		return 1 << (31 - Integer.numberOfLeadingZeros(n));
	}


}
