import java.util.HashSet;
import java.util.Arrays;

class Ex9_5_allPermutations {
	
	public static int factorial(int n) {
		int product = 1;
		for (int i = 1; i <= n; i++) product *= i;
		return product;
	}

	private static void swap(char[] ch, int i, int j) {
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}


	/**
	 * Make all permutations of the characters in the string.
	 * @param s
	 * @return Set of all permutations.
	 */
	public static HashSet<String> allPermutations(String s) {
		char[] ch = s.toCharArray();
		HashSet<String> permutations = new HashSet<String>();
		allPermutations(permutations, ch, 0);
		return permutations;
	}

	private static void allPermutations(HashSet<String> permutations, char[] ch, int i) {

		if (i == ch.length - 1) {
			permutations.add(new String(ch));
			return;
		}


		for (int j = i; j < ch.length; j++) {
			char[] ch2 = Arrays.copyOf(ch, ch.length);
			swap(ch2, i, j);
			allPermutations(permutations, ch2, i + 1);
		}

	}

	public static void main(String[] args) {
		
		// test it (8 is enough)
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		for (int i = 1; i <= 8; i++) {
			char[] ch = Arrays.copyOf(alphabet, i); // make distinct char array of length i
			HashSet<String> stringSet = allPermutations(new String(ch)); // get all permutations
			assert stringSet.size() == factorial(i);
		}
	}

}
