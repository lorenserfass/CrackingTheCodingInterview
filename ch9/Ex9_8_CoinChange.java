import java.util.Arrays;
import java.util.HashMap;

public class Ex9_8_CoinChange {
	
	// so I can use a pair of integers as a hash key
	static class IntPair {
		int a;
		int b;
		IntPair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		public boolean equals(Object other) {
			if (this == other) return true;
			if (!(other instanceof IntPair)) return false;
			IntPair key = (IntPair) other;
			return this.a == key.a && this.b == key.b;
		}
		
		public int hashCode() { return 31 * a + b; }
	}
	
	// so I can "call by reference" with an integer
	static class IntegerReference {
		int x;
		IntegerReference(int x) { this.x = x; }
	}

	/**
	 * Dynamic programming, iterative.
	 * @param values Values of the coin denominations (e.g. {1, 5, 10, 25})
	 * @param total
	 * @return The number of ways of making change for "total", with the given coin denomination values.
	 */
	public static int numberOfWays1(int[] values, int total) {
		Arrays.sort(values); // test for duplicates?
		int[][] ways = new int[total + 1][values.length]; // initialized to 0
		// ways[sum][denoms] is the number of ways of making "sum" cents using the first "denoms+1" denominations

		for (int i = 0; i < values.length; i++)
			ways[0][i] = 1; // 1 way of making 0 cents no matter how many denominations we allow

		for (int maxDenom = 0; maxDenom < values.length; maxDenom++) {
			for (int sum = 1; sum <= total; sum++) {
				if (maxDenom > 0)
					ways[sum][maxDenom] += ways[sum][maxDenom - 1];
				if (sum - values[maxDenom] >= 0)
					ways[sum][maxDenom] += ways[sum - values[maxDenom]][maxDenom];
			}
		}

		return ways[total][values.length - 1];
	}
	
	
	/**
	 * Recursive.
	 * Calculates only some cells, but many times each.
	 * @param values Values of the coin denominations (e.g. {1, 5, 10, 25})
	 * @param total
	 * @return
	 */
	public static int numberOfWays2(int[] values, int total) {
		return numberOfWays2(values, total, values.length - 1);
	}
	
	private static int numberOfWays2(int[] values, int total, int k) {
		if (total < 0 || k < 0) return 0;
		if (total == 0) return 1;
		
		return numberOfWays2(values, total, k - 1) +
				numberOfWays2(values, total - values[k], k);
	}

	
	/**
	 * Counting the number of times the recursive function is called.
	 * @param values Values of the coin denominations (e.g. {1, 5, 10, 25})
	 * @param total
	 * @return the number of ways of making change, together with the count of recursive function calls
	 */
	public static IntPair numberOfWays2_WithStats(int[] values, int total) {
		IntegerReference count = new IntegerReference(0);
		int ways = numberOfWays2_WithStats(values, total, values.length - 1, count); 
		return new IntPair(ways, count.x);
	}
	
	private static int numberOfWays2_WithStats(int[] values, int total, int k, IntegerReference count) {
		count.x++;

		if (total < 0 || k < 0) return 0;
		if (total == 0) return 1;
		
		return numberOfWays2_WithStats(values, total, k - 1, count) +
				numberOfWays2_WithStats(values, total - values[k], k, count);
	}


	/**
	 * Recursive, but with caching to reduce number of function calls.
	 * @param values Values of the coin denominations (e.g. {1, 5, 10, 25})
	 * @param total
	 * @return
	 */
	public static int numberOfWays3(int[] values, int total) {
		HashMap<IntPair, Integer> map = new HashMap<IntPair, Integer>();
		return numberOfWays3(values, total, values.length - 1, map);
	}
	
	private static int numberOfWays3(int[] values, int total, int k, HashMap<IntPair, Integer> map) {
		if (total < 0 || k < 0) return 0;
		if (total == 0) return 1;
		
		IntPair key = new IntPair(total, k);
		if (map.containsKey(key))
			return map.get(key);
		
		int numWays = numberOfWays3(values, total, k - 1, map) +
						numberOfWays3(values, total - values[k], k, map);
		
		map.put(key, numWays);
		return numWays;
	}
	
	
	
	public static IntPair numberOfWays3_WithStats(int[] values, int total) {
		IntegerReference count = new IntegerReference(0);
		HashMap<IntPair, Integer> map = new HashMap<IntPair, Integer>();
		int ways = numberOfWays3_WithStats(values, total, values.length - 1, map, count);
		return new IntPair(ways, count.x);
	}
	
	private static int numberOfWays3_WithStats(int[] values, int total,
			int k, HashMap<IntPair, Integer> map, IntegerReference count) {
		
		count.x++;
		if (total < 0 || k < 0) return 0;
		if (total == 0) return 1;
		
		IntPair blah = new IntPair(total, k);
		if (map.containsKey(blah))
			return map.get(blah);
		
		int numWays = numberOfWays3_WithStats(values, total, k - 1, map, count) +
						numberOfWays3_WithStats(values, total - values[k], k, map, count);
		
		map.put(blah, numWays);
		return numWays;
	}

	public static void main(String[] args) {
		int[] coinValues = {1, 5, 10, 25};
		System.out.println(numberOfWays1(coinValues, 200));
		System.out.println(numberOfWays2(coinValues, 200));
		
		IntPair results1 = numberOfWays2_WithStats(coinValues, 200);
		IntPair results2 = numberOfWays3_WithStats(coinValues, 200);
		
		System.out.println("Number of ways of making change: " + results1.a + ". Number of function calls: " + results1.b);
		System.out.println("Number of ways of making change: " + results2.a + ". Number of function calls: " + results2.b);
	}

}
