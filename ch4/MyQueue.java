
class MyQueue<T> {

	private Node head;
	private Node tail;
	private int size;

	class Node {
		T value;
		Node next;

		Node(T value) {
			this.value = value;
			this.next = null;
		}
	}

	public MyQueue() {
		head = tail = null;
		size = 0;
	}

	public void enqueue(T value) {
		Node node = new Node(value);
		if (tail == null)
			head = tail = node;
		else {
			tail.next = node;
			tail = tail.next;
		}
		size++;
	}


	public T dequeue() {
		if (size == 0) throw new java.util.NoSuchElementException();
		T value = head.value;
		head = head.next; 
		if (--size == 0) tail = null;
		return value;
	}

	public int size() { return size; }

	public boolean isEmpty() { return size == 0; }


	public static void main(String[] args) {
		MyQueue<Integer> q = new MyQueue<Integer>();
		for (int i = 0; i < 10; i++)
			q.enqueue(i);

		while (!q.isEmpty())
			System.out.println(q.dequeue());

	}

}
