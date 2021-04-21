import java.util.Scanner;

class Main {

	private Node root;

	private class Node {
		private int key;
		private int balance;
		private Node left, right, parent;

		Node(int k, Node p) {
			key = k;
			parent = p;
		}
	}

	public void insert(int key) {
		Node x = new Node(key, null);
		if (root == null) {
			root = x;
		}
		if (root.key == key) {
			return;
		}
		Node current = root;
		Node next = null;
		if (root.key > key) {
			next = root.left;
		} else {
			next = root.right;
		}

		while (next != null) {
			if (next.key == key) {
				return;
			} else if (next.key > key) {
				current = next;
				next = next.left;
			} else {
				current = next;
				next = next.right;
			}
		}
		if (current.key > key) {
			current.left = x;
		} else {
			current.right = x;
		}
		x.parent = current;
		rebalance(x);
	}

	public void delete(int delKey) {
		Node current = null;
		Node next = root;
		if (next == null) {
			return;
		}

		while (next != null && next.key != delKey) {

		}
	}

	// rebalances the given node; then checks if rotations are needed
	// and performs them accordingly.
	private void rebalance(Node n) {
		setBalance(n);

		if (n.balance == -2) {
			if (height(n.left.left) >= height(n.left.right))
				n = rotateRight(n);
			else
				n = rotateLeftThenRight(n);

		} else if (n.balance == 2) {
			if (height(n.right.right) >= height(n.right.left))
				n = rotateLeft(n);
			else
				n = rotateRightThenLeft(n);
		}

		if (n.parent != null) {
			rebalance(n.parent);
		} else {
			root = n;
		}
	}

	private Node rotateLeft(Node a) {
		/*
		 * parent parent | | a b \ / b a
		 */

		Node b = a.right;
		b.parent = a.parent;
		a.right = b.left;

		if (b.left != null) {
			a.right.parent = a;
		}

		a.parent = b;
		b.left = a;

		if (b.parent != null) {
			if (b.parent.left == a) {
				b.parent.left = b;
			} else {
				b.parent.right = b;
			}
		}
		setBalance(a, b);

		return b;
	}

	private Node rotateRight(Node a) {
		/*
		 * parent parent | | a b / \ b a
		 */

		Node b = a.left;
		b.parent = a.parent;

		a.left = b.right;

		if (a.left != null)
			a.left.parent = a;

		b.right = a;
		a.parent = b;

		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}

		setBalance(a, b);

		return b;
	}

	private Node rotateLeftThenRight(Node n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}

	private Node rotateRightThenLeft(Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}

	// recursive function that gives the height of a node
	private int height(Node n) {
		if (n == null)
			return -1;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	// sets/updates the balance of a set of nodes
	private void setBalance(Node... nodes) {
		for (Node n : nodes)
			n.balance = height(n.right) - height(n.left);
	}

	// prints the balance vector in-order
	public void printBalance() {
		printBalance(root);
		System.out.println();
	}

	private void printBalance(Node n) {
		if (n != null) {
			printBalance(n.left);
			System.out.printf("%s ", n.balance);
			printBalance(n.right);
		}
	}

	// this functions prints the tree nodes in-order;
	// prints node.key:height(node);
	// maybe useful for debugging.
	private void printTree() {
		printTree(root);
		System.out.println();
	}

	private void printTree(Node n) {
		if (n != null) {
			printTree(n.left);
			System.out.print(n.key + ":" + height(n) + " ");
			printTree(n.right);
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int test = in.nextInt();

		for (int t = 0; t < test; t++) {
			Main tree = new Main();
			int n = in.nextInt();

			for (int i = 0; i < n; i++) {
				int d = in.nextInt();
				if (d > 0) {
					// Inserting d
					tree.insert(d);
				} else if (d < 0) {
					// Deleting |d|;
					tree.delete(-d);
				}
			}

			// print the Balance Vector
			tree.printBalance();
		}
	}
}
