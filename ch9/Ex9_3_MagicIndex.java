
public class Ex9_3_MagicIndex {


	/**
	 * Search the whole array in sequence.
	 * Use this if values are not distinct.
	 * @param arr
	 * @return
	 */
	public static int magicIndexLinear(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == i) return i;
		}
		return -1; // no magic index
	}


	private static int magicIndexRecursive(int[] arr, int start, int end) {
		// binary search with different criteria
		if (start > end) return -1; // unsuccessful search

		int middle = (start + end) / 2;
		// test if middle is a magic index

		if (arr[middle] < middle)
			return magicIndexRecursive(arr, middle + 1, end);

		else if (arr[middle] > middle)
			return magicIndexRecursive(arr, start, middle - 1);

		else return middle; // middle == arr[middle]
	}

	/**
	 * Like binary search, but with different comparisons.
	 * @param arr
	 * @return
	 */
	public static int magicIndexLogN(int[] arr) {
		return magicIndexRecursive(arr, 0, arr.length - 1);
	}


}


