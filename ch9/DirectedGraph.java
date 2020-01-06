import java.util.LinkedList;
import java.util.Stack;

public class DirectedGraph {

	private final int N;
	private LinkedList<Edge>[] adj;

	class Edge {
		int from;
		int to;
		int weight;
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	public DirectedGraph(int N) {
		this.N = N;
		adj = new LinkedList[N];
		for (int i = 0; i < N; i++)
			adj[i] = new LinkedList<Edge>();
	}

	public void addEdge(int from, int to, int weight) {
		adj[from].add(new Edge(from, to, weight));
	}


	private int[] topologicalSort() {
		// TODO: detect cycle
		Stack<Integer> stack = new Stack<Integer>();
		int[] sorted = new int[N];
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++)
			if (!visited[i])
				visit(i, stack, visited);
		for (int i = 0; i < N; i++)
			sorted[i] = stack.pop();
		return sorted;
	}

	private void visit(int start, Stack<Integer> stack, boolean[] visited) {
		visited[start] = true;
		for (Edge e : adj[start])
			if (!visited[e.to])
				visit(e.to, stack, visited);
		stack.push(start);
	}

	public int[] longestPath() {
		int[] topo = topologicalSort();
		int[] distances = new int[N];
		int[] previous = new int[N];
		for (int i = 0; i < N; i++) {
			distances[i] = 0;
			previous[i] = -1;
		}
		for (int topoIndex = 0; topoIndex < N; topoIndex++) {
			// look at node topo[topoIndex], follow its edges, update arrays
			int i = topo[topoIndex];
			for (Edge e : adj[i]) {
				if (distances[e.to] < distances[i] + e.weight) {
					distances[e.to] = distances[i] + e.weight;
					previous[e.to] = i;
				}
			}
		}
		// find last index of the longest path
		int lastIndex = 0;
		for (int i = 0; i < N; i++)
			if (distances[i] > distances[lastIndex])
				lastIndex = i;
		Stack<Integer> stack = new Stack<Integer>();
		while (previous[lastIndex] != -1) {
			stack.push(lastIndex);
			lastIndex = previous[lastIndex];
		}
		int[] path = new int[stack.size()];
		int i = 0;
		while (!stack.isEmpty())
			path[i++] = stack.pop();
		return path;
	}




	public static void main(String[] args) {



	}


}
