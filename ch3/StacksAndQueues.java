class StacksAndQueues {


	public static void main(String[] args) {

		SetOfStacks<Integer> ss = new SetOfStacks<Integer>(5);

		for (int i = 0; i < 100; i++) {
			ss.push(i);
		}

		while (ss.size() > 0) {
			System.out.println(ss.pop());
		}


	}

}
