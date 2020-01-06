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
