class SetOfStacks<T> {

	private int capacity;
	private MyStack<MyStack<T>> stacks;
	private int size = 0;;

	public SetOfStacks() {
		capacity = 10;
		stacks = new MyStack<MyStack<T>>();
	}

	public SetOfStacks(int capacity) {
		this.capacity = capacity;
		stacks = new MyStack<MyStack<T>>();
	}

	public void push(T value) {
		if (stacks.size() == 0 || stacks.peek().size() == capacity)
			stacks.push(new MyStack<T>());

		size++;
		stacks.peek().push(value);
	}

	public T pop() {
		if (size == 0)
			throw new java.util.NoSuchElementException();

		size--;
		if (stacks.peek().size() == 0)
			stacks.pop();

		return stacks.peek().pop();
	}



	public int size() { return size; }
}
