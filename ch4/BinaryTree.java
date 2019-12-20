class BinaryTree<T> {

	Node root = null;

	class Node {
		T value;
		Node parent; // used in ex 4.6, also ancestor exercise
		Node left;
		Node right;
		
		Node(T v) {
			value = v;
			left = null;
			right = null;
		}
	}

	
	

}
