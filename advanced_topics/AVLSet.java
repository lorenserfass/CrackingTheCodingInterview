import java.util.Random;

class AVLSet<K extends Comparable<K>> {

	private Node root;
	private Random random;

	class Node {
		K key;
		Node l; // left
		Node r; // right
		int height;
		int size;
		Node(K key, int height, int size) {
			this.key = key;
			this.height = height;
			this.size = size;
			this.l = null;
			this.r = null;
		}
	}

	public AVLSet() {
		root = null;
		random = new Random();
	}

	private int height(Node x) {
		return (x == null) ? 0 : x.height;
	}

	private void setHeight(Node x) {
		if (x == null) return;
		x.height = 1 + Math.max(height(x.l), height(x.r));
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		return (x == null) ? 0 : x.size;
	}

	private void setSize(Node x) {
		if (x == null) return;
		x.size = 1 + size(x.l) + size(x.r);
	}


	public K randomKey() {
		int r = random.nextInt(size(root));
		return getKeyAtIndex(root, r);
	}

	public K getKeyAtIndex(Node x, int i) {
		// TODO: block out of bounds
		if      (i == size(x.l)) return x.key;
		else if (i <  size(x.l)) return getKeyAtIndex(x.l, i);
		else                     return getKeyAtIndex(x.r, i - 1 - size(x.l));
	}


	// insertion

	public void insert(K key) {
		root = insert(root, key);
	}

	private Node insert(Node x, K key) {
		if (x == null) return new Node(key, 1, 1);

		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.l = insert(x.l, key);
		else if (cmp > 0) x.r = insert(x.r, key);
		else {
			x.key = key;
			return x;
		}

		// maintain AVL balance
		setHeight(x);
		setSize(x);
		x = balance(x);

		return x;
	}

	private Node balance(Node x) {
		if (x == null) return null;

		int diff = height(x.r) - height(x.l);

		if (diff == 2) {
			// right tree is taller and height of right tree
			// is at least 2 (a null node has height 0)
			if (height(x.r.l) > height(x.r.r)) // detect "right left" shape
				x.r = rotateRight(x.r);
			x = rotateLeft(x);
		}
		else if (diff == -2) {
			// left tree is taller and height of left tree
			// is at least 2 (a null node has height 0)
			if (height(x.l.r) > height(x.l.l)) // detect "left right" shape
				x.l = rotateLeft(x.l);
			x = rotateRight(x);
		}

		return x;
	}

	private Node rotateLeft(Node x) {
		Node t = x.r;
		x.r = t.l;
		t.l = x;
		setHeight(x);
		setHeight(t);
		setSize(x);
		setSize(t);
		return t;
	}

	private Node rotateRight(Node x) {
		Node t = x.l;
		x.l = t.r;
		t.r = x;
		setHeight(x);
		setHeight(t);
		setSize(x);
		setSize(t);
		return t;
	}


	// deletion
	public void delete(K key) {
		root = delete(root, key);
	}

	private Node delete(Node x, K key) {
		if (x == null) return null; // key not found

		// System.out.println(x.key);

		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.l = delete(x.l, key);
		else if (cmp > 0) x.r = delete(x.r, key);
		else { // key found! delete node x.
			if      (x.l == null) x = x.r;
			else if (x.r == null) x = x.l;
			else {
				// x has two children
				Node toLetGo = x;

				// replace x by either successor or predecessor, randomly chosen
				if (random.nextBoolean()) {
					x = successor(x);
					x.r = deleteMin(toLetGo.r);
					x.l = toLetGo.l;
				}

				else {
					x = predecessor(x);
					x.l = deleteMax(toLetGo.l);
					x.r = toLetGo.r;
				}
				// Since we are returning x, which is now different from toLetGo,
				// and the only link to toLetGo was in its parent Node which we
				// are resetting to x,
				// there are no more links to toLetGo and it is garbage.
			}
		}

		// maintain AVL balance
		setHeight(x);
		setSize(x);
		x = balance(x);

		return x;
	}



	private Node deleteMin(Node x) {
		if (x.l == null) return x.r;
		x.l = deleteMin(x.l);
		setHeight(x);
		setSize(x);
		x = balance(x);
		return x;
	}

	private Node deleteMax(Node x) {
		if (x.r == null) return x.l;
		x.r = deleteMax(x.r);
		setHeight(x);
		setSize(x);
		x = balance(x);
		return x;
	}

	private Node successor(Node x) {
		// assumes that x.r is not null
		Node s = x.r;
		while (s.l != null) s = s.l;
		return s;
	}

	private Node predecessor(Node x) {
		// assumes x.l is not null
		Node p = x.l;
		while (p.r != null) p = p.r;
		return p;
	}
		

	public String toString() {
		StringBuilder sb = new StringBuilder();
		addToString(sb, root, 0);
		return sb.toString();
	}

	private void addToString(StringBuilder sb, Node x, int depth) {
		if (x == null) return;
		addToString(sb, x.r, depth + 1);
		for (int i = 0; i < depth; i++) sb.append("\t");
		sb.append(x.key + "," + x.height + "," + x.size + "\n");
		addToString(sb, x.l, depth + 1);
	}

	public static void main(String[] args) {
		Random random = new Random();
		AVLSet<Integer> avl = new AVLSet<Integer>();


		avl.insert(1);
		avl.insert(2);
		avl.insert(3);
		avl.insert(4);
		avl.insert(5);
		avl.insert(7);
		avl.insert(8);

		System.out.println(avl);
		avl.delete(6);
		System.out.println("\n\n\n" + avl);
	}

}



