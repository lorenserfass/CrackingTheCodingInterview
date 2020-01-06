import java.util.Random;
import java.util.Arrays;

class MyBSTMultiset<T extends Comparable<T>> {

	private Node root;

	public MyBSTMultiset() { root = null; }

	private class Node {
		T key;
		int size;
		Node left;
		Node right;

		Node(T key) {
			this.key = key;
			size = 1;
			left = null;
			right = null;
		}
	}

	public boolean isEmpty() { return size() == 0; }

	public int size() { return size(root); }

	private int size(Node node) {
		if (node == null) return 0;
		return node.size;
	}
	



	// Insert key, preserving BST properties.
	public void insert(T key) { root = insert(root, key); }

	private Node insert(Node node, T key) {
		if (node == null) return new Node(key);
		
		node.size++;
		if (key.compareTo(node.key) < 0)
			node.left = insert(node.left, key);
		else
			node.right = insert(node.right, key);
		

		return node;
	}


	// return whether the tree contains the key
	public boolean containsKey(T key) { return containsKey(root, key); }

	private boolean containsKey(Node node, T key) {
		if (node == null) return false;
		int compare = key.compareTo(node.key);
		if      (compare <  0) return containsKey(node.left, key);
		else if (compare >  0) return containsKey(node.right, key);
		else                   return true;
	}



	// Number of keys smaller (or equal?) to this key
	public int rank(T key) {
		return rank(root, key, 0);
	}

	private int rank(Node node, T key, int rank) {
		if (node == null) return rank;
		
		if (key.compareTo(node.key) < 0)
			return rank(node.left, key, rank);
		else
			return rank(node.right, key, rank + size(node.left) + 1);
	}
	



	// Imagine the tree is an array (in sorted order) and get the i'th smallest value
	public T getKeyAtIndex(int i) { return getKeyAtIndex(i, root); }

	private T getKeyAtIndex(int i, Node node) {
		if (node == null)  return null; // TODO: throw exception?
		int sizeLeft = size(node.left);
		if (i == sizeLeft) return node.key;
		if (i <  sizeLeft) return getKeyAtIndex(i, node.left);
		return getKeyAtIndex(i - sizeLeft - 1, node.right);
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		buildString(sb, root, 0);
		return sb.toString();
	}

	private void buildString(StringBuilder sb, Node node, int depth) {
		if (node.right != null) buildString(sb, node.right, depth + 1);
		for (int i = 0; i < depth; i++)
			sb.append("    ");
		sb.append(node.key + "," + node.size + "\n");
		if (node.left != null) buildString(sb, node.left, depth + 1);
	}


	public static void main(String[] args) {
		MyBSTMultiset<Integer> tree = new MyBSTMultiset<Integer>();

		Random r = new Random();
		int[] x = {5, 1, 4, 4, 5, 9, 7, 13, 3}; // new int[10];
		// for (int i = 0; i < 10; i++) x[i] = r.nextInt(10);
		System.out.println("Array: " + Arrays.toString(x));

		for (int i : x) {
			tree.insert(i);
		}

		System.out.println("printing tree (rotated 90 degrees CCW)");
		System.out.println(tree);

		System.out.println("testing getKeyAtIndex():");
		for (int i = 0; i < tree.size(); i++)
			System.out.print(tree.getKeyAtIndex(i) + " ");
		System.out.println();
		
		/*for (int i : x)
			System.out.println("rank(" + i + ") = " + tree.rank(i));*/
		
		System.out.println("rank(1) = " + tree.rank(1));
		System.out.println("rank(3) = " + tree.rank(3));
		System.out.println("rank(4) = " + tree.rank(4));
		
	}

}
