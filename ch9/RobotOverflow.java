import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class RobotOverflow {

	public static int factorial(int n) {
		int f = 1;
		for (int i = 1; i <= n; i++) {
			if ((f * i) / i != f) {
				throw new RuntimeException();
			}
			f *= i;
		}
		return f;
	}


	public static int choose(int n, int k) {
		if (n - k < k) k = n - k;
		int r = 1;
		for (int d = 1; d <= k; d++) {
			if ((r * n) / r != n) {
				throw new RuntimeException();
			}
			r *= n--;
			r /= d;
		}
		return r;
	}

	public static int robot1(int n, int m) {
		return factorial(n + m - 2) / (factorial(n - 1) * factorial(m - 1));
	}

	public static int robot2(int n, int m) {
		return choose(n + m - 2, n - 1);
	}

	public static int robot3(int n, int m) {
		int[] row = new int[m];
		Arrays.fill(row, 1);

		for (int i = 1; i < n; i++) {
				int[] row2 = new int[m];
				row2[0] = 1;
				for (int j = 1; j < m; j++) {
					if (row2[j-1] + row[j] < 0) {
						throw new RuntimeException();
					}
					row2[j] = row2[j-1] + row[j];
				}
				row = row2;
		}

		return row[m - 1];
	}

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("robots.csv");
		fw.write("function_name,m,n\n");
		
		for (int n = 1; n <= 50; n++) {
			for (int k = 1; k < n; k++) {

				try {
					robot1(k, n - k);
					fw.write("1. factorial robot," + k + "," + (n - k) + "\n");
				}
				catch(Exception e) { }

				try {
					robot2(k, n - k);
					fw.write("2. smart choose robot," + k + "," + (n - k) + "\n");
				}
				catch(Exception e) { }

				try {
					robot3(k, n - k);
					fw.write("3. DP robot," + k + "," + (n - k) + "\n");
				}
				catch(Exception e) { }

			}
		}
		
		fw.flush();
		fw.close();
	}

}
