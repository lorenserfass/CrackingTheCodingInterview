class Stack<T> {
	Node<T> top = null;
	private int size = 0;

	public void push(T val) {
		size++;
		Node<T> t = new Node(val);
		t.next = top;
		top = t;
	}

	public T pop() {
		if (top == null)
			throw new java.util.NoSuchElementException();

		T t = top.value;
		top = top.next;
		size--;
		return t;
	}


	public T peek() {
		if (top == null)
			throw new java.util.NoSuchElementException();

		return top.value;
	}

	public int size() { return size; }

}
