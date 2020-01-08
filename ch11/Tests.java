import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

class Tests {


	/**
	 * Read file of anagram words that are sorted alphabetically.
	 * Runs function to sort the list into groups of anagrams.
	 * @throws IOException
	 */
	static void Ex11_2_TEST() throws IOException {
		ArrayList<String> strings = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("anagrams.txt"));
		String line;
		while ((line = br.readLine()) != null)
			strings.add(line);
		br.close();
		
		Exercises.Ex11_2_quicksortAnagram(strings);
		for (String s : strings)
			System.out.println(s);
	}
	
	/**
	 * Make a somewhat random matrix where each row and column is in sorted order.
	 * (Duplicates are possible). Will include both positive and negative numbers.
	 * @param rows Number of rows
	 * @param cols Number of columns
	 * @return
	 */
	static int[][] makeIncreasingMatrix(int rows, int cols) {
		int[][] mat = new int[rows][cols];
		Random r = new Random();
		
		mat[0][0] = r.nextInt(100) - 30;
		for (int i = 1; i < cols; i++)
			mat[0][i] = mat[0][i - 1] + r.nextInt(5);
		for (int i = 1; i < rows; i++)
			mat[i][0] = mat[i - 1][0] + r.nextInt(5);
		
		for (int i = 1; i < rows; i++)
			for (int j = 1; j < cols; j++)
				mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]) + r.nextInt(5);
		
		return mat;
	}
	
	/**
	 * @param x a (positive?) number
	 * @param y a (positive?) number
	 * @return the gcd
	 */
	static int gcd(int x, int y) {
		int r;
		// TODO: understand this
		while (y > 0) {
			r = x % y;
			x = y;
			y = r;
		}
		return x;
	}
	
	
	static void rotateArrayInPlace(int[] x, int r) {
		// deal with annoying negative r.  (-3 mod 10 should be 7, not -3):
		r %= x.length;
		if (r < 0) r = x.length + r;

		int numSets = gcd(x.length, r);
		int setSize = x.length / numSets;
		
		// This double loop is linear time because numSets * setSize == x.length
		r = (x.length - r) % x.length; //  :-(
		for (int i = 0; i < numSets; i++) {
			// rotate a cycle
			int t = x[i];
			for (int j = 0; j < setSize - 1; j++) {
				x[(i + r * j) % x.length] = x[(i + r * (j + 1)) % x.length];
			}
			x[(i - r + x.length) % x.length] = t;
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			StringBuilder sb = new StringBuilder();
			for (int col = 0; col < matrix[0].length; col++)
				sb.append(String.format("%1$5s", matrix[row][col]));
			System.out.println(sb.toString());
		}
	}
	
	public static void main(String[] args) throws IOException {
		for (int i = -3; i < 15; i++) {
			int[] x = new int[12];
			for (int j = 0; j < 12; j++) x[j] = j;
			rotateArrayInPlace(x, i);
			System.out.println("rotating by " + i + ": " + Arrays.toString(x));
		}
		
		
		// make 100 random "increasing" matrices.
		// search using both methods and compare results.
		for (int i = 0; i < 100; i++) {
			int[][] mat = makeIncreasingMatrix(20, 20);
			for (int j = -30; j < 200; j++) {
				Exercises.Coords s1 = Exercises.Ex11_6_searchMatrix(mat, j);
				Exercises.Coords s2 = Exercises.Ex11_6_searchMatrixSlow(mat, j);
				assert (s1 == null && s2 == null) || (mat[s1.row][s1.col] == j);
			}
		}
		
		for (int i = -20; i < 20; i++) {
			System.out.format("mod(%s, 7) = %s\n", i, Exercises.mod(i, 7));
		}
		
	}
}
