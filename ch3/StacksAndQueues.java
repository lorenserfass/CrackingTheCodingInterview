class StacksAndQueues {


	public static void main(String[] args) {


		// ex 3.3
		System.out.println("Testing 3.3");
		SetOfStacks<Integer> ss = new SetOfStacks<Integer>(5);
		for (int i = 0; i < 100; i++) { ss.push(i); }
		while (ss.size() > 0) { System.out.println(ss.pop()); }


		// ex 3.5
		System.out.println("Testing 3.5");
		MyStack<Integer> s = new MyStack<Integer>();
		s.push(2);
		s.push(5);
		s.push(4);
		s.push(3);
		s.push(1);
		s.push(3);
		System.out.println("Before sorting:");
		s.print();
		System.out.println("After sorting:");
		SortStack.sortStack(s);
		s.print();
	}

}
