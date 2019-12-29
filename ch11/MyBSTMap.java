import java.util.Random;
import java.util.Arrays;

class MyBSTMap<T extends Comparable<T>, U> {

	private Node root;

	public MyBSTMap() { root = null; }

	private class Node {
		T key;
		U value;
		int size;
		Node left;
		Node right;

		Node(T key, U value) {
			this.key = key;
			this.value = value;
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
	public void insert(T key, U value) { root = insert(root, key, value); }

	private Node insert(Node node, T key, U value) {
		if (node == null) return new Node(key, value);
		
		int compare = key.compareTo(node.key);
		if      (compare < 0) node.left = insert(node.left, key, value);
		else if (compare > 0) node.right = insert(node.right, key, value);
		else                  node.value = value;

		// for multiset
		/*node.size++;
		if (key.compareTo(node.key) < 0)
			node.left = insert(node.left, key, value);
		else
			node.right = insert(node.right, key, value);*/
		
		node.size = 1 + size(node.left) + size(node.right); 

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



	// Number of elements smaller (or equal?) to this element
	/*public int rank(T key) {
		return rank(key, root);
	}

	private int rank(T key, Node node) {
		
	}*/
	
	public U getValue(T key) {
		return getValue(root, key);
	}
	
	private U getValue(Node node, T key) {
		if (node == null) throw new java.util.NoSuchElementException();
		
		int compare = key.compareTo(node.key);
		if      (compare < 0) return getValue(node.left, key);
		else if (compare > 0) return getValue(node.right, key);
		else    return node.value;
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
		sb.append(node.key + "," + node.value + "," + node.size + "\n");
		if (node.left != null) buildString(sb, node.left, depth + 1);
	}


	public static void main(String[] args) {
		MyBSTMap<Integer, Integer> tree = new MyBSTMap<Integer, Integer>();

		Random r = new Random();
		int[] x = new int[10];
		for (int i = 0; i < 10; i++) x[i] = r.nextInt(10);
		System.out.println("Array: " + Arrays.toString(x));

		for (int i : x) {
			if (!tree.containsKey(i))
				tree.insert(i, 1);
			else
				tree.insert(i, 1 + tree.getValue(i));
		}

		System.out.println("printing tree (rotated 90 degrees CCW)");
		System.out.println(tree);

		System.out.println("testing getKeyAtIndex():");
		for (int i = 0; i < tree.size(); i++)
			System.out.print(tree.getKeyAtIndex(i) + " ");
		System.out.println();
	}

}
