class MyStack<T> {

	Node top = null;
	private int size = 0;
	
	class Node {
		Node next;
		T value;
		
		Node(T v, Node n) {
			value = v;
			next = n;
		}
	}

	public void push(T val) {
		size++;
		Node newTop = new Node(val, top);
		top = newTop;
	}

	public T pop() {
		if (size == 0)
			throw new java.util.NoSuchElementException();

		T t = top.value;
		top = top.next;
		size--;
		return t;
	}

	public boolean isEmpty() { return size == 0; }


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


}
