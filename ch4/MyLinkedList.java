public class MyLinkedList<T> {
	
	class Node {
		Node next;
		T value;

		Node(T value) {
			next = null;
			this.value = value;
		}
	}

	public Node head = null;


	public void addHead(T value) {
		Node toAdd = new Node(value);
		toAdd.next = head;
		head = toAdd;
	}

	// this method is so we can "rewire" the list, reordering without creating new nodes
	// (for some of the exercises)
	public void addHead(Node newHead) {
		newHead.next = head;
		head = newHead;
	}

	public void addTail(T value) {
		if (head == null) {
			head = new Node(value);
			return;
		}

		Node node = head;
		while (node.next != null) node = node.next;
		node.next = new Node(value);
	}
	
	public String toString() {
		Node n = head;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		while (n != null) {
			sb.append(n.value);
			if (n.next != null) sb.append(", ");
			n = n.next;
		}
		sb.append("]");
		return sb.toString();
	}

	public void print() {
		System.out.println(toString());
	}

}
