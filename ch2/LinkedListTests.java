import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

class LinkedListTests {
	
	public static void ex2_1_removeDuplicates_TEST() {
		MyLinkedList<Integer> ll1 = new MyLinkedList<Integer>();
		
		// test empty list
		LinkedListExercises.ex2_1_remove_duplicates(ll1);
		
		// test list with one element
		ll1.addHead(5);
		LinkedListExercises.ex2_1_remove_duplicates(ll1);
		assert ll1.head.value == 5 : "remove duplicates changed a value";
		assert ll1.head.next == null : "remove duplicates added a node";
		
		// test list with two elements that are duplicated
		MyLinkedList<Integer> ll2 = new MyLinkedList<Integer>();
		ll2.addHead(4);
		ll2.addHead(4);
		LinkedListExercises.ex2_1_remove_duplicates(ll2);
		assert ll2.head.value == 4 : "remove duplicates changed a value";
		assert ll2.head.next == null : "remove duplicates didn't change (4,4) to (4)";
		
		// test a list without duplicates
		MyLinkedList<Integer> ll3 = new MyLinkedList<Integer>();
		for (int i = 0; i < 10; i++)
			ll3.addTail(i);
		MyLinkedList<Integer>.Node node = ll3.head;
		for (int i = 0; node.next != null; i++) {
			assert i == node.value;
			node = node.next;
		}
		
		// test some random lists
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			MyLinkedList<Integer> llBefore = new MyLinkedList<Integer>();
			MyLinkedList<Integer> llAfter = new MyLinkedList<Integer>();
			
			// add 7 integers between 0 and 9 inclusive (
			for (int j = 0; j < 8; j++) {
				int rint = r.nextInt(10);
				llBefore.addHead(rint);
				llAfter.addHead(rint);
			}
			LinkedListExercises.ex2_1_remove_duplicates(llAfter);
			
			HashSet<Integer> countsBefore = new HashSet<Integer>();
			HashMap<Integer, Integer> countsAfter = new HashMap<Integer, Integer>();
			
			MyLinkedList<Integer>.Node n1 = llBefore.head;
			while (n1 != null) {
				countsBefore.add(n1.value);
				n1 = n1.next;
			}

			MyLinkedList<Integer>.Node n2 = llAfter.head;
			while (n2 != null) {
				if (countsAfter.containsKey(n2.value))
					countsAfter.put(n2.value, countsAfter.get(n2.value) + 1);
				else
					countsAfter.put(n2.value, 1);
				n2 = n2.next;
			}
			
			for (int value : countsBefore) {
				assert countsAfter.containsKey(value) : "remove duplicates removed a value";
				assert countsAfter.get(value) == 1 : "\nafter remove duplicates there's still a duplicate:\n" + llBefore + " to " + llAfter;
			}
			
			for (int value : countsAfter.keySet()) {
				assert countsBefore.contains(value) : "remove duplicates introduced a new value: " + llBefore + " to " + llAfter;
			}
			
			
		}
	}

	public static void ex2_2_kthFromLast_TEST() {
		// test empty list
		MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
		assert LinkedListExercises.ex2_2_kth_from_last(ll, 0) == null;
		assert LinkedListExercises.ex2_2_kth_from_last(ll, 5) == null;
		
		ll.addHead(15);
		assert LinkedListExercises.ex2_2_kth_from_last(ll, 1) == 15;
		
		MyLinkedList<Integer> ll2 = new MyLinkedList<Integer>();
		for (int i = 1; i <= 10; i++)
			ll2.addHead(i);
		
		for (int k = 1; k <= 10; k++)
			assert LinkedListExercises.ex2_2_kth_from_last(ll2, k) == k;
		
		assert LinkedListExercises.ex2_2_kth_from_last(ll2, 11) == null;
	}
	
	public static void ex2_3_deleteNode_TEST() {
		
	}

	private static <T extends Comparable<T>> boolean isPartitioned(MyLinkedList<T> ll, T value) {
		// tests whether all nodes less than "value" come before
		// all nodes greater or equal to "value"
		if (ll.head == null) return true; // empty list

		MyLinkedList<T>.Node node = ll.head;
		
		while (node.next != null) {
			// test if left node belongs in second part, and right node belongs in first part
			if (node.value.compareTo(value) >= 0 && node.next.value.compareTo(value) < 0)
				return false;
			node = node.next;
		}
		return true;
	}
	
	public static void ex2_4_partition_TEST() {
		MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
		LinkedListExercises.ex2_4_partitionAroundValue(ll, 5);
		assert isPartitioned(ll, 7);
		
		
		
		Random r = new Random();
		
		for (int i = 0; i < 100; i++) {
			MyLinkedList<Integer> llBefore = new MyLinkedList<Integer>();
			MyLinkedList<Integer> llAfter = new MyLinkedList<Integer>();
			
			for (int j = 0; j < 10; j++) {
				int randint = r.nextInt(15);
				llBefore.addHead(randint);
				llAfter.addHead(randint);
			}
			int x = r.nextInt(15);
			LinkedListExercises.ex2_4_partitionAroundValue(llAfter, x);
			assert isPartitioned(llAfter, x) : "list not partitioned by " + x + ". Before: " + llBefore + ". After: " + llAfter;
			
			// TODO: test that they contain the same elements with the same counts
		}
	}
	
	public static void ex2_6_findLoop_TEST() {
		// test an empty list
		MyLinkedList<Integer> emptyList = new MyLinkedList<Integer>();
		assert LinkedListExercises.ex2_6_findLoop(emptyList) == null;
		assert LinkedListExercises.ex2_6_findLoopQuadratic(emptyList) == null;
		
		// test a list without a loop
		MyLinkedList<Integer> llWithoutLoop = new MyLinkedList<Integer>();
		llWithoutLoop.addHead(1);
		llWithoutLoop.addHead(2);
		llWithoutLoop.addHead(3);
		assert LinkedListExercises.ex2_6_findLoop(llWithoutLoop) == null;
		assert LinkedListExercises.ex2_6_findLoopQuadratic(llWithoutLoop) == null;

		// test a list with a loop
		MyLinkedList<String> llWithLoop = new MyLinkedList<String>();
		MyLinkedList<String>.Node a = llWithLoop.new Node("A");
		MyLinkedList<String>.Node b = llWithLoop.new Node("B");
		MyLinkedList<String>.Node c = llWithLoop.new Node("C");
		MyLinkedList<String>.Node d = llWithLoop.new Node("D");
		MyLinkedList<String>.Node e = llWithLoop.new Node("E");
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = c;
		llWithLoop.head = a;
		assert LinkedListExercises.ex2_6_findLoop(llWithLoop) == c;
		assert LinkedListExercises.ex2_6_findLoopQuadratic(llWithLoop) == c;
	}

	public static void main(String[] args) {
		
		ex2_1_removeDuplicates_TEST();
		
		ex2_2_kthFromLast_TEST();

		ex2_4_partition_TEST();
		
		ex2_6_findLoop_TEST();
	}
}
