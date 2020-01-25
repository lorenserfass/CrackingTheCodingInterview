import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ch9Tests {

	private static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	// TODO: test this
	private static void shuffle(int[] arr) {
		Random r = new Random();
		for (int i = 0; i < arr.length; i++) {
			swap(arr, i, i + r.nextInt(arr.length - i));
		}
	}

	public static int[] makeRandomSortedArrayWithoutDuplicates(int n, int lb, int ub) {

		int[] arr = new int[ub - lb];

		for (int i = lb; i < ub; i++) arr[i - lb] = i;

		shuffle(arr);

		int[] blah = Arrays.copyOf(arr, n);
		Arrays.sort(blah);
		return blah;
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		assert Ex9_2_Robot.getNumPaths1(3, 4) == 10;
		
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		pairs.add(new Pair(1, 1));
		pairs.add(new Pair(2, 1));
		pairs.add(new Pair(1, 2));
		
		assert Ex9_2_Robot.getNumPaths2(4, 5, pairs) == 5;
		
		
		for (int i = 0; i < 1000; i++) {
			int[] arr = makeRandomSortedArrayWithoutDuplicates(10, -10, 20);
			int mi1 = Ex9_3_MagicIndex.magicIndexLinear(arr);
			int mi2 = Ex9_3_MagicIndex.magicIndexLogN(arr);
			if (mi1 == -1)
				assert mi2 == -1;
			else {
				System.out.println("magic index found");
				assert arr[mi2] == mi2;
			}
		}
		
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 10; j++) {
				int n1 = Ex9_2_Robot.getNumPaths1(i, j);
				int n2 = Ex9_2_Robot.getNumPaths1_1(i, j);
				long n3 = Ex9_2_Robot.getNumPaths1_2(i, j);
				assert n1 == n2;
				assert n1 == n3;
			}
		}
		
	}

}
