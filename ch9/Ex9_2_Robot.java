import java.util.ArrayList;

public class Ex9_2_Robot {


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

