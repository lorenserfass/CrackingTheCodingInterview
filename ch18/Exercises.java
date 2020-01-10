import java.util.Random;
import java.util.Arrays;

class Exercises {
	

	private static void swap(int[] x, int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}

	/**
	 * Ex 18.2
	 * @param x
	 */
	public static void shuffle(int[] x) {
		Random random = new Random();
		int n = x.length;
		for (int i = 0; i < n; i++) {
			int r = i + random.nextInt(n - i);
			swap(x, i, r);
		}
	}
	
	/**
	 * Ex. 18.3
	 * Return a sample of size m taken from x.
	 * This uses extra space so as not to change order of x.
	 * @param x
	 * @param m
	 * @return	m integers chosen from x without replacement
	 */
	public static int[] sample(int[] x, int m) {
		Random random = new Random();
		int n = x.length;
		int[] y = Arrays.copyOf(x, x.length);
		for (int i = 0; i < m; i++)
			swap(y, i, i + random.nextInt(n - i));
		return Arrays.copyOf(y, m);
	}
	
	
	/**
	 * Ex 18.4
	 * @param n non-negative integer
	 * @param d integer between 0 and 9 inclusive
	 * @return Count the number of times d occurs in all the numbers 0-n inclusive.
	 */
	public static int countDigit(int n, int d) {
		if (n == 0 && d == 0) return 1;
		if (d == 0) d = 10;
		int i = 0; // 0 for the rightmost digit, 1 for the 10s digit, 2 for the 100s digit, etc.
		int pow10 = 1; // 10^i
		int count = 0; // running total of the 2s as we move leftward in the number

		while (pow10 <= n) {
			int digit = (n % (10 * pow10)) / pow10; // isolate digit i of n
			count += digit * i * pow10 / 10; // num2s(9) = 1. num2s(99): 20. num2s(999) = 300. num2s(9999) = 4000
			if      (digit == d) count += n % pow10 + 1;
			else if (digit >  d) count += pow10;

			pow10 *= 10;
			i++;
		}

		return count;
	}

	private static int partition(int[] x, int lo, int hi) {
		// code for Quicksort from Sedgewick algorithms book
		int i = lo, j = hi + 1;
		int v = x[lo];
		while (true) {
			while (x[++i] < v) if (i == hi) break;
			while (x[--j] > v) if (j == lo) break;
			if (i >= j) break;
			swap(x, i, j);
		}
		swap(x, lo, j);
		return j;
	}

	private static void smallest(int[] x, int lo, int hi, int n) {
		if (hi <= lo) return;
		int j = partition(x, lo, hi); // now index j is "in place" for a sorted array, and x[lo..j-1] <= x[j] <= x[j+1..hi]
		int numLeft = j - lo + 1; // number of indices, including j, placed on the left side of the partition
		if (numLeft > n) // if you comment these lines out, it becomes quicksort
			smallest(x, lo,    j-1, n);
		else if (numLeft < n) // if you comment these lines out, it becomes quicksort
			smallest(x, j+1,   hi,  n - numLeft);
		else return; // if you comment these lines out, it becomes quicksort
	}
	
	/**
	 * Ex 18.6
	 * Moves the smallest n integers of the array to the left.
	 * @param array
	 * @param n
	 * @return
	 */
	public static void smallest(int[] array, int n) {
		shuffle(array);
		smallest(array, 0, array.length - 1, n);
	}

	/**
	 * Do Ex 18.6 by sorting the whole array.
	 * (I'm hoping this is slower for large arrays).
	 * @param array
	 * @param n
	 * @return
	 */
	public static void smallestForTesting(int[] array, int n) {
		Arrays.sort(array);
	}
		

	public static void main(String[] args) {

		/*Random r = new Random();
		for (int i = 0; i < 10; i++) {
			int[] original = new int[arraySize];
			for (int j = 0; j < arraySize; j++)
				original[j] = r.nextInt(upperBound);
			int[] array2 = Arrays.copyOf(original, arraySize);
			int[] test = smallestForTesting(array2, n);
			smallest(array2, n);
			int[] smallest = Arrays.copyOf(array2, n);
			Arrays.sort(smallest);
			for (int j = 0; j < n; j++)
				if (test[j] != smallest[j]) {
					System.out.println("wrong answer for: " + Arrays.toString(original));
					break;
				}
		}*/
		



	}

}
