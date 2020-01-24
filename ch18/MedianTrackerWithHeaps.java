import java.util.Random;

public class MedianTrackerWithHeaps {
	
	private PQ<Integer> hi; // above or equal to median
	private PQ<Integer> lo; // below or equal to median
	
	public MedianTrackerWithHeaps() {
		hi = new PQ<Integer>(true);  // a min-heap
		lo = new PQ<Integer>(false); // a max-heap
	}
	
	// TODO: this seems unclean
	public void track(int x) {
		if (hi.size() == 0 && lo.size() == 0) {
			lo.enqueue(x);
			return;
		}
		if (x >= median())
			hi.enqueue(x);
		else
			lo.enqueue(x);
		if (lo.size() < hi.size()) {
			while (lo.size() < hi.size())
				lo.enqueue(hi.dequeue());
		}
		else if (hi.size() < lo.size()) {
			while (hi.size() < lo.size())
				hi.enqueue(lo.dequeue());
		}
	}
	
	public double median() {
		if (lo.size() > hi.size())
			return lo.peek();
		else if (hi.size() > lo.size())
			return hi.peek();
		else
			return (lo.peek() + hi.peek()) / 2.0;
	}

	public static void main(String[] args) {
		MedianTracker mt = new MedianTracker();
		MedianTrackerWithHeaps mth = new MedianTrackerWithHeaps();
		
		Random random = new Random();
		// run two median trackers in parallel, test whether they agree
		for (int i = 0; i < 1000; i++) {
			int r = random.nextInt(5000);
			mt.track(r);
			mth.track(r);

			assert mt.median() == mth.median();
		}
		

	}

}

