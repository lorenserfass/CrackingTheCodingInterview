class MedianTracker {

	private MyBSTMultiset<Double> bst;
	
	public void insert(double x) {
		bst.insert(x);
	}
	
	public double median() {
		int sz = bst.size();
		if (sz % 2 == 1)
			return bst.getKeyAtIndex(sz / 2);
		else
			return (bst.getKeyAtIndex(sz/2) + bst.getKeyAtIndex(sz/2 - 1)) / 2.0;
	}
	
	public MedianTracker() {
		bst = new MyBSTMultiset<>();
	}
	
	
	public static void main(String[] args) {
		
	}
}
