class Hanoi {

	MyStack<Integer> a;
	MyStack<Integer> b;
	MyStack<Integer> c;

	private void moveDisks(int n, MyStack<Integer> start, MyStack<Integer> middle, MyStack<Integer> end) {
		if (n == 0) return;
		moveDisks(n - 1, start, end, middle);
		end.push(start.pop());
		moveDisks(n - 1, middle, start, end);
	}

	// fill stack "a" with integers, move to stack "c" via "b"
	public Hanoi(int n) {
		if (n > 10) return;

		a = new MyStack<Integer>();
		b = new MyStack<Integer>();
		c = new MyStack<Integer>();

		for (int i = n; i >= 1; i--) a.push(i);
		moveDisks(n, a, b, c);
	}
}

