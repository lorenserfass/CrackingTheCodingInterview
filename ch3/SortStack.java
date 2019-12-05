class SortStack {

	private static <T extends Comparable> void placeMin(Stack<T> stack, Stack<T> holder) {
		T min = stack.peek();

		while (stack.size() > 0) {
			// move all items to other stack, keeping track of min
			
		}

		// move all items back to stack, stopping at min, and move
		// everything on stack to holder.


		// place min


		// 

	}

	public static <T extends Comparable> void sortStack(Stack<T> stack) {
		int numberPlaced = 0;

		Stack<T> holder = new Stack<T>();

		while (numberPlaced < stack.size()) {
			placeMin(stack, holder);
		}

	}

	

}
