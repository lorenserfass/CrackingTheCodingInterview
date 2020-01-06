import java.util.ArrayList;
import java.util.HashMap;

public class DFA<T> {

	private int state;
	private int numStates;
	private ArrayList<HashMap<T, Integer>> dfa; // map states with input to next state

	public DFA(T[] pattern) {
		state = 0;
		numStates = pattern.length;

		dfa = new ArrayList<HashMap<T, Integer>>();

		dfa.add(new HashMap<T, Integer>());
		dfa.get(0).put(pattern[0], 1);

		for (int x = 0, j = 1; j < pattern.length; j++) {
			dfa.add((HashMap<T, Integer>) dfa.get(x).clone());
			dfa.get(j).put(pattern[j], j + 1); // set match case
			if (dfa.get(x).containsKey(pattern[j]))
				x = dfa.get(x).get(pattern[j]);
			else
			x = 0;
		}
	}

	public DFA(ArrayList<T> pattern) {
		state = 0;
		numStates = pattern.size();

		dfa = new ArrayList<HashMap<T, Integer>>();

		dfa.add(new HashMap<T, Integer>());
		dfa.get(0).put(pattern.get(0), 1); // TODO: deal with patterns of 0 size

		for (int x = 0, j = 1; j < pattern.size(); j++) {
			dfa.add((HashMap<T, Integer>) dfa.get(x).clone());
			dfa.get(j).put(pattern.get(j), j + 1); // set match case
			if (dfa.get(x).containsKey(pattern.get(j)))
				x = dfa.get(x).get(pattern.get(j));
			else
			x = 0;
		}
	}

	public boolean isFinished() {
		return state == numStates;
	}
	
	public boolean transition(T t) {
		// move to next state and return whether dfa is finished
		if (state >= numStates) throw new java.util.NoSuchElementException("the dfa is finished");

		if (dfa.get(state).containsKey(t))
			state = dfa.get(state).get(t);
		else
			state = 0;

		return isFinished();
	}


	/**
	 * Knuth-Morris-Pratt string search algorithm.
	 * Searches series for pattern.
	 * @param series
	 * @param pattern
	 * @return index at which pattern is found in series, or -1 if not found
	 */
	public static <U> int search(U[] series, U[] pattern) {
		DFA<U> dfa2 = new DFA<U>(pattern);
		for (int i = 0; i < series.length; i++)
			if (dfa2.transition(series[i]))
				return i - pattern.length + 1;
		return -1;
	}


	public static void main(String[] args) {
		// *  text:    abacadabrabracabracadabrabrabracad
		// *  pattern:               abracadabra

		String[] text = {"a", "b", "a", "c", "a", "d", "a", "b", "r", "a", "b", "r",
				"a", "c", "a", "b", "r", "a", "c", "a", "d", "a",
				"b", "r", "a", "b", "r", "a", "b", "r", "a", "c", "a", "d"};
		String[] pattern = {"a", "b", "r", "a", "c", "a", "d", "a", "b", "r", "a"};

		System.out.println(search(text, pattern));
	}

}
