import java.util.Random;
import java.util.Arrays;

/**
 * Implements a priority queue which can be used either as a MaxPQ or as a MinPQ.
 * (This is a setting decided at initialization.)
 * 
 * Also contains static methods for heapsort (both ascending and descending)
 * and finding the max n or min n of an array using a heap in place.
 * @author loren
 *
 * @param <T>
 */
class PQ<T extends Comparable<T>> {

	private T[] pq;
	private int size;
	private final int isMinPQ;
	
	/**
	 * @param capacity this is the max capacity; the PQ is not resizing yet
	 * @param isMinPQ indicates whether this should be a min-PQ (true) or max-PQ (false)
	 */
	public PQ(int capacity, boolean isMinPQ) {
		pq = (T[]) new Comparable[capacity + 1];
		size = 0;
		this.isMinPQ = isMinPQ ? 1 : -1;
	}


	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }

	public T peek() { return pq[1]; }

	public void enqueue(T x) {
		pq[++size] = x;
		sink(swim(size));
	}


	public T dequeue() {
		T toReturn = pq[1];
		pq[1] = pq[size];
		pq[size--] = null;
		sink(1);
		return toReturn;
	}


	private void swap(int i, int j) {
		T temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	private int swim(int i) {
		while (i > 1 && isMinPQ * pq[i].compareTo(pq[i/2]) < 0) {
			swap(i, i/2);
			i /= 2;
		}
		return i;
	}

	private void sink(int i) {
		while (2*i <= size) {
			int child = 2*i;
			if (child + 1 <= size)
				if (isMinPQ * pq[child + 1].compareTo(pq[child]) < 0)
					child++;
			if (isMinPQ * pq[child].compareTo(pq[i]) < 0) {
				swap(i, child);
				i = child;
			}
			else
				break;
		}
	}

	// this method for testing
	public static <T extends Comparable<T>> boolean isSortedAscending(T[] x) {
		for (int i = 1; i < x.length; i++)
			if (x[i].compareTo(x[i-1]) < 0)
				return false;
		return true;
	}

	// this method for testing
	public static <T extends Comparable<T>> boolean isSortedDescending(T[] x) {
		for (int i = 1; i < x.length; i++)
			if (x[i].compareTo(x[i-1]) > 0)
				return false;
		return true;
	}


	/*******************************/
	/* static methods for heapsort */
	/* These will be 0-based.      */
	/*                             */
	/* Index relationships:        */
	/* root = 0                    */
	/* child1 = 2*parent + 1       */
	/* child2 = 2*parent + 2       */
	/* parent = (child + 1)/2 - 1  */
	/*******************************/

	public static <T extends Comparable<T>> void heapSortAscending(T[] x) {
		maxHeapify(x);
		unMaxHeapify(x, x.length);
	}

	public static <T extends Comparable<T>> void heapSortDescending(T[] x) {
		minHeapify(x);
		unMinHeapify(x, x.length);
	}
	
	/**
	 * Move the smallest n elements to the *end* of x.
	 * @param x
	 * @param n
	 */
	public static <T extends Comparable<T>> void getSmallestN(T[] x, int n) {
		minHeapify(x);
		unMinHeapify(x, n);
	}

	/**
	 * Move the largest n elements to the *end* of x.
	 * @param x
	 * @param n
	 */
	public static <T extends Comparable<T>> void getLargestN(T[] x, int n) {
		maxHeapify(x);
		unMaxHeapify(x, n);
	}

	private static <T extends Comparable<T>> void minHeapify(T[] x) {
		for (int i = 0; i < x.length; i++)
			enqueue(x, i, true);
	}

	private static <T extends Comparable<T>> void maxHeapify(T[] x) {
		for (int i = 0; i < x.length; i++)
			enqueue(x, i, false);
	}

	private static <T extends Comparable<T>> void unMinHeapify(T[] x, int numToTakeOutOfHeap) {
		for (int i = 0; i < numToTakeOutOfHeap; i++)
			dequeue(x, x.length - i, true);
	}

	private static <T extends Comparable<T>> void unMaxHeapify(T[] x, int numToTakeOutOfHeap) {
		for (int i = 0; i < numToTakeOutOfHeap; i++)
			dequeue(x, x.length - i, false);
	}

	private static <T> void swap(T[] x, int i, int j) {
		T temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}


	private static <T extends Comparable<T>> void enqueue(T[] x, int i, boolean minPQ) {
		// expand the queue to include index i
		// by incrementing size to i+1 and swimming then sinking element at index i
		int toSink = swim(x, i, minPQ);
		sink(x, toSink, i + 1, minPQ);
	}

	private static <T extends Comparable<T>> void dequeue(T[] x, int size, boolean minPQ) {
		swap(x, 0, size - 1);
		sink(x, 0, size - 1, minPQ);
	}

	private static <T extends Comparable<T>> int swim(T[] x, int i, boolean minPQ) {
		int m = minPQ ? 1 : -1;
		while (i > 0) {
			int parent = (i + 1) / 2 - 1;
			if (m * x[i].compareTo(x[parent]) < 0) {
				swap(x, i, parent);
				i = parent;
			}
			else
				break;
		}
		return i;
	}

	private static <T extends Comparable<T>> void sink(T[] x, int i, int size, boolean minPQ) {
		int m = minPQ ? 1 : -1;
		while (2 * i + 1 < size) {
			int child = 2 * i + 1;
			// if right child has higher priority, use right child:
			if (child + 1 < size)
				if (m * x[child + 1].compareTo(x[child]) < 0)
					child++;
			if (m * x[child].compareTo(x[i]) < 0) {
				swap(x, i, child);
				i = child;
			}
			else
				break;
		}
	}


	public static void main(String[] args) {
		Random random = new Random();

		int n = 10;
		PQ<Integer> minPQ = new PQ<Integer>(n, true);
		PQ<Integer> maxPQ = new PQ<Integer>(n, false);
		int[] x = new int[n];

		/*for (int i = 0; i < 100; i++) {
			for (int j = 0; j < n; j++) minPQ.enqueue(random.nextInt(200));
			for (int j = 0; j < n; j++) x[j] = minPQ.dequeue();
			if (!minPQ.isEmpty()) System.out.println("not empty.");
			if (!isSortedAscending(x)) System.out.println("not sorted.");
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < n; j++) maxPQ.enqueue(random.nextInt(200));
			for (int j = 0; j < n; j++) x[j] = maxPQ.dequeue();
			if (!minPQ.isEmpty()) System.out.println("not empty.");
			if (!isSortedDescending(x)) System.out.println("not sorted.");
		}*/
	
		Integer[] x2 = new Integer[n];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < n; j++) x2[j] = random.nextInt(100);
			heapSortAscending(x2);
			if (!isSortedAscending(x2)) System.out.println("not sorted");
		}

	}

}
