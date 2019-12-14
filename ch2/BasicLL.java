public class BasicLL<T> {

	public BasicLL<T> next;
	public T value;


	public BasicLL(T val) {
		value = val;
	}

	/*
	// constant time
	public BasicLL addAtBeginning(T val) {
		BasicLL toAdd = new BasicLL(val);
		toAdd.next = next;
		return toAdd;
	}

	// linear time
	public void addAtEnd(T val) {
		BasicLL ll = this;
		while (ll.next != null) ll = ll.next; // point to last current node
		ll.next = new BasicLL(val);
	}

	// constant time
	public T removeFromBeginning() throws Exception {
		if (next == null)
			throw new java.lang.Exception();

		BasicLL vbll = next;
		next = next.next; // is this enough??
		vbll.next = null;
		return (T) vbll.value;
	}


	// linear time
	public int size() {
		int n = 0;
		BasicLL ll = next;
		while (ll != null) {
			n++;
			ll = ll.next;
		}
		return n;
	}*/

	public void print() {
		BasicLL<T> vbll = this;
		while (vbll != null) {
			System.out.println(vbll.value);
			vbll = vbll.next;
		}
	}


}

