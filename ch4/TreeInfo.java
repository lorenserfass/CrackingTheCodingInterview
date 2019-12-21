class TreeInfo<T> {
		T leftmostValue; // used in exercise 4.5
		T rightmostValue; // used in exercise 4.5
		int depth; // used in exercise 4.1
		boolean isBST; // used in exercise 4.5
		boolean isBalanced1; // used in exercise 4.1. Depths of two subtrees of any node differ by no more than 1.
		boolean isBalanced2; // alternative definition: Depths of two subtrees of any node are never off by more than a factor of 2.

		public TreeInfo(T leftmostValue, T rightmostValue, int depth, boolean isBST, boolean isBalanced1, boolean isBalanced2) {
			this.leftmostValue = leftmostValue;
			this.rightmostValue = rightmostValue;
			this.depth = depth;
			this.isBST = isBST;
			this.isBalanced1 = isBalanced1;
			this.isBalanced2 = isBalanced2;
		}
}
