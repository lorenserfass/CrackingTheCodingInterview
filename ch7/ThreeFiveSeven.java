import java.util.ArrayList;
import java.util.TreeSet;

class ThreeFiveSeven {

	private TreeSet<Integer> nums;
	private ArrayList<Integer> numsList;
    
    public ThreeFiveSeven() {
        nums = new TreeSet<Integer>();
        numsList = new ArrayList<Integer>();
        nums.add(3);
        nums.add(5);
        nums.add(7);
        
        numsList.add(3);
        numsList.add(5);
        numsList.add(7);
    }
    
    private int minOfThree(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
    
    private int getPotentialNext(int largestSoFar, int multiplier) {
    	// return the smallest number not yet in the set, that is
    	// formed by multiplying a number in the set by this multiplier
    	int x = nums.higher((int) Math.ceil((double) largestSoFar / multiplier));
    	
    	return x * multiplier;
    }
    
    private int addNext() {
    	int largestSoFar = numsList.get(numsList.size() - 1);
    	int next = minOfThree(getPotentialNext(largestSoFar, 3),
    						  getPotentialNext(largestSoFar, 5),
    						  getPotentialNext(largestSoFar, 7));
    	nums.add(next);
    	numsList.add(next);
    	return next;
    }
    
    public int get(int k) {
    	// k = 0 returns 3
    	// k = 1 returns 5
    	// ...
        while (nums.size() < k + 1) addNext();
        return numsList.get(k); // would like to use nums.getIndex(k - 1) but it doesn't exist
    }
    
    public static void main(String[] args) {
        ThreeFiveSeven x = new ThreeFiveSeven();
    
        System.out.println(x.get(3));
        System.out.println(x.get(2));
    
    }



}
