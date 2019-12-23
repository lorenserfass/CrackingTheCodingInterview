import java.util.HashSet;
import java.util.ArrayList;

class Ex9_4_allSubsets {

	public static ArrayList<ArrayList<Integer>> allSubsets(int[] set) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		
		list.add(new ArrayList<Integer>()); // adding the empty set
		
		for (int j = 0; j < set.length; j++) {
			int n = list.size();
			for (int k = 0; k < n; k++) {
				ArrayList<Integer> set2 = (ArrayList<Integer>) list.get(k).clone();
				set2.add(set[j]);
				list.add(set2);
			}
		}
		return list;
	}

	
	public static void print(ArrayList<Integer> blah) {
		for (int x : blah) System.out.print(x + ", ");
		System.out.println();
	}

	public static void main(String[] args) {
		int[] set = {1, 3, 5, 7};

		ArrayList<ArrayList<Integer>> subsets = allSubsets(set);
		System.out.println(subsets.size());
		for (ArrayList<Integer> hs : subsets)
			print(hs);

	}
}
