package ArtikulationsKnotenSolution;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		// Create a new Scanner object for reading the input
		Scanner sc = new Scanner(System.in);

		// Read the number of testcases to follow
		int t = sc.nextInt();

		// Iterate over the testcases and solve the problem
		for (int i = 0; i < t; i++) {
			testCase(sc);
		}
	}

	public static ArrayList<Integer> disc; // disc[v] = order in which dfs
											// examines v
	public static ArrayList<Integer> low; // low[v] contains the lowest disc[]
											// value of a vertex reachable
											// from a subtree rooted at v
	public static ArrayList<Boolean> ap; // ap[v] = true if v is an articulation
											// point
	public static ArrayList<Boolean> visited; // tracks if dfs visited a
												// particular vertex
	public static int counter = -1;

	private static void dfs(ArrayList<ArrayList<Integer>> G, int v, int parent) {
		counter++;
		disc.set(v, counter);
		low.set(v, counter);
		visited.set(v, true);

		int children = 0;
		for (int idx = 0; idx < G.get(v).size(); idx++) {
			int w = G.get(v).get(idx);

			if (!visited.get(w)) {
				children++;
				dfs(G, w, v);

				// Update low[v]
				int newval = Math.min(low.get(v), low.get(w));
				low.set(v, newval);

				// Non-root is an articulation point if one his children
				// can not reach a vertex visited before v, without going
				// through v
				if (parent != -1 && low.get(w) >= disc.get(v))
					ap.set(v, true);

				// Root is an articulation point if it has at least two children
				// in DFS tree
				if (parent == -1 && children > 1)
					ap.set(v, true);

			} else {
				// If there is a back-edge to a non-parent vertex, update low[v]
				if (w != parent) {
					int newval = Math.min(low.get(v), disc.get(w));
					low.set(v, newval);
				}
			}
		}
	}

	public static void testCase(Scanner sc) {
		int n = sc.nextInt();
		int m = sc.nextInt();

		ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++)
			G.add(new ArrayList<Integer>());

		// Create a graph
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();

			G.get(u).add(v);
			G.get(v).add(u);
		}

		// Initialize
		counter = -1;
		disc = new ArrayList<Integer>(n);
		low = new ArrayList<Integer>(n);
		ap = new ArrayList<Boolean>(n);
		visited = new ArrayList<Boolean>(n);

		for (int i = 0; i < n; i++) {
			visited.add(i, false);
			ap.add(i, false);
			low.add(i, i);
			disc.add(i, -1);
		}

		// Do dfs for each connected component of the graph
		for (int i = 0; i < n; i++) {
			if (disc.get(i) == -1)
				dfs(G, i, -1);
		}

		// Output solution
		boolean found = false;
		for (int i = 0; i < n; i++) {
			if (ap.get(i) == true) {
				if (found)
					System.out.print(" ");
				System.out.print(i);
				found = true;
			}
		}
		if (found)
			System.out.println();
		else
			System.out.println("-1");
	}
}