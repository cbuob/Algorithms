import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Vertex implements Comparable<Vertex> {
	public int i, j, value, pathCost;

	public Vertex(int i, int j, int value, int pathCost) {
		this.i = i;
		this.j = j;
		this.value = value;
		this.pathCost = pathCost;

	}

	/*
	 * @Override public boolean equals(Object other) { return
	 * (compareTo((Vertex) other) == 0); }
	 */

	@Override
	public int compareTo(Vertex other) {
		// return Integer.compare(this.pathCost, o.pathCost);
		if (this.pathCost != other.pathCost)
			return this.pathCost < other.pathCost ? -1 : 1;
		else if (this.i != other.i)
			return this.i < other.i ? 1 : -1;
		else if (this.j != other.j)
			return this.j < other.j ? 1 : -1;
		else
			return 0;
	}

	public Vertex getUp(Vertex x, Vertex[][] T) {
		return T[x.i - 1][x.j];
	}

	public Vertex getDown(Vertex x, Vertex[][] T) {
		return T[x.i + 1][x.j];
	}

	public Vertex getLeft(Vertex x, Vertex[][] T) {
		return T[x.i][x.j - 1];
	}

	public Vertex getRight(Vertex x, Vertex[][] T) {
		return T[x.i][x.j + 1];
	}

}

class Main {

	public static void main(String[] args) {
		/*
		 * TreeSet<Vertex> why = new TreeSet<Vertex>(); Vertex v1 = new
		 * Vertex(1, 1, 1, Integer.MAX_VALUE); Vertex v2 = new Vertex(1, 2, 1,
		 * Integer.MAX_VALUE); Vertex v3 = new Vertex(1, 3, 1,
		 * Integer.MAX_VALUE); Vertex v4 = new Vertex(2, 1, 1,
		 * Integer.MAX_VALUE); Vertex v5 = new Vertex(2, 2, 1,
		 * Integer.MAX_VALUE); Vertex v6 = new Vertex(3, 1, 1,
		 * Integer.MAX_VALUE); Vertex v7 = new Vertex(4, 1, 1,
		 * Integer.MAX_VALUE); why.add(v1); why.add(v2); why.add(v3);
		 * why.add(v4); why.add(v5); why.add(v6); why.add(v7); for (Vertex v :
		 * why) { System.err.println(why.contains(v)); }
		 */

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);

		// Read the number of cases and loop over the cases
		int case_no, cases = scanner.nextInt();
		for (case_no = 0; case_no < cases; case_no++) {

			// Read the jungle size
			int n = scanner.nextInt();

			// Create a new 2D array for the times
			Vertex[][] T = new Vertex[n][n];
			HashSet<Vertex> checkedSet = new HashSet<Vertex>();
			HashSet<Vertex> toDoset = new HashSet<Vertex>();
			TreeSet<Vertex> queue = new TreeSet<Vertex>();
			// Read the times
			Vertex start = null;
			int i, j;
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					T[i][j] = new Vertex(i, j, scanner.nextInt(), Integer.MAX_VALUE);
					if (T[i][j].value == 0) {
						start = T[i][j];
						start.pathCost = 0;
					}
					queue.add(T[i][j]);
					// System.out.println("size: " + queue.size());
					// System.out.println(queue.contains(T[i][j]));

				}
			}

			// checkedSet.add(start);
			// queue.add(start);
			Vertex current = queue.pollFirst();
			// Vertex previous = null;
			while (current.i > 0 && current.i < n - 1 && current.j > 0 && current.j < n - 1) {
				Vertex up = current.getUp(current, T);
				// System.out.println("up: " + up.i + " " + up.j + "path: " +
				// up.pathCost);
				Vertex down = current.getDown(current, T);
				// System.out.println("down: " + down.i + " " + down.j + "path:
				// " + down.pathCost);
				Vertex left = current.getLeft(current, T);
				// System.out.println("right: " + left.i + " " + left.j + "path:
				// " + left.pathCost);
				Vertex right = current.getRight(current, T);

				if (queue.contains(up)) {
					if ((current.pathCost + up.value) < (up.pathCost)) {
						queue.remove(up);
						up.pathCost = (current.pathCost + up.value);
						queue.add(up);
					}
				}
				if (queue.contains(down)) {
					// System.out.println("true");
					if ((current.pathCost + down.value) < (down.pathCost)) {
						queue.remove(down);
						down.pathCost = (current.pathCost + down.value);
						queue.add(down);
					}
				}
				if (queue.contains(left)) {
					// System.out.println("true");
					if ((current.pathCost + left.value) < (left.pathCost)) {
						queue.remove(left);
						left.pathCost = (current.pathCost + left.value);
						queue.add(left);
					}
				}
				if (queue.contains(right)) {
					// System.out.println("true");
					if ((current.pathCost + right.value) < (right.pathCost)) {
						queue.remove(right);
						right.pathCost = (current.pathCost + right.value);
						queue.add(right);
					}
				}
				current = queue.pollFirst();

			}
			System.out.println(current.pathCost);
		}
		scanner.close();
	}

}
