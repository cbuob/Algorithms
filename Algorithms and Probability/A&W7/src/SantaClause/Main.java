package SantaClause;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

class Main {

	static int numVer; // the number of vertices in the graph
	static ArrayList<Integer> graph[]; // flow is computed on this graph
	static int[][] capacity, flow;

	// Compute a path with positive residual capacity using BFS
	// Return true if such a path exists and false otherwise
	public static boolean augmentingPathExists(int previousVertexOnPath[]) {
		// Allocate space for auxilary data structures
		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[numVer];

		// Initialization of auxilary data structures
		for (int i = 0; i < numVer; i++)
			visited[i] = false;
		queue.add(0);
		visited[0] = true;

		// BFS
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int w : graph[v])
				if (!visited[w] && capacity[v][w] > flow[v][w]) {
					visited[w] = true;
					previousVertexOnPath[w] = v;
					queue.add(w);
					if (w == numVer - 1)
						return true;
				}
		}

		return false;
	}

	// Computes the value of a maximum flow
	public static int computeMaximumFlow() {
		int i, flowOnPath;
		// Find paths with BFS and return path in previousVertexOnPath array
		int[] previousVertexOnPath = new int[numVer];
		// Start with empty flow
		int maxFlow = 0;
		// Use augmenting path P as long as possible
		while (augmentingPathExists(previousVertexOnPath)) {
			// Compute smallest remaining capacity on P
			flowOnPath = Integer.MAX_VALUE;
			for (i = numVer - 1; i != 0; i = previousVertexOnPath[i]) {
				int p = previousVertexOnPath[i];
				flowOnPath = Math.min(flowOnPath, capacity[p][i] - flow[p][i]);
			}
			// Add the smallest remaining capacity to each edge of P
			for (i = numVer - 1; i != 0; i = previousVertexOnPath[i]) {
				int p = previousVertexOnPath[i];
				flow[p][i] += flowOnPath;
				flow[i][p] -= flowOnPath;
			}
			maxFlow += flowOnPath;
		}

		return maxFlow;
	}

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

	public static void testCase(Scanner sc) {
		// Read the input
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		numVer = n * m + 2;

		// Don't forget to initialize numVer
		// Initialize the graph
		capacity = new int[numVer][numVer];
		flow = new int[numVer][numVer];

		graph = (ArrayList<Integer>[]) new ArrayList[numVer];
		HashSet<Integer> blocked = new HashSet<Integer>();
		for (int i = 0; i < k; i++) {
			int x = sc.nextInt() + 1;
			int y = sc.nextInt();
			blocked.add(n * y + x);
		}

		for (int i = 0; i < numVer; i++)
			graph[i] = new ArrayList<Integer>();

		boolean mode = (n % 2 == 0) ? true : false;
		int line = 1;
		for (int i = 1; i < numVer - 1; i++) {
			if (blocked.contains(i)) {

			}

			// from s
			else if ((mode) ? line % 2 == 0 ? i % 2 == 0 : (i + 1) % 2 == 0 : i % 2 == 0) {
				connect(0, i);
				if (!blocked.contains(i - 1) && !isLeftEdge(n, m, i)) {
					connect(i, i - 1);
				}
				if (!blocked.contains(i + 1) && !isRightEdge(n, m, i)) {
					connect(i, i + 1);
				}
				if (!blocked.contains(i - n) && !isBottomEdge(n, m, i)) {
					connect(i, i - n);
				}
				if (!blocked.contains(i + n) && !isTopEdge(n, m, i)) {
					connect(i, i + n);
				}
			} else { // to t
				connect(i, numVer - 1);
			}
			line = i % n == 0 ? line + 1 : line;
		}

		// Here add the edges to the graph and initialize capacity and flow!
		// Example:
		// graph[0].add(1); <-- adding an edge from vertex 0 to vertex 1
		// graph[1].add(0); <-- add the reverse edge!
		// flow[0][1] = 0;
		// capacity[0][1] = 1; <- replace 1 with your desired capacity
		// capacity[1][0] = 0; <- the reverse edge should have capacity 0

		// IMPORTANT: Vertex 0 is always considered as the source
		// and vertex numVer - 1 as the sink

		// Compute maximum flow
		int maxFlow;
		maxFlow = computeMaximumFlow();

		// Output result
		System.out.println(maxFlow);
	}

	public static void connect(int from, int to) {
		graph[from].add(to);
		graph[to].add(from);
		capacity[from][to] = 1;
		capacity[to][from] = 0;
	}

	public static boolean isLeftEdge(int n, int m, int i) {
		if (i % n == 1 || n == 1)
			return true;
		return false;
	}

	public static boolean isRightEdge(int n, int m, int i) {
		if (i % n == 0)
			return true;
		return false;
	}

	public static boolean isBottomEdge(int n, int m, int i) {
		if (i <= n)
			return true;
		return false;
	}

	public static boolean isTopEdge(int n, int m, int i) {
		if (i > n * (m - 1) || m == 1)
			return true;
		return false;
	}

}