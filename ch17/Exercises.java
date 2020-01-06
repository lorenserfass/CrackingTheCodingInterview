import java.util.Arrays;
import java.util.Random;

class Exercises {


	private static String[] counts = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
			"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	private static String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	private static String[] thousandPowers = {"", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion", "sextillion", "septillion", "octillion"};

	/**
	 * Ex 17.1
	 * Swap two integers (here, in an array) without a temp variable.
	 * @param x
	 * @param i
	 * @param j
	 */
	public static void swapWithoutTemp(int[] x, int i, int j) {
		x[i] = x[i] ^ x[j];
		x[j] = x[i] ^ x[j];
		x[i] = x[i] ^ x[j];
	}

	
	/**
	 * Ex 17.7
	 * Prints the number in English.
	 * NOT DONE.
	 * @param x
	 * @return
	 */
	public static String numberInEnglish(int x) {

		if (x == 0) return "zero";

		String s = "";

		int thousandPower = 0;
		while (x != 0) {
			int r = x % 1000;
			x /= 1000;
			String s2 = "";
			if (r >= 100) {
				s2 = counts[r / 100] + " hundred";
				r %= 100;
			}
			if (r < 20 && r > 0) {
				s2 = s2 + " " + counts[r];
			}
			else if (r > 0) {
				s2 = s2 + " " + tens[r / 10];
				r %= 10;
				if (r != 0)
					s2 = s2 + "-" + counts[r];
			}
			if (thousandPower > 0)
				s2 = s2 + " " + thousandPowers[thousandPower];
			s = s2 + " " + s;
			thousandPower++;
		}

		return s;
	}


	private static int rand5() {
		return new Random().nextInt(5);
	}
	
	private static int rand4() {
		int r;
		do r = rand5(); while (r != 4);
		return r;
	}
	
	private static int rand8() {
		int r = rand4();
		if (rand4() % 2 == 0)
			return r + 4;
		else
			return r;
	}

	/**
	 * Ex 17.11
	 * Uses rand5() to generate rand7()
	 * @return
	 */
	public static int rand7() {
		int r;
		do r = rand8(); while (r != 7);
		return r;
	}
	
	
	/**
	 * Ex.
	 * NOT DONE
	 * @param x
	 * @param sum
	 */
	public void allPairsThatSum(int[] x, int sum) {
		int[] y = Arrays.copyOf(x, x.length);
		Arrays.sort(y);
		int lb = 0;
		int ub = x.length - 1;
	}

	

	public static void main(String[] args) {



		for (int i = 0; i <= 100; i++) {
			System.out.println(numberInEnglish(i));
		}
		System.out.println(numberInEnglish(123456789));




	}

}
