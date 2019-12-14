import java.util.HashSet;

public class LinkedListExercises {
	
	
	public static <T> void ex2_1_remove_duplicates(MyLinkedList<T> list) {
		HashSet<T> valuesSet = new HashSet<T>();
		
		MyLinkedList<T>.Node node1 = list.head;
		
		if (node1 == null) return; // empty list
		MyLinkedList<T>.Node node2;
		
		valuesSet.add(list.head.value);
		
		while (node1 != null) {
			node2 = node1.next;
			if (node2 == null)
				break;
			
			while (node2 != null && valuesSet.contains(node2.value))
				node2 = node2.next;
			
			if (node2 != null) valuesSet.add(node2.value);
			node1.next = node2;
			node1 = node1.next;
		}

	}


	public static <T> T ex2_2_kth_from_last(MyLinkedList<T> list, int k) {
		// k = 1 returns the last element, etc.
		if (k <= 0) return null;

		int len = 0; // list size could be stored in the list class; for a question like this they probably don't want you to use that

		// find the size of the length in one pass
		MyLinkedList<T>.Node node = list.head;
		while (node != null) {
			len++;
			node = node.next;
		}
		if (k > len) return null;


		// count again through it, stopping at appropriate place
		int stopAt = len - k;
		len = 0;
		node = list.head;
		while (len < stopAt) {
			node = node.next;
			len++;
		}
			
		return node.value;
	}


	// TODO: this not tested yet
	public static <T> void ex2_3_delete_node(MyLinkedList<T> list, MyLinkedList<T>.Node toDelete) throws Exception {
		if (toDelete == null)
			throw new java.lang.Exception("trying to delete nothing");


		if (toDelete == list.head) {
			list.head = list.head.next;
			return;
		}

		else {
			MyLinkedList<T>.Node node = list.head;
			// advance until it is right before toDelete:
			while (node.next != toDelete)
				node = node.next;

			node.next = toDelete.next;
			toDelete.next = null; // Not sure if this is necessary or helpful
		}
		return;
	}


	public static <T extends Comparable<T>>
	void ex2_4_partitionAroundValue(MyLinkedList<T> ll, T val) {
		MyLinkedList<T> less = new MyLinkedList<T>();
		MyLinkedList<T> greater_or_equal = new MyLinkedList<T>();

		MyLinkedList<T>.Node l = ll.head;
		while (l != null) {
			MyLinkedList<T>.Node n = l.next;
			l.next = null;
			if (val.compareTo(l.value) <= 0)
				greater_or_equal.addHead(l);
			else
				less.addHead(l);
			l = n;
		}

		l = less.head;
		if (l == null) {
			ll.head = greater_or_equal.head;
			return;
		}
		
		while (l.next != null) l = l.next;

		l.next = greater_or_equal.head;

		ll.head = less.head;
	}


	public static MyLinkedList<Integer> ex2_5_part1(MyLinkedList<Integer> list1,
							MyLinkedList<Integer> list2) {
		MyLinkedList<Integer> list3 = new MyLinkedList<Integer>();

		MyLinkedList<Integer>.Node n1 = list1.head;
		MyLinkedList<Integer>.Node n2 = list2.head;

		int carryover = 0;

		while (n1 != null || n2 != null) {
			int sum = carryover;
			sum += (n1 != null) ? n1.value : 0;
			sum += (n2 != null) ? n2.value : 0;

			if (sum >= 10) {
				sum = sum - 10;
				carryover = 1;
			}
			else {
				carryover = 0;
			}
			list3.addTail(sum);
			
			if (n1 != null) n1 = n1.next;
			if (n2 != null) n2 = n2.next;
		}


		return list3;
	}

	public static <T> MyLinkedList<T> reverse(MyLinkedList<T> list1) {
		MyLinkedList<T> list2 = new MyLinkedList<T>();

		MyLinkedList<T>.Node node = list1.head;

		while (node != null) {
			list2.addHead(node.value);
			node = node.next;
		}
		return list2;
	}

	public static MyLinkedList<Integer> ex2_5_part2(MyLinkedList<Integer> list1,
							MyLinkedList<Integer> list2) {
		return reverse(ex2_5_part1(reverse(list1), reverse(list2)));
	}


	// TODO: is it cheating to use a HashSet?
	public static <T> MyLinkedList<T>.Node ex2_6_findLoop(MyLinkedList<T> ll) {
		// returns null if no loop.
		// if there is a loop, returns the node at the beginning of the loop.
		HashSet<MyLinkedList<T>.Node> nodeset = new HashSet<MyLinkedList<T>.Node>();
		MyLinkedList<T>.Node node = ll.head;
		
		while (node != null) {
			if (nodeset.contains(node))
				return node;
			
			nodeset.add(node);
			node = node.next;
		}
		return null;
	}
	
	
	public static <T> MyLinkedList<T>.Node ex2_6_findLoopQuadratic(MyLinkedList<T> ll) {
		MyLinkedList<T>.Node node = ll.head;
		
		// outer loop: go through the nodes, keep track of the index
		// inner loop: check all preceding nodes (starting from head) to see
		// if equal to current node
		int i = 0;
		while (node != null) {
			MyLinkedList<T>.Node test = ll.head;
			int j = 0;
			while (j < i) {
				if (test == node)
					return node;
				test = test.next;
				j++;
			}
			
			node = node.next;
			i++;
		}
		return null;
	}


	public static <T> boolean ex2_7_isPalindrome(MyLinkedList<T> list1) {
		MyLinkedList<T> list2 = reverse(list1);

		MyLinkedList<T>.Node node1 = list1.head;
		MyLinkedList<T>.Node node2 = list2.head;

		while (node1 != null && node2 != null) {
			if (node1.value != node2.value)
				return false;
			node1 = node1.next;
			node2 = node2.next;
		}

		return true;
	}

	public static void main(String[] args) throws Exception {


		// exercise 2.3
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.addTail(i);
		}
		System.out.println("\n\nTesting ex2_3");
		MyLinkedList<Integer>.Node n = list.head;
		while (n.next != null) n = n.next;
		System.out.println("Deleting last node:");
		ex2_3_delete_node(list, n);
		list.print();

		System.out.println("Deleting a middle node:");
		n = list.head;
		while (n.value != 4) n = n.next;
		ex2_3_delete_node(list, n);
		list.print();

		System.out.println("Deleting the first node:");
		n = list.head;
		ex2_3_delete_node(list, n);
		list.print();
		


		
		// ex 2.5
		System.out.println("\n\nTesting ex2_5.");
		MyLinkedList<Integer> list1 = new MyLinkedList<Integer>();
		list1.addTail(8);
		list1.addTail(7);
		list1.addTail(9);
		MyLinkedList<Integer> list2 = new MyLinkedList<Integer>();
		list2.addTail(5);
		list2.addTail(4);
		list2.addTail(6);
		list2.addTail(2);
		list2.addTail(3);

		MyLinkedList<Integer> list3 = ex2_5_part1(list1, list2);
		list3.print();

		System.out.println("\n\nTesting ex2_5.");
		MyLinkedList<Integer> list4 = ex2_5_part2(list1, list2);
		list4.print();

		// TODO: test 2.7
	}

}
