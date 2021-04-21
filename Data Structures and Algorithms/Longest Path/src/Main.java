import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);

		// Read the number of paragraphs and page width
		int case_no, cases = scanner.nextInt();

		for (case_no = 1; case_no <= cases; case_no++) {

			// Read the number of vertices and edges
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			int i;

			// There are several representations of the graph, choose what seems
			// useful:

			// For every edge, the source and target vertex
			int[] edge_from = new int[m];
			int[] edge_to = new int[m];

			// For every vertex V, List of vertices X with direct edge from V to
			// X
			List<List<Integer>> v_out = new ArrayList<List<Integer>>();

			// For every vertex V, List of vertices X with direct edge to V from
			// X
			List<List<Integer>> v_in = new ArrayList<List<Integer>>();

			// Create the empty lists
			for (i = 0; i < n; i++) {
				v_out.add(new ArrayList<Integer>());
				v_in.add(new ArrayList<Integer>());
			}

			// Read elements of A and elements of B
			for (i = 0; i < m; i++) {
				// Read the edge
				int s = scanner.nextInt();
				int t = scanner.nextInt();
				// Insert into all three graph representations
				edge_from[i] = s;
				edge_to[i] = t;
				v_out.get(s).add(t);
				v_in.get(t).add(s);
			}

			// First, compute the topological order

			// The List to hold the topological order, empty
			ArrayList<Integer> order = new ArrayList<Integer>();

			// TODO: Put your implementation here
			int[] inCount = new int[n];
			Stack<Integer> zeroIn = new Stack<Integer>();
			for (i = 0; i < n; i++) {
				inCount[i] = v_in.get(i).size();
				if (inCount[i] == 0) {
					zeroIn.push(i);
				}
			}
			while (zeroIn.size() != 0) {
				int v = zeroIn.pop();
				order.add(v);
				Iterator<Integer> iterator = v_out.get(v).iterator();
				while (iterator.hasNext()) {
					int x = iterator.next();
					inCount[x]--;
					if (inCount[x] == 0) {
						zeroIn.push(x);
					}

				}
			}
			System.out.println(order.toString());
			int[] longestPath = new int[n];
			for (i = 0; i < n; i++) {
				int v = order.get(i);
				Iterator<Integer> iterator = v_in.get(v).iterator();
				int longest = 1;
				while (iterator.hasNext()) {
					int x = iterator.next();
					if (longestPath[x] >= longest) {
						longest = longestPath[x] + 1;
					}
				}
				longestPath[v] = longest;

			}
			int best = 1;
			for (i = 0; i < n; i++) {
				if (longestPath[i] > best) {
					best = longestPath[i];
				}
			}
			// TODO: Output the length of the longest path
			System.out.println(best);
		}
		scanner.close();
	}
}
