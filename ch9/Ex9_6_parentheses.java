class Ex9_6_parentheses {

	public static void printAllParenStrings(int n) {
		addParen("", 0, 0, n);
	}

	private static void addParen(String s, int open, int close, int n) {
		if (close == n) {
			System.out.println(s);
			return;
		}

		if (open < n)
			addParen(s + "(", open + 1, close, n);

		if (close < open)
			addParen(s + ")", open, close + 1, n);
	}

	public static void main(String[] args) {
		printAllParenStrings(3);

	}

}
