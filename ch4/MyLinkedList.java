public class MyLinkedList<T> {

	public ListNode<T> head = null;


	public void addHead(T value) {
		ListNode toAdd = new ListNode(value);
		toAdd.next = head;
		head = toAdd;
	}

	// this method is so we can "rewire" the list, reordering without creating new nodes
	// (for some of the exercises)
	public void addHead(ListNode<T> newHead) {
		newHead.next = head;
		head = newHead;
	}

	public void addTail(T value) {
		if (head == null) {
			head = new ListNode(value);
			return;
		}

		ListNode node = head;
		while (node.next != null) node = node.next;
		node.next = new ListNode(value);
	}

	public void print() {
		int i = 0;
		ListNode<T> n = head;
		while (n != null) {
			System.out.println("ListNode " + (i++) + ": " + n.value);
			n = n.next;
		}
	}

}
