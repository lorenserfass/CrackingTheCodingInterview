import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class Exercises {
	
	public static class IntPair {
		final int a;
		final int b;
		public IntPair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public String toString() {
			return "IntPair [a=" + a + ", b=" + b + "]";
		}
	}


	private static String[] counts = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
			"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	private static String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	private static String[] thousandPowers = {"", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion", "sextillion", "septillion", "octillion"};

	/**
	 * Ex 17.1
	 * Swap two integers (here, in an array) without a temp variable.
	 * @param x
	 * @param i
	 * @param j
	 */
	public static void swapWithoutTemp(int[] x, int i, int j) {
		x[i] = x[i] ^ x[j];
		x[j] = x[i] ^ x[j];
		x[i] = x[i] ^ x[j];
	}
	

	
	/**
	 * Ex 17.3
	 * @param n the number taken factorial of
	 * @return the number of trailing zeros in n factorial
	 */
	public static int factorialTrailingZeros(int n) {
		int count2 = 0; // number of 2's in fact(n)'s prime factorization
		int factor = 2;
		while (factor <= n) {
			count2 += n / factor;
			factor *= 2;
		}
		
		int count5 = 0; // number of 5's in fact(n)'s prime factorization
		factor = 5;
		while (factor <= n) {
			count5 += n / factor;
			factor *= 5;
		}

		return Math.min(count2, count5);
	}
	
	
	/**
	 * Ex 17.6
	 * Find indices m and n so that if x[m]..x[n] inclusive were sorted, x would be sorted.
	 * TODO: more testing.
	 * Maybe there's a more clever, efficient way.
	 * This just copies the array, sorts it, and compares the arrays.
	 * This is boring but may be useful for testing.
	 * @param x
	 */
	public static IntPair findInterval(int[] x) {
		int[] y = Arrays.copyOf(x, x.length);
		
		Arrays.sort(y);
		
		int lo = 0;
		int hi = x.length - 1;
		
		while (x[lo] == y[lo]) lo++;
		if (lo == x.length)
			return new IntPair(0, 0);

		while (x[hi] == y[hi]) hi--;
		
		return new IntPair(lo, hi);
	}

	
	/**
	 * Ex 17.7
	 * Prints the number in English.
	 * TODO: NOT DONE. ROUGH DRAFT.
	 * @param x
	 * @return
	 */
	public static String numberInEnglish(int x) {

		if (x == 0) return "zero";

		String s = "";

		int thousandPower = 0;
		while (x != 0) {
			int r = x % 1000;
			x /= 1000;
			String s2 = "";
			if (r >= 100) {
				s2 = counts[r / 100] + " hundred";
				r %= 100;
			}
			if (r < 20 && r > 0) {
				s2 = s2 + " " + counts[r];
			}
			else if (r > 0) {
				s2 = s2 + " " + tens[r / 10];
				r %= 10;
				if (r != 0)
					s2 = s2 + "-" + counts[r];
			}
			if (thousandPower > 0)
				s2 = s2 + " " + thousandPowers[thousandPower];
			s = s2 + " " + s;
			thousandPower++;
		}

		return s;
	}


	private static int rand5() {
		return new Random().nextInt(5);
	}
	
	private static int rand4() {
		int r;
		do { r = rand5(); } while (r == 4);
		return r;
	}
	
	private static int rand8() {
		int r = rand4();
		if (rand4() % 2 == 0)
			return r + 4;
		else
			return r;
	}

	/**
	 * Ex 17.11
	 * Uses rand5() to generate rand7()
	 * @return A random number between 0 and 6 (inclusive) with equal probability.
	 */
	public static int rand7() {
		int r;
		do r = rand8(); while (r == 7);
		return r;
	}
	
	
	/**
	 * Ex. 17.12
	 * Find all pairs of integers in array that have a target sum.
	 * NOT DONE
	 * @param x
	 * @param target
	 */
	public void allPairsThatSum(int[] x, int target) {
		int[] y = Arrays.copyOf(x, x.length);
		Arrays.sort(y);
		int lo = 0;
		int hi = x.length - 1;
		
		// TODO
		
	}

	

	public static void main(String[] args) {



		for (int i = 0; i <= 100; i++) {
			System.out.println(numberInEnglish(i));
		}
		System.out.println(numberInEnglish(123456789));

		int[] counts = new int[7];
		for (int i = 0; i < 10000; i++) counts[rand7()]++;
		System.out.println(Arrays.toString(counts));
		
		
		// test 17.6
		int[] x = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
		System.out.println(findInterval(x));
	}

}
