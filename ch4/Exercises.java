class Exercises {

	static boolean isRight(BinaryTree.BinaryNode node) {
		if (node.parent == null)
			return false;
		return node.parent.right == node;
	}

	static boolean isLeft(BinaryTree.BinaryNode node) {
		if (node.parent == null)
			return false;
		return node.parent.left == node;
	}

	/*
	private static null blah(BinaryTree.BinaryNode node) {
		if (node == null)
			return;
	}

	static MyLinkedList<MyLinkedList<T>> ex4_4_makeLinkedLists(BinaryTree<T> tree) {
		
	}*/


	static BinaryTree.BinaryNode next_ex4_6(BinaryTree.BinaryNode node) {
		if (node.right != null) {
			node = node.right;
			while (node.left != null)
				node = node.left;
			return node;
		}
		// else 
		else {
			while (isRight(node))
				node = node.parent;
			if (isLeft(node)) {
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
		t.new BinaryNode<Integer>();

	}
}
