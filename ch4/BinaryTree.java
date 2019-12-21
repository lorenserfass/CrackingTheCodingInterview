import java.util.ArrayList; // for returning from exercise 4.4
import java.util.Vector; // for input to exercise 4.3

/**
 * Contains methods for exercises 4.1, 4.3, 4.4, 4.5, 4.6, 4.7
 * @author loren
 *
 * @param <T>
 */
class BinaryTree<T extends Comparable<T>> {

	Node root = null;

	class Node {
		T value;
		Node parent; // used in ex 4.6, also ancestor exercise
		Node left;
		Node right;
		
		Node(T value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		Node(T value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
	
	public BinaryTree() {
		root = null;
	}
	
	


	/**
	 * Checks if the depths of two subtrees of any node never differ by more than 1
	 * @return
	 */
	public boolean Ex4_1_isBalanced1() {
		return getTreeInfo(root).isBalanced1;
	}


	/**
	 * Checks if the depths of two subtrees of any node are never off by more than a factor of 2
	 * TODO: check this
	 * @return
	 */
	public boolean Ex4_1_isBalanced2() {
		return getTreeInfo(root).isBalanced2;
	}
	

	/**
	 * Given a sorted Vector, constructs a BST of minimal height
	 * @param sortedVector
	 */
	public BinaryTree(Vector<T> sortedVector) {
		for (int i = 0; i < sortedVector.size(); i++)
			root = insertInSequence(root, i, sortedVector.get(i));
	}


	
	/**
	 * @return An ArrayList of linked lists of nodes at each depth
	 */
	public ArrayList<MyLinkedList<BinaryTree<T>.Node>> Ex4_4_listOfListsOfNodes() {
		ArrayList<MyLinkedList<Node>> lol = new ArrayList<MyLinkedList<Node>>();
		addToLOL(lol, root, 0);
		return lol;
	}


	/**
	 * Checks if all the nodes are in order.
	 * @return
	 */
	public boolean Ex4_5_isBST() {
		return getTreeInfo(root).isBST;
	}
	
	
	
	
	/**
	 * Given a node, finds the "next" node according to in-order traversal.
	 * @param node
	 * @return
	 */
	public Node Ex4_6_next(Node node) {
		if (node.right != null) {
			node = node.right;
			while (node.left != null) node = node.left;
			return node;
		}
		else {
			while (isRightChild(node))
				node = node.parent;
			if (isLeftChild(node)) {
				node = node.parent;
				while (node.left != null) node = node.left;
				return node;
			}
			else
				return null;
		}
	}
	
	
	
	/**
	 * Quadratic time in tree height.
	 * Uses the parent links.
	 * @param node1
	 * @param node2
	 * @return
	 */
	public Node Ex4_7_1_ancestor(Node node1, Node node2) {
		while (node1 != null) {
			Node n2 = node2;
			while (n2 != null) {
				if (node1 == n2)
					return node1;
				n2 = n2.parent;
			}
			
			node1 = node1.parent;
		}
		return null; // could end up in different trees?
	}
	
	
	/**
	 * Linear time in tree height.
	 * Uses extra space and parent link.
	 * @param node1
	 * @param node2
	 * @return
	 */
	public Node Ex4_7_2_ancestor(Node node1, Node node2) {
		// linear time in tree height
		MyStack<Node> stack1 = new MyStack<Node>();
		MyStack<Node> stack2 = new MyStack<Node>();
		
		while (node1 != null) {
			stack1.push(node1);
			node1 = node1.parent;
		}
		while (node2 != null) {
			stack2.push(node2);
			node2 = node2.parent;
		}
		
		Node ancestor = null;
		while (stack1.peek() == stack2.peek()) {
			ancestor = stack1.pop();
			stack2.pop();
		}
		return ancestor;
	}

	
	/**
	 * Get information about a (sub)tree.
	 * @param node
	 * @return
	 */
	private TreeInfo<T> getTreeInfo(Node node) {

		if (node == null)
			return new TreeInfo<T>(null, null, 0, true, true, true);
		
		TreeInfo<T> leftInfo  = getTreeInfo(node.left);
		TreeInfo<T> rightInfo = getTreeInfo(node.right);

		// info to return
		T leftmost  = node.value;
		T rightmost = node.value;
		int depth = 1 + Math.max(leftInfo.depth, rightInfo.depth);
		boolean isBST = true;
		boolean isBalanced1 = leftInfo.isBalanced1 && rightInfo.isBalanced1 && Math.abs(leftInfo.depth - rightInfo.depth) <= 1;
		boolean isBalanced2 = leftInfo.isBalanced2 && rightInfo.isBalanced2 && 2 * leftInfo.depth <= rightInfo.depth && 2 * rightInfo.depth <= leftInfo.depth;
		
		if (node.left != null) {
			leftmost = leftInfo.leftmostValue;
			if (!leftInfo.isBST || leftInfo.rightmostValue.compareTo(node.value) > 0)
				isBST = false;
		}

		if (node.right != null) {
			rightmost = rightInfo.rightmostValue;
			if (!rightInfo.isBST || rightInfo.leftmostValue.compareTo(node.value) < 0)
				isBST = false;
		}
			
		return new TreeInfo<T>(leftmost, rightmost, depth, isBST, isBalanced1, isBalanced2);
	}
	
	
	
	
	/**
	 * recursive function for exercise 4.4
	 * @param lol
	 * @param node
	 * @param depth
	 */
	private void addToLOL(ArrayList<MyLinkedList<BinaryTree<T>.Node>> lol, Node node, int depth) {
		if (node == null) return;
		
		if (depth >= lol.size())
			lol.add(new MyLinkedList<BinaryTree<T>.Node>());
		
		lol.get(depth).addHead(node);
		addToLOL(lol, node.left, depth + 1);
		addToLOL(lol, node.right, depth + 1);
	}
	
	
	/**
	 * Recursive function for exercise 4.3
	 * @param node
	 * @param i
	 * @param value
	 * @return
	 */
	private Node insertInSequence(Node node, int i, T value) {
		if (node == null) return new Node(value, null, null);

		int depthSoFar = HelperFunctions.log2(i) + 1;
		boolean isFull = HelperFunctions.powerOfTwo(depthSoFar) == (i + 1);

		if (isFull) return new Node(value, node, null);

		node.right = insertInSequence(node.right, i - (1 << (31 - Integer.numberOfLeadingZeros(i))), value);
		return node;
	}
	
	
	
	private boolean isRightChild(Node node) {
		return node.parent != null && node.parent.right == node;
	}

	private boolean isLeftChild(Node node) {
		return node.parent != null && node.parent.left == node;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		buildString(sb, root, 0);
		return sb.toString();
	}

	private void buildString(StringBuilder sb, Node node, int depth) {
		if (node == null) return;

		buildString(sb, node.right, depth + 1);
		sb.append("\n");
		for (int i = 0; i < depth; i++)
		sb.append("  ");
		sb.append(node.value);
		buildString(sb, node.left, depth + 1);
	}
	
	
}
