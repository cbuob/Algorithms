import java.util.Scanner;

class TreeNode {

	public TreeNode left, right, parent, root;
	public int key, height;

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
		// TODO: Implement the function

		root = this;
		height++;

		TreeNode current = root;
		TreeNode elter = null;
		while (current != null) {
			if (val < current.key) {
				elter = current;
				current = current.left;
			} else {
				elter = current;
				current = current.right;
			}
		}
		if (val < elter.key) {
			TreeNode child = new TreeNode(val, elter);
			elter.left = child;
		} else {
			TreeNode child = new TreeNode(val, elter);
			elter.right = child;
		}
	}

	// Return if "key" is in the tree, recursively
	public boolean contains(int val) {
		// TODO: Implement the function
		TreeNode current = root;
		while (current != null) {
			if (val < current.key) {
				current = current.left;
			} else {
				current = current.right;
			}
			if (current != null) {
				if (current.key == val) {
					return true;
				}
			}
		}
		return false;
	}

	// Delete "val" from the tree
	// You may assume that the tree contains "val"
	public void delete(int val) {
		// TODO: Implement the function

		TreeNode current = this;

		// find node
		while (current.key != val) {
			if (val < current.key) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		// in case node to be deleted doesn't have any children
		if (current.left == null && current.right == null) {
			height--;
			if (current.parent.left != null) {
				if (current.parent.left.key == current.key) {
					current.parent.left = null;
				}
			} else if (current.parent.right != null) {
				if (current.parent.right.key == current.key) {
					current.parent.right = null;
				}
			}
		}

		// in case node to be deleted has one kid
		else if (current.left == null || current.right == null) {
			height--;
			// existing child on right
			if (current.left == null) {
				// grand-parent on right
				if (current.parent.left != null) {
					if (current.parent.left.key == current.key) {
						current.parent.left = current.right;
						current.right.parent = current.parent;
					}
				}
				// grand-parent on left
				else if (current.parent.right != null) {
					if (current.parent.right.key == current.key) {
						current.parent.right = current.right;
						current.right.parent = current.parent;
					}
				}
			}
			// existing child on left
			else if (current.right == null) {
				// grand-parent on right
				if (current.parent.left != null) {
					if (current.parent.left.key == current.key) {
						current.parent.left = current.left;
						current.left.parent = current.parent;
					}
				}
				// grand-parent on left
				else if (current.parent.right != null) {
					if (current.parent.right.key == current.key) {
						current.parent.right = current.left;
						current.left.parent = current.parent;
					}
				}
			}
		}
		// in case current has two children
		else {
			TreeNode y = findY(current);
			int value = y.key;
			y.delete(value);
			current.key = value;
		}
	}

	/*** Methods to implement for another 1 point */

	// Find "rank"-th smallest element in the tree, counting from 0
	public int findByRank(int rank) {
		// TODO: Implement the function

		return -1;
	}

	public TreeNode findY(TreeNode x) {
		TreeNode current = x;
		TreeNode start = current.right;

		if (current.right.left == null) {
			return current.right;
		} else {
			while (start != null) {
				current = start;
				start = start.left;
			}
		}
		return current;
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