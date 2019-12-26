import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

class Exercises {
	
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
	




}
