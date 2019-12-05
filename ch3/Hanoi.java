class Hanoi {

	Stack<Integer> a;
	Stack<Integer> b;
	Stack<Integer> c;

	private void moveDisks(int n, Stack start, Stack middle, Stack end) {
		if (n == 0) return;
		moveDisks(n - 1, start, end, middle);
		end.push(start.pop());
		moveDisks(n - 1, middle, start, end);
	}

	// fill stack "a" with integers, move to stack "c" via "b"
	public Hanoi(int n) {
		if (n > 10) return;

		a = new Stack<Integer>();
		b = new Stack<Integer>();
		c = new Stack<Integer>();

		for (int i = n; i >= 1; i--) a.push(i);
		moveDisks(n, a, b, c);
	}
}

