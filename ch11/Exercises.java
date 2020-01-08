import java.util.Arrays;
import java.util.ArrayList;

class Exercises {
	
	public static class Coords {
		int row;
		int col;
		Coords(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 * @param x Sorted array
	 * @param v Value sought
	 * @return an index i where x[i] == v, if i exists. (Otherwise returns -1)
	 */
	static int binarySearch(int[] x, int v) {
		int l = 0, u = x.length - 1, m;
		while (l <= u) {
			m = (l + u) / 2;
			if (x[m] > v)
				u = m - 1;
			else if (x[m] < v)
				l = m + 1;
			else
				return m;
		}
		return -1;
	}
	
	
	/**
	 * mod(-1, 5) = 4
	 * @param n
	 * @param modulus
	 * @return
	 */
	public static int mod(int n, int modulus) {
		if (n >= 0)
			return n % modulus;
		else {
			int blah = (-n) % modulus;
			return (modulus - blah) % modulus;
		}
	}
	
	/**
	 * In a rotated sorted array, find the start of the rotation.
	 * (The index with negative delta to the preceding index.)
	 * @param x Sorted (ascending) array that has been rotated
	 * @return Index beginning the rotation
	 */
	public static int findRotation(int[] x) {
		int rot = findRotation(x, x.length, 0, x.length - 1);
		return (rot == -1) ? 0 : rot;
	}

	
	private static int findRotation(int[] x, int n, int lo, int hi) {
		// TODO: this is real messy.
		if (lo > hi) return -1;
		if (lo == hi) {
			if (delta(x, lo) >= 0)
				return -1;
			else
				return lo;
		}
		if (hi - lo == 1) {
			if (delta(x, hi) < 0)
				return hi;
		}

		int m = (lo + hi) / 2;
		if (delta(x, m) < 0) return m;

		if (x[lo] > x[mod(m - 1, n)])
			return findRotation(x, n, lo, mod(m - 1, n));
		if (x[mod(m + 1, n)] > x[hi])
			return findRotation(x, n, mod(m + 1, n), hi);

		// at this point, we may have to check both subarrays
		int left = findRotation(x, n, lo, mod(m - 1, n));
		int right = findRotation(x, n, mod(m + 1, n), hi);

		if (left != -1)  return left;
		if (right != -1) return right;
		return -1;
	}	

	/**
	 * Value of index i minus value at i-1. (wraps around)
	 * @param x
	 * @param i
	 * @return
	 */
	public static int delta(int[] x, int i) {
		// TODO: bounds checking
		if (i == 0) return x[0] - x[x.length - 1];
		return x[i] - x[i-1];
		// return x[i] - x[mod(i - 1, x.length)]; // could use Math.floorMod
	}
	
	/**
	 * @param x An array sorted in ascending order which has been rotated.
	 * @param v Value sought.
	 * @return an index i where x[i] == v, if i exists. (Otherwise returns -1)
	 */
	static int Ex11_3_searchRotatedArray(int[] x, int v) {
		// find rotation. x[r] will be the smallest element of the array.
		// TODO: the loop below is linear time, defeating the purpose
		// of the binary search below it. Correct by searching for
		// the array's starting point using binary search as well.
		// Still, this version is useful for testing.
		/*int r = 0;
		for (int i = 0; i < x.length - 1; i++)
			if (x[i] > x[i + 1]) {
				r = i + 1;
				break;
			}*/
		int r = findRotation(x);
		
		// iterative binary search with modular offset
		int l = 0, u = x.length - 1, m, mr;
		while (l <= u) {
			m = (l + u) / 2;
			mr = (m + r) % x.length;
			if      (x[mr] > v) u = m - 1;
			else if (x[mr] < v) l = m + 1;
			else                return mr;
		}
		return -1;
	}


	static <T> void swap(ArrayList<T> x, int i, int j) {
		T t = x.get(i);
		x.set(i, x.get(j));
		x.set(j, t);
	}

	public static String sortString(String s) { // return a new string, lower case and with characters sorted
		char[] s1 = s.toLowerCase().toCharArray();
		Arrays.sort(s1);
		return new String(s1);
	}

	/**
	 * Sort a list of strings into anagram groups.
	 * @param x
	 */
	public static void Ex11_2_quicksortAnagram(ArrayList<String> x) {
		Ex11_2_quicksortAnagram(x, 0, x.size() - 1);
	}

	private static void Ex11_2_quicksortAnagram(ArrayList<String> x, int l, int u) {
		if (l >= u) return;
		int m = l;
		for (int i = l + 1; i <= u; i++) // partition. TODO: understand this
			if (sortString(x.get(i)).compareTo(sortString(x.get(l))) < 0)
				swap(x, ++m, i);
		swap(x, m, l); // put partitioning element where it belongs
		Ex11_2_quicksortAnagram(x, l, m - 1);
		Ex11_2_quicksortAnagram(x, m + 1, u);
	}
	
	
	public static Coords Ex11_6_searchMatrix(int[][] matrix, int value) {
		return searchMatrix(matrix, value, 0, matrix.length - 1, 0, matrix[0].length - 1);
	}
	
	private static Coords searchMatrix(int[][] matrix, int value, int vl, int vu, int hl, int hu) {
		if (vl > vu || hl > hu)
			return null; // signifies that the value was not found

		int hm = (hl + hu) / 2; // horizontal middle
		int vm = (vl + vu) / 2; // vertical middle
		
		if (matrix[vm][hm] == value)
			return new Coords(vm, hm);
		
		Coords lowerLeft, upperRight, other;
		
		// in either case one quadrant is eliminated
		if (value < matrix[vm][hm]) {
			lowerLeft  = searchMatrix(matrix, value, vm, vu,     hl, hm - 1);
			upperRight = searchMatrix(matrix, value, vl, vm - 1, hm, hu);
			other      = searchMatrix(matrix, value, vl, vm - 1, hl, hm - 1);
		}
		else { // value < matrix[vm][hm]
			lowerLeft  = searchMatrix(matrix, value, vm + 1, vu, hl,     hm);
			upperRight = searchMatrix(matrix, value, vl,     vm, hm + 1, hu);
			other      = searchMatrix(matrix, value, vm + 1, vu, hm + 1, hu);
		}
		
		if (lowerLeft != null) return lowerLeft;
		if (upperRight != null) return upperRight;
		if (other != null) return other;
		
		return null;
	}

	/**
	 * for testing
	 * @param matrix
	 * @param value
	 * @return
	 */
	public static Coords Ex11_6_searchMatrixSlow(int[][] matrix, int value) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] == value)
					return new Coords(row, col);
			}
		}
		return null;
	}


}
