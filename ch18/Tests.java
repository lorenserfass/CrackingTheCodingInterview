import java.util.Vector;
import java.util.Arrays;
import java.util.Random;

class Tests {


	public static void main(String[] args) {
		/*Vector<Integer> arraySizes = new Vector<Integer>();
		for (int i = 1; i <= 5; i++)
			for (int j = 2; j <= 10; j += 2)
				arraySizes.add((int) Math.pow(10, i) * j);
		for (int i : arraySizes) System.out.println(i);*/
		Random r = new Random();
		
		int[] arraySizes = {20, 40, 60, 80, 100, 200, 400, 600, 800, 1000, 2000, 4000, 6000, 8000,
						10000, 20000, 40000, 60000, 80000, 100000, 200000, 400000, 600000, 800000, 1000000};
		
		int partitionIncrements = 5;
		
		int ntrials = 10;
		
		int trial = 0;
		
		long startTime, duration;
		
		for (int size : arraySizes) {
			for (int i = 1; i < partitionIncrements; i++) {
				int numberToPartition = size * i / partitionIncrements;
				for (int j = 0; j < ntrials; j++) {
					int[] array = new int[size];
					for (int k = 0; k < size; k++) array[k] = r.nextInt();
					int[] array2 = Arrays.copyOf(array, size);
					// TODO: not done here, was interested in timing things
					
					trial++;
				}
			}
		}
	}

}
