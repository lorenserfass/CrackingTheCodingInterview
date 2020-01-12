import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Data structure which is internally either a tree or a doubly linked list.
 * Methods convertToTree and convertToList change the internal structure
 * in place (using the existing nodes and rewiring the connection between them).
 * @author loren
 * 
 */
class TreeList {

	private boolean isTree;
	private BiNode root;
	private BiNode left;
	private BiNode right;
	private int size;

	public TreeList() {
		isTree = true;
		root = null;
		left = null;
		right = null;
	}

	static class BiNode {
		public int data;
		public BiNode node1, node2;
		BiNode(int x) {
			this.data = x;
			node1 = null;
			node2 = null;
		}
	}
	
	public static void swap(int[] x, int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}
	
	public static void shuffle(int[] x) {
		int n = x.length;
		Random random = new Random();
		for (int i = 0; i < n; i++)
			swap(x, i, i + random.nextInt(n - i));
	}

	public int size() { return size; }
	public boolean isTree() { return isTree; }
	public boolean isList() { return !isTree; }

	public void insertIntoTree(int x) {
		if (!isTree) return;
		root = insertIntoTree(root, x);
	}

	private BiNode insertIntoTree(BiNode node, int x) {
		if (node == null) {
			size++;
			return new BiNode(x);
		}
		if (x >= node.data) node.node2 = insertIntoTree(node.node2, x);
		else node.node1 = insertIntoTree(node.node1, x);
		return node;
	}
	
	


	public void convertToList() {
		if (!isTree) return;
		BiNode[] ends = convertToList(root);
		left = ends[0];
		right = ends[1];
		root = null;
		isTree = false;
	}

	private BiNode[] convertToList(BiNode node) {
		if (node == null) return new BiNode[] {null, null};
		
		BiNode[] endNodes = new BiNode[2];
		
		if (node.node1 != null) {
			BiNode[] leftStuff = convertToList(node.node1);
			endNodes[0] = leftStuff[0];
			node.node1 = leftStuff[1];
			leftStuff[1].node2 = node;
		}
		else {
			endNodes[0] = node;
		}
		if (node.node2 != null) {
			BiNode[] rightStuff = convertToList(node.node2);
			endNodes[1] = rightStuff[1];
			node.node2 = rightStuff[0];
			rightStuff[0].node1 = node;
		}
		else {
			endNodes[1] = node;
		}

		return endNodes;
	}
	
	
	public void convertToTree() {
		if (isTree) return;
		root = convertToTree(left, size);
		isTree = true;
	}
	
	/**
	 * Rearranges the nodes into a subtree.
	 * return the topmost node of the subtree created by this function.
	 * @param left
	 * @param size
	 * @return the topmost BiNode of the resulting subtree
	 */
	private BiNode convertToTree(BiNode node, int size) {
		System.out.println(size);
		if (size <= 0) return null; // needed?

		// TODO: base case 1 needed?
		if (size == 1) {
			node.node1 = null;
			node.node2 = null;
			return node;
		}

		// Find the middle of the list segment.
		// This will be the top of the subtree.
		BiNode newRoot = node;
		int middleIndex = size / 2;
		for (int i = 0; i < middleIndex; i++) {
			newRoot = newRoot.node2;
		}
		
		newRoot.node1 = convertToTree(node, middleIndex); // TODO: not sure of int argument
		newRoot.node2 = convertToTree(newRoot.node2, size - middleIndex - 1); // TODO: not sure of int argument
		
		return newRoot;
	}
	
	
	
	public class InTreeOrder implements Iterator<BiNode> {
		
		Stack<BiNode> stack;
		
		InTreeOrder() {
			if (!isTree) return;
			stack = new Stack<BiNode>();
			BiNode node = root;
			while (node != null) {
				stack.add(node);
				node = node.node1;
			}
		}

		@Override
		public boolean hasNext() {
			if (!isTree) return false;
			return !stack.isEmpty();
		}

		@Override
		public BiNode next() {
			if (!isTree) return null;
			BiNode next = stack.pop();
			BiNode node = next.node2;
			while (node != null) {
				stack.add(node);
				node = node.node1;
			}
			return next;
		}
		
	}
	
	
	public class InListOrder implements Iterator<BiNode> {
		
		BiNode next;
		
		InListOrder() {
			next = left;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public BiNode next() {
			BiNode node = next;
			next = next.node2;
			return node;
		}
		
	}
	
	
	public String treeString() {
		if (!isTree) return "";
		
		StringBuilder sb = new StringBuilder();
		constructTreeString(root, sb, 0);
		return sb.toString();
	}
	
	private void constructTreeString(BiNode node, StringBuilder sb, int depth) {
		if (node == null) return;
		
		constructTreeString(node.node2, sb, depth + 1);
		for (int i = 0; i < depth; i++) {
			sb.append("  ");
		}
		sb.append(node.data);
		sb.append("\n");
		constructTreeString(node.node1, sb, depth + 1);
	}

	public static void main(String[] args) {
		
		// randomly order integers between 0 and 19
		int n = 20;
		int[] x = new int[n];
		for (int i = 0; i < n; i++) x[i] = i;
		shuffle(x);

		// Insert integers between 0 and n into a TreeList in random order
		// and print the resulting tree.
		TreeList tl = new TreeList();
		for (int i = 0; i < n; i++) tl.insertIntoTree(x[i]);
		System.out.println(tl.treeString());
		
		// make an ArrayList of the nodes in the tree
		ArrayList<TreeList.BiNode> arraylist1 = new ArrayList<TreeList.BiNode>();
		Iterator<BiNode> treeIter = tl.new InTreeOrder();
		while (treeIter.hasNext())
			arraylist1.add(treeIter.next());
		
		
		// convert the tree to a list, make an ArrayList of the nodes in the list
		tl.convertToList();
		ArrayList<TreeList.BiNode> arraylist2 = new ArrayList<TreeList.BiNode>();
		Iterator<BiNode> listIter = tl.new InListOrder();
		while (listIter.hasNext())
			arraylist2.add(listIter.next());
		
		// convert the list to a tree, make another ArrayList
		tl.convertToTree();
		ArrayList<TreeList.BiNode> arraylist3 = new ArrayList<TreeList.BiNode>();
		treeIter = tl.new InTreeOrder();
		while (treeIter.hasNext())
			arraylist3.add(treeIter.next());
		
		// check that arraylist1, arraylist2, and arraylist3 are in the same order and
		// all have n elements
		Iterator<BiNode> iter1 = arraylist1.iterator();
		Iterator<BiNode> iter2 = arraylist2.iterator();
		Iterator<BiNode> iter3 = arraylist3.iterator();
		int count = 0;
		while (iter1.hasNext() && iter2.hasNext() && iter3.hasNext()) {
			TreeList.BiNode next1 = iter1.next();
			TreeList.BiNode next2 = iter2.next();
			TreeList.BiNode next3 = iter3.next();
			assert next1 == next2 && next1 == next3;
			count++;
		}
		assert !iter1.hasNext() && !iter2.hasNext() && !iter3.hasNext();
		assert count == n;
		
		


		System.out.println(tl.treeString());

	}
}
