import java.util.ArrayList;

class Tests {

	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();

		ArrayList<BinaryTree<Integer>.Node> nodeList = new ArrayList<BinaryTree<Integer>.Node>();
		for (int i = 0; i < 13; i++)
			nodeList.add(null);

		nodeList.set(0, tree.new Node(0, null, null));
		nodeList.set(2, tree.new Node(2, null, null));
		nodeList.set(4, tree.new Node(4, null, null));
		nodeList.set(3, tree.new Node(3, nodeList.get(2), nodeList.get(4)));
		nodeList.set(1, tree.new Node(1, nodeList.get(0), nodeList.get(3)));
		nodeList.set(6, tree.new Node(6, null, null));
		nodeList.set(7, tree.new Node(7, nodeList.get(6), null));
		nodeList.set(9, tree.new Node(9, null, null));
		nodeList.set(12, tree.new Node(12, null, null));
		nodeList.set(11, tree.new Node(11, null, nodeList.get(12)));
		nodeList.set(10, tree.new Node(10, nodeList.get(9), nodeList.get(11)));
		nodeList.set(8, tree.new Node(8, nodeList.get(7), nodeList.get(10)));
		nodeList.set(5, tree.new Node(5, nodeList.get(1), nodeList.get(8)));
		
		nodeList.get(0).parent = nodeList.get(1);
		nodeList.get(1).parent = nodeList.get(5);
		nodeList.get(2).parent = nodeList.get(3);
		nodeList.get(3).parent = nodeList.get(1);
		nodeList.get(4).parent = nodeList.get(3);
		nodeList.get(5).parent = null;
		nodeList.get(6).parent = nodeList.get(7);
		nodeList.get(7).parent = nodeList.get(8);
		nodeList.get(8).parent = nodeList.get(5);
		nodeList.get(9).parent = nodeList.get(10);
		nodeList.get(10).parent = nodeList.get(8);
		nodeList.get(11).parent = nodeList.get(10);
		nodeList.get(12).parent = nodeList.get(11);


		tree.root = nodeList.get(5);
		tree.fillParents();
		
		
		int[][] ancestors =
			{{0, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5},
			{1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5},
			{1, 1, 2, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5},
			{1, 1, 3, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5},
			{1, 1, 3, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 6, 7, 8, 8, 8, 8, 8},
			{5, 5, 5, 5, 5, 5, 7, 7, 8, 8, 8, 8, 8},
			{5, 5, 5, 5, 5, 5, 8, 8, 8, 8, 8, 8, 8},
			{5, 5, 5, 5, 5, 5, 8, 8, 8, 9, 10, 10, 10},
			{5, 5, 5, 5, 5, 5, 8, 8, 8, 10, 10, 10, 10},
			{5, 5, 5, 5, 5, 5, 8, 8, 8, 10, 10, 11, 11},
			{5, 5, 5, 5, 5, 5, 8, 8, 8, 10, 10, 11, 12}};

		
		System.out.println("Example tree (rotated 90 degrees CCW):");
		System.out.println(tree);

		System.out.print("Checking ancestor calculations... ");
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				assert ancestors[i][j] == tree.Ex4_7_1_ancestor(nodeList.get(i), nodeList.get(j)).value;
				assert ancestors[i][j] == tree.Ex4_7_2_ancestor(nodeList.get(i), nodeList.get(j)).value;
				assert ancestors[i][j] == tree.Ex4_7_3_ancestor(nodeList.get(i), nodeList.get(j)).value;
			}
		}
		System.out.println("OK");

		// Node[][] ancestors = 
	}
}
