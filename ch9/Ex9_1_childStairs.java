class Ex9_1_childStairs {

	public static int numberOfWaysOfRunningUpStairs(int n) {
		int[] x = new int[4]; // java initializes to 0
		x[3] = 1;
		for (int i = 0; i < n; i++) {
			System.arraycopy(x, 1, x, 0, 3);
			x[3] = x[0] + x[1] + x[2];
		}
		return x[3];
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(numberOfWaysOfRunningUpStairs(i));
		}
	}

}
