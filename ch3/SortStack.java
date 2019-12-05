class SortStack {

	private static <T extends Comparable<T>> void placeMin(Stack<T> stack, Stack<T> holder) {
		// this function finds the smallest element of "holder" stack and places it on "stack" stack
		T min = holder.peek();
		int minIndex = 0;

		int numberHeld = 0;
		// this loop should find the value and index of smallest element on "holder" stack
		while (!holder.isEmpty()) {
			if (holder.peek().compareTo(min) < 0) {
				min = holder.peek();
				minIndex = numberHeld;
			}
			numberHeld++;
			stack.push(holder.pop());
		}

		// move all items back to "holder." except skip min element, holding it
		// and putting it on "stack"
		for (int i = numberHeld - 1; i >= 0; i--) {
			if (i != minIndex)
				holder.push(stack.pop());
			else
				stack.pop();
		}

		// place min
		stack.push(min);
	}

	public static <T extends Comparable<T>> void sortStack(Stack<T> stack) {
		// I'm assuming we can either transfer an element directly from
		// stack to stack, or hold exactly one element aside.
		// If we can only transfer directly, there's no way of reordering.

		Stack<T> holder = new Stack<T>();
		while (stack.size() > 0) holder.push(stack.pop());

		int n = holder.size();
		for (int i = 0; i < n; i++)
			placeMin(stack, holder);
	}
}

