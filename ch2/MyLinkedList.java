public class MyLinkedList<T> {

	public Node<T> head = null;


	public void addHead(T value) {
		Node toAdd = new Node(value);
		toAdd.next = head;
		head = toAdd;
	}

	// this method is so we can "rewire" the list, reordering without creating new nodes
	// (for some of the exercises)
	public void addHead(Node<T> newHead) {
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

	public void print() {
		int i = 0;
		Node<T> n = head;
		while (n != null) {
			System.out.println("Node " + (i++) + ": " + n.value);
			n = n.next;
		}
	}

}
