class QueueFromTwoStacks<T> {

	private Stack<T> head;
	private Stack<T> tail;

	public QueueFromTwoStacks() {
		head = new Stack<T>();
		tail = new Stack<T>();
	}

	public void pushTail(T value) {
		tail.push(value);
	}

	public void pushHead(T value) {
		head.push(value);
	}

	public T popTail() {
		if (tail.isEmpty())
			while (!head.isEmpty())
				tail.push(head.pop());

		return tail.pop();
	}

	public T peekTail() {
		if (tail.isEmpty())
			while (!head.isEmpty())
				tail.push(head.pop());

		return tail.peek();
	}

	public T popHead() {
		if (head.isEmpty())
			while (!tail.isEmpty())
				head.push(tail.pop());

		return head.pop();
	}

	public T peekHead() {
		if (head.isEmpty())
			while (!tail.isEmpty())
				head.push(tail.pop());

		return head.peek();
	}


}

