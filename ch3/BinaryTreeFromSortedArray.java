import java.util.Vector;

public class BinaryTreeFromSortedArray<T extends Comparable<T>> {

Node root = null;

class Node {
Node left;
Node right;
T value;

Node(T v, Node l, Node r) {
value = v;
left = l;
right = r;
}
}

public BinaryTreeFromSortedArray(Vector<T> sortedArray) {
for (int i = 0; i < sortedArray.size(); i++) {
root = insertInSequence(root, i, sortedArray.get(i));
}
}

private static int log2(int n) {
if(n <= 0) throw new IllegalArgumentException();
   return 31 - Integer.numberOfLeadingZeros(n);
}

private static int powerOfTwo(int power) {
   return 1 << power;
}

private boolean isPowerOf2(int x) {
   return x > 0 && (x & (x - 1)) == 0;
}

public static int floorPowerOfTwo(int n) {
if (n <= 0) throw new IllegalArgumentException();
return 1 << (31 - Integer.numberOfLeadingZeros(n));
}

private Node insertInSequence(Node node, int i, T value) {
if (node == null) return new Node(value, null, null);

int depthSoFar = log2(i) + 1;
boolean isFull = powerOfTwo(depthSoFar) == (i + 1);

// System.out.println(i + "\t" + depthSoFar + "\t" + isFull + "\t" + powerOfTwo(log2(i)));

if (isFull) return new Node(value, node, null);
// node.right = insertInSequence(node.right, i - powerOfTwo(log2(i)), value);
node.right = insertInSequence(node.right, i - (1 << (31 - Integer.numberOfLeadingZeros(i))), value);
return node;
}

private void printRecursive(StringBuilder sb, Node node, int depth) {
if (node == null) return;

printRecursive(sb, node.right, depth + 1);
sb.append("\n");
for (int i = 0; i < depth; i++)
sb.append("  ");
sb.append(node.value);
printRecursive(sb, node.left, depth + 1);
}

public String toString() {
StringBuilder sb = new StringBuilder();
printRecursive(sb, root, 0);
return sb.toString();
}



public static void main(String[] args) {

for (int i = 1; i < 18; i++) {
Vector<Integer> v = new Vector<Integer>();
for (int j = 0; j < i; j++) v.add(j);
BinaryTreeFromSortedArray<Integer> bt = new BinaryTree<Integer>(v);
System.out.println("#########################################");
System.out.println("#########################################");
System.out.println(bt);
}

/* for (int i = 1; i <= 17; i++) {
System.out.println(i + "\t" + floorPowerOfTwo(i));
}*/




}

}


