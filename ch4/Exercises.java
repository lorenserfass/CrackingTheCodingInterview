import java.util.ArrayList;

class Exercises {
	
	public static class Pair<T, U> {
		T first;
		U second;
		
		Pair(T t, U u) {
			first = t;
			second = u;
		}
	}
	
	private static int log2(int n) {
		if(n < 0) throw new IllegalArgumentException();
		return 32 - Integer.numberOfLeadingZeros(n);
	}


	public static <T> boolean isRightChild(BinaryTree<T>.Node node) {
		if (node.parent == null)
			return false;
		return node.parent.right == node;
	}

	public static <T> boolean isLeftChild(BinaryTree<T>.Node node) {
		if (node.parent == null)
			return false;
		return node.parent.left == node;
	}
	
	private static <T> Pair<Integer, Boolean> heightBalanced(BinaryTree<T>.Node node) {
		if (node == null)
			return new Pair<Integer, Boolean>(0, true);
		
		Pair<Integer, Boolean> pair1 = heightBalanced(node.left);
		Pair<Integer, Boolean> pair2 = heightBalanced(node.right);
		int height1 = pair1.first;
		int height2 = pair2.first;
		boolean balanced1 = pair1.second;
		boolean balanced2 = pair2.second;
		
		return new Pair<Integer, Boolean>(1 + Math.max(height1, height2), balanced1 && balanced2 && Math.abs(height1 - height2) <= 1);
	}
	
	public static <T> boolean ex4_1_isBalanced(BinaryTree<T> tree) {
		return heightBalanced(tree.root).second;
	}
	
	

	public static <T> BinaryTree<T>.Node ancestor_QuadraticInTreeHeight(BinaryTree<T>.Node node1, BinaryTree<T>.Node node2) {
		while (node1 != null) {
			BinaryTree<T>.Node n2 = node2;
			while (n2 != null) {
				if (node1 == n2)
					return node1;
				n2 = n2.parent;
			}
			
			node1 = node1.parent;
		}
		return null;
	}
	

	private static <T> void makeLOLrecursive(ArrayList<MyLinkedList<BinaryTree<T>.Node>> lol, BinaryTree<T>.Node node, int depth) {
		if (node == null) return;
		if (depth >= lol.size())
			lol.add(new MyLinkedList<BinaryTree<T>.Node>());
		lol.get(depth).addHead(node);
		
		makeLOLrecursive(lol, node.left, depth + 1);
		makeLOLrecursive(lol, node.right, depth + 1);
	}

	
	public static <T> ArrayList<MyLinkedList<BinaryTree<T>.Node>> ex4_4_makeListOfLists(BinaryTree<T> tree) {
		ArrayList<MyLinkedList<BinaryTree<T>.Node>> listOfLists = new ArrayList<MyLinkedList<BinaryTree<T>.Node>>();

		makeLOLrecursive(listOfLists, tree.root, 0);
		
		return listOfLists;
	}

	public static <T> BinaryTree<T>.Node ancestor_stacksExtraSpace(BinaryTree<T>.Node node1, BinaryTree<T>.Node node2) {
		// linear time in tree height
		Stack<BinaryTree<T>.Node> stack1 = new Stack<BinaryTree<T>.Node>();
		Stack<BinaryTree<T>.Node> stack2 = new Stack<BinaryTree<T>.Node>();
		
		while (node1 != null) {
			stack1.push(node1);
			node1 = node1.parent;
		}
		while (node2 != null) {
			stack2.push(node2);
			node2 = node2.parent;
		}
		
		BinaryTree<T>.Node ancestor = null;
		while (stack1.peek() == stack2.peek()) {
			ancestor = stack1.pop();
			stack2.pop();
		}
		return ancestor;
	}
	
	private static <T> boolean isAncestor(BinaryTree<T>.Node above, BinaryTree<T>.Node below) {
		if (above == null) return false;
		if (above == below) return true; // (we are calling a node its own ancestor)
		return isAncestor(above.left, below) || isAncestor(above.right, below);
	}
	


	static <T> BinaryTree<T>.Node ex4_6_nextNode(BinaryTree<T>.Node node) {
		if (node.right != null) {
			node = node.right;
			while (node.left != null)
				node = node.left;
			return node;
		}
		// else 
		else {
			while (isRightChild(node))
				node = node.parent;
			if (isLeftChild(node)) {
				node = node.parent;
				while (node.left != null)
					node = node.left;
				return node;
			}
			else
				return null;
		}
	}

	public static void main(String[] args) {
		BinaryTree<Integer> t = new BinaryTree<Integer>();

	}
}
