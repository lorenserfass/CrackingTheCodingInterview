import java.util.Arrays;


class UnionFind {

	private int[] parent;
	private int[] size;
	private int capacity;
	private int groups;

	public UnionFind(int capacity) {
		parent = new int[capacity];
		size = new int[capacity];
		for (int i = 0; i < capacity; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		this.capacity = capacity;
		this.groups = capacity;
	}

	public void union(int i, int j) {
		i = find(i);
		j = find(j);
		if (i == j) return;
		if (size[i] > size[j]) {
			// add j's tree to i
			size[i] += size[j];
			parent[j] = i;
		}
		else {
			// add i's tree to j
			size[j] += size[i];
			parent[i] = j;
		}
		groups--;
	}

	public int groups() { return groups; }

	public boolean connected(int i, int j) { return find(i) == find(j); }

	private int find(int i) {
		while (parent[i] != i) i = parent[i];
		return i;
	}

	public void resize(int capacity) {
		if (capacity <= this.capacity) return;

		int diff = capacity - this.capacity;

		parent = Arrays.copyOf(parent, capacity);
		size = Arrays.copyOf(size, capacity);

		for (int i = this.capacity; i < capacity; i++) {
			parent[i] = i;
			size[i] = 1;
		}

		this.capacity = capacity;
		groups += diff;
	}

	private int size(int i) {
		return size[find(i)];
	}


}
