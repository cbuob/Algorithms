import java.util.Scanner;

class TreeNode {

	public TreeNode left, right, parent;
	public int key;

	// Simple constructor
	public TreeNode(int newKey, TreeNode newParent) {
		key = newKey;
		left = null;
		right = null;
		parent = newParent;
	}

	// Print to System.out in a bracket-notation like "(1 (2)) 3 (4)"
	// recursively
	// No newline printed. Note that building and returning a String would be
	// cleaner
	// but much slower if not done carefully.
	public void print() {
		if (left != null) {
			System.out.print("(");
			left.print();
			System.out.print(") ");
		}
		System.out.print(key);
		if (right != null) {
			System.out.print(" (");
			right.print();
			System.out.print(")");
		}
	}

	/*** Methods to implement for 1 point */

	// Insert element "key" into the tree
	// You may assume that it is not there yet
	public void insert(int val) {
		if (val < this.key) {
			if (this.left != null) {
				this.left.insert(val);
			} else {
				TreeNode t = new TreeNode(val, this);
				this.left = t;
			}
		} else {
			if (this.right != null) {
				this.right.insert(val);
			} else {
				TreeNode t = new TreeNode(val, this);
				this.right = t;
			}
		}
	}

	// Return if "key" is in the tree, recursively
	public boolean contains(int val) {
		if (findVal(val) == null) {
			return false;
		} else {
			return true;
		}
	}

	public TreeNode findVal(int val) {
		TreeNode current = this;
		while (current.key != val) {
			parent = current;
			if (current.key > val) {
				current = current.left;
			} else {
				current = current.right;
			}
			if (current == null) {
				return null;
			}
		}
		return current;

		//
		// TreeNode next;
		// if (this.key == val) {
		// return this;
		// }
		// if (this.left != null && this.key > val) {
		// next = this.left;
		// } else if (this.right != null && this.key < val) {
		// next = this.right;
		// } else {
		// return null;
		// }
		// while (next != null) {
		//
		// if (next.key == val) {
		// return next;
		// } else if (next.key > val) {
		// next = next.left;
		// } else if (next.key < val) {
		// next = next.right;
		// }
		// }
		// return null;

	}

	public void delete(int val) {
		TreeNode toBeDeleted = findVal(val);
		boolean isLeftChild = false;
		// not necessary
		if (toBeDeleted == null) {
			return;
		}
		if (toBeDeleted.parent.left != null && toBeDeleted.parent.left == toBeDeleted) {
			isLeftChild = true;
		}
		// Case 1: if node to be deleted has no children
		if (toBeDeleted.left == null && toBeDeleted.right == null) {
			if (isLeftChild == true) {
				toBeDeleted.parent.left = null;
				toBeDeleted = null;
			} else {
				toBeDeleted.parent.right = null;
				toBeDeleted = null;
			}
		}
		// Case 2 :
		else if (toBeDeleted.right == null) {
			if (isLeftChild) {
				toBeDeleted.parent.left = toBeDeleted.left;
				toBeDeleted.left.parent = toBeDeleted.parent;
			} else {
				toBeDeleted.parent.right = toBeDeleted.left;
				toBeDeleted.left.parent = toBeDeleted.parent;
			}
		} else if (toBeDeleted.left == null) {
			if (isLeftChild) {
				toBeDeleted.parent.left = toBeDeleted.right;
				toBeDeleted.right.parent = toBeDeleted.parent;
			} else {
				toBeDeleted.parent.right = toBeDeleted.right;
				toBeDeleted.right.parent = toBeDeleted.parent;
			}
		} else if (toBeDeleted.left != null && toBeDeleted.right != null) {

			// case 3
			TreeNode successor = getSuccessor(toBeDeleted);
			if (isLeftChild) {
				toBeDeleted.parent.left = successor;
				successor.parent = toBeDeleted.parent;
			} else {
				toBeDeleted.parent.right = successor;
				successor.parent = toBeDeleted.parent;
			}
			successor.left = toBeDeleted.left;
		}
	}

	public TreeNode getSuccessor(TreeNode deleteNode) {
		TreeNode successor = deleteNode;
		TreeNode successorParent = deleteNode;
		TreeNode current = deleteNode.right;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.left;
		}

		if (successor != deleteNode.right) {
			successorParent.left = successor.right;
			successor.right = deleteNode.right;
			successor.left = deleteNode.left;
			successor.right.parent = successor;
			successor.left.parent = successor;
		}
		return successor;
	}

	/*** Methods to implement for another 1 point */

	// Find "rank"-th smallest element in the tree, counting from 0
	public int findByRank(int rank) {
		// TODO: Implement the function
		return -1;
	}
}

/*
 * NB: For the judge to run the program, do not modify the declaration of the
 * class Main or method main() below. The class has to be declared as
 * "class Main { ... }" and the method as
 * "public static void main(String[] args) { ... }"
 */
class Main {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);
		boolean run = true;

		// We use a "special" smallest value node as a root.
		// Always having at least one node of the tree simplifies a lot of
		// special cases.
		TreeNode root = new TreeNode(-1, null);

		// A simple loop reading one command at a time
		while (run) {
			String cmd = scanner.next();

			if (cmd.equals("I")) {
				int val = scanner.nextInt();
				root.insert(val);
			}

			if (cmd.equals("D")) {
				int val = scanner.nextInt();
				root.delete(val);
			}

			if (cmd.equals("C")) {
				int val = scanner.nextInt();
				if (root.contains(val)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}

			if (cmd.equals("P")) {
				// We are not printing the special "-1" node
				if (root.right == null) {
					System.out.println("EMPTY");
				} else {
					root.right.print();
					System.out.println();
				}
			}

			if (cmd.equals("R")) {
				int rank = scanner.nextInt();
				// We look for element number "rank+1" to skip the special "-1"
				// value
				System.out.println(root.findByRank(rank + 1));
			}

			if (cmd.equals("X")) {
				run = false;
			}
		}
		scanner.close();
	}
}
