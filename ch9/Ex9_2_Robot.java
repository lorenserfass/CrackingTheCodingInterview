import java.util.ArrayList;
import java.util.Arrays;

public class Ex9_2_Robot {
	
	
	/**
	 * Calculates (n choose k), or factorial(n) / (factorial(k) * factorial(n - k))
	 * avoiding using factorials and with unlikely overflow.
	 * Time proportional to k, or to n-k, whichever is smaller.
	 * @param n
	 * @param k
	 * @return
	 */
	public static long choose(int n, int k) {
		if (k > n) return 0;
		if (n < 0 || k < 0) return 0;
		if (n - k < k) k = n - k;
		
		long r = 1;
		for (int d = 1; d <= k; d++) {
			r *= n--;
			r /= d;
		}
		return r;
	}


	/**
	 * Count number of paths from upper-left to lower-right in
	 * an X times Y board, when only down and right are allowed
	 * directions.
	 * This uses X * Y space.
	 * @param X
	 * @param Y
	 * @return number of paths
	 */
	public static int getNumPaths1(int X, int Y) {
		int[][] board;

		board = new int[X][Y];
		for (int i = 0; i < X; i++)
			board[i][0] = 1;
		for (int i = 0; i < Y; i++)
			board[0][i] = 1;

		for (int i = 1; i < X; i++) {
			for (int j = 1; j < Y; j++) {
				board[i][j] = board[i-1][j] + board[i][j-1];
			}
		}

		return board[X - 1][Y - 1];
	}

	/**
	 * Count number of paths from upper-left to lower-right in
	 * an X times Y board, when only down and right are allowed
	 * directions.
	 * This uses space proportional to Y and time proportional to X * Y.
	 * @param X
	 * @param Y
	 * @return number of paths
	 */
	public static int getNumPaths1_1(int X, int Y) {
		int[] row = new int[Y];
		Arrays.fill(row, 1);
		
		for (int x = 1; x < X; x++) {
			int[] rowx = new int[Y];
			rowx[0] = 1;
			for (int y = 1; y < Y; y++)
				rowx[y] = rowx[y - 1] + row[y];
			row = rowx;
		}
		return row[Y - 1];
	}
	
	
	/**
	 * Count number of paths from upper left to lower right in
	 * an X times Y board, when only down and right are allowed
	 * directions.
	 * This uses O(1) space, and time proportional to X or Y, whichever is smaller.
	 * @param X
	 * @param Y
	 * @return
	 */
	public static long getNumPaths1_2(int X, int Y) {
		return choose(X + Y - 2, X - 1);
	}

	public static int getNumPaths2(int X, int Y, ArrayList<Pair> unavailableSpots) {
		int[][] board;

		board = new int[X][Y];

		board[0][0] = 1;

		for (Pair p : unavailableSpots) {
			board[p.x][p.y] = -1; // -1 can be a signal that the spot is blocked
		}

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (board[i][j] == -1) continue;
				if (i == 0 && j == 0) continue; // TODO: this is annoying
				board[i][j] = 0;
				if (i > 0 && board[i - 1][j] != -1)
					board[i][j] += board[i - 1][j];
				if (j > 0 && board[i][j - 1] != -1)
					board[i][j] += board[i][j - 1];
			}
		}

		return board[X - 1][Y - 1];
	}
	
	

}

