import java.util.Random;
import java.util.Arrays;

class MyBST<T extends Comparable<T>> {

	private Node root;

	public MyBST() { root = null; }

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
	public void insert(T key) { root = insert(key, root); }

	private Node insert(T key, Node node) {
		if (node == null) return new Node(key);

		node.size++;
		if (key.compareTo(node.key) < 0)
			node.left = insert(key, node.left);
		else
			node.right = insert(key, node.right);
		return node;
	}


	// return whether the tree contains the key
	public boolean contains(T key) { return contains(key, root); }

	private boolean contains(T key, Node node) {
		if (node == null) return false;
		int compare = key.compareTo(node.key);
		if      (compare <  0) return contains(key, node.left);
		else if (compare <  0) return contains(key, node.left);
		else                   return true;
	}



	// Number of elements smaller (or equal?) to this element
	/*public int rank(T key) {
		return rank(key, root);
	}

	private int rank(T key, Node node) {
		
	}*/



	// Imagine the tree is an array (in sorted order) and get the i'th smallest value
	public T getIndex(int i) { return getIndex(i, root); }

	private T getIndex(int i, Node node) {
		if (node == null)  return null; // TODO: throw exception?
		int sizeLeft = size(node.left);
		if (i == sizeLeft) return node.key;
		if (i <  sizeLeft) return getIndex(i, node.left);
		return getIndex(i - sizeLeft - 1, node.right);
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
		MyBST<Integer> tree = new MyBST<Integer>();

		Random r = new Random();
		int[] x = new int[10];
		for (int i = 0; i < 10; i++) x[i] = r.nextInt(10);
		System.out.println("Array: " + Arrays.toString(x));

		for (int i : x) tree.insert(i);

		System.out.println("printing tree (rotated 90 degrees CCW)");
		System.out.println(tree);

		System.out.println("testing getIndex():");
		for (int i = 0; i < 10; i++)
			System.out.print(tree.getIndex(i) + " ");
		System.out.println();
	}

}
