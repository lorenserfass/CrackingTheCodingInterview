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
			toDelete.next = null;
		}
		return;
	}


	public static <T extends Comparable<T>>
	void ex2_4_partition_around_value(MyLinkedList<T> ll, T val) {
		MyLinkedList<T> less = new MyLinkedList<T>();
		MyLinkedList<T> greater_or_equal = new MyLinkedList<T>();

		MyLinkedList<T>.Node l = ll.head;
		while (l.next != null) {
			MyLinkedList<T>.Node n = l.next;
			l.next = null;
			if (val.compareTo(l.value) <= 0)
				greater_or_equal.addHead(l);
			else
				less.addHead(l);
			l = n;
		}

		l = less.head;
		while (l.next != null) l = l.next;

		l.next = greater_or_equal.head;

		ll.head = less.head;
	}

	// TODO: check this for bugs and use it to test more scenarios
	public static <T extends Comparable<T>> boolean isPartitioned(MyLinkedList<T> list, T val) {
		MyLinkedList<T>.Node node = list.head;
		if (node == null) return true;

		while (node.next != null) {
			if (node.value.compareTo(val) >= 0)
				break;
			node = node.next;
		}

		node = node.next;

		while (node.next != null) {
			if (node.value.compareTo(val) < 0)
				return false;
			node = node.next;
		}
			
		return true;
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


	// TODO: Ex 2.6


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


		// exercise 2.2
		System.out.println("Testing ex2_2");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.addTail(i);
		}
		System.out.println("the list:");
		list.print();
		System.out.println("element 1 from the end (the last):" + ex2_2_kth_from_last(list, 1));
		System.out.println("element 4 from end:" + ex2_2_kth_from_last(list, 4));
		System.out.println("10 from the end:" + ex2_2_kth_from_last(list, 10));


		// exercise 2.3
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
		

		// ex 2.4
		System.out.println("\n\nTesting ex2_4. List before partition:");
		MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
		ll.addHead(8);
		ll.addHead(5);
		ll.addHead(9);
		ll.addHead(1);
		ll.addHead(2);
		ll.addHead(6);
		ll.addHead(10);
		ll.addHead(4);
		ll.addHead(7);
		ll.addHead(3);
		ll.print();

		ex2_4_partition_around_value(ll, 5);
		System.out.println("Testing ex2_4. List after partitioning on 5:");
		ll.print();
		System.out.println(isPartitioned(ll, 5));

		
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
