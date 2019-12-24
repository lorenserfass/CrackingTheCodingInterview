class Ex9_6_parentheses {

	public static void printAllParenStrings(int n) {
		addParen("", 0, 0, n);
	}

	private static void addParen(String s, int opened, int closed, int n) {
		if (closed == n) {
			System.out.println(s);
			return;
		}

		if (opened < n)
			addParen(s + "(", opened + 1, closed, n);

		if (closed < opened)
			addParen(s + ")", opened, closed + 1, n);
	}

	public static void main(String[] args) {
		printAllParenStrings(4);
	}

}
