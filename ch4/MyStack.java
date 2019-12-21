class MyStack<T> {
	Node top = null;
	private int size = 0;
	
	class Node {
		Node next;
		T value;
		
		Node(T value) {
			next = null;
			this.value = value;
		}
	}

	public void push(T val) {
		size++;
		Node t = new Node(val);
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

	public void print() {
		// (prints from the top)
		Node node = top;
		while (node != null) {
			System.out.println(node.value);
			node = node.next;
		}
	}

	public int size() { return size; }

	public boolean isEmpty() { return size == 0; }

}
