
public class Ex11_8_intTracker {
	
	private MyBSTMultiset<Integer> bst = new MyBSTMultiset<Integer>();
	
	public void track(int x) {
		bst.insert(x);
	}
	
	public int getRankOfNumber(int x) {
		return bst.rank(x);
	}

	public static void main(String[] args) {
		int[] x = {5, 1, 4, 4, 5, 9, 7, 13, 3};
		
		Ex11_8_intTracker tracker = new Ex11_8_intTracker();
		for (int i : x) tracker.track(i);
		
		// Book says the results are 0, 1, 3, not 1, 2, 4.
		// The wording of the question is strange.
		// "getRankOfNumber returns the number of values less than or equal to x
		// (not including x itself"
		System.out.println("rank(1) = " + tracker.getRankOfNumber(1));
		System.out.println("rank(3) = " + tracker.getRankOfNumber(3));
		System.out.println("rank(4) = " + tracker.getRankOfNumber(4));

	}

}
