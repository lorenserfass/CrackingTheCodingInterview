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
	 * @param x An array sorted in ascending order which has been rotated.
	 * @param v Value sought.
	 * @return an index i where x[i] == v, if i exists. (Otherwise returns -1)
	 */
	static int Ex11_3_searchRotatedArray(int[] x, int v) {
		// find rotation. x[r] will be the smallest element of the array.
		int r = 0;
		for (int i = 0; i < x.length - 1; i++)
			if (x[i] > x[i + 1]) {
				r = i + 1;
				break;
			}
		
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
