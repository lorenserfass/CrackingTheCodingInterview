import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Ex9_10_Boxes {
	
	public class Box implements Comparable<Box> {
		int width;
		int height;
		int depth;
		public Box(int width, int height, int depth) {
			this.width = width;
			this.height = height;
			this.depth = depth;
		}

		@Override
		public int compareTo(Box other) {
			if (this.height < other.height) return -1;
			else if (this.height > other.height) return 1;
			else {
				if (this.width < other.width) return -1;
				else if (this.width > other.width) return 1;
				else {
					if (this.depth < other.depth) return -1;
					if (this.depth > other.depth) return 1;
				}
			}
			return 0;
		}
		
		public boolean fitsIn(Box other) {
			return this.height < other.height && this.width < other.width && this.depth < other.depth;
		}
	}
	
	/*public class HeightWidthDepth implements Comparator<Box> {

		@Override
		public int compare(Box b1, Box b2) {
			if (b1.height < b2.height) return -1;
			else if (b1.height > b2.height) return 1;
			else {
				if (b1.width < b2.width) return -1;
				else if (b1.width > b2.width) return 1;
				else {
					if (b1.depth < b2.depth) return -1;
					if (b1.depth > b2.depth) return 1;
				}
			}
				
			return 0;
		}
		
	}*/
	
	/**
	 * Find the highest stack that can be made from the give boxes,
	 * if each box must be larger than the box above it in all dimensions.
	 * UNTESTED.
	 * @param boxes
	 * @return
	 */
	public static Box[] boxStackIterative(Box[] boxes) {
		int n = boxes.length;
		Integer[] index = new Integer[n];
		for (int i = 0; i < n; i++) index[i] = i; // ii = {0, 1, 2 ... n-1}
		
		// index is an index vector, indexing into boxes.
		// Sort the index vector according to the boxes.
		// The index for the "smallest" box comes first
		Arrays.sort(index, Comparator.comparing((i) -> boxes[i]));
		
		// For each box 0 through (n-1),
		// (and now 0 refers to the smallest box, n-1 the largest),
		// store the best stack height reached with that box as the base,
		// and which box stacks on top of that box in the best scenario.
		int[] bestHeights = new int[n];
		Integer[] bestFits = new Integer[n];

		// initialize base case, the smallest box
		bestHeights[0] = boxes[index[0]].height;
		bestFits[0] = null;
		
		// consider the boxes in ascending order of size
		for (int i = 1; i < n; i++) {
			bestHeights[i] = boxes[index[i]].height;
			bestFits[i] = null;
			
			// look at all the smaller boxes to see which makes
			// the best stack with the current box
			for (int j = 0; j < i; j++) {
				if (boxes[index[j]].fitsIn(boxes[index[i]])) {
					int proposedHeight = bestHeights[j] + boxes[index[i]].height;
					if (proposedHeight > bestHeights[i]) {
						bestHeights[i] = proposedHeight;
						bestFits[i] = j;
					}
				}
			}
		} // Now for each box we have the best height that could be formed if
		// that's the bottom box, and we have the box that should sit
		// on top of that one.
		
		// find the best box for the bottom of the stack
		Integer bestBase = 0;
		Integer bestHeight = 0;
		for (int i = 0; i < n; i++) {
			if (bestHeights[i] > bestHeight) {
				bestHeight = bestHeights[i];
				bestBase = i;
			}
		}
		
		// make the stack above the starting box
		ArrayList<Box> boxStack = new ArrayList<Box>();
		while (bestBase != null) {
			boxStack.add(boxes[index[bestBase]]);
			bestBase = bestFits[bestBase];
		}
		
		return (Box[]) boxStack.toArray();
	}


}
