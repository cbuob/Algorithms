package MaxFlow;

import java.util.ArrayList;
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

		numVer = n + m + 2;

		// Don't forget to initialize numVer
		// Initialize the graph
		capacity = new int[numVer][numVer];
		flow = new int[numVer][numVer];

		graph = (ArrayList<Integer>[]) new ArrayList[numVer];
		for (int i = 0; i < numVer; i++)
			graph[i] = new ArrayList<Integer>();

		int sum = 0;
		for (int i = 1; i <= n; i++) {
			int toys = sc.nextInt();
			graph[0].add(i);
			graph[i].add(0);
			capacity[0][i] = toys;
			sum += toys;

		}
		for (int i = n + 1; i <= m + n; i++) {
			int amount = sc.nextInt();
			graph[numVer - 1].add(i);
			graph[i].add(numVer - 1);
			capacity[i][numVer - 1] = amount;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = n + 1; j <= m + n; j++) {
				graph[j].add(i);
				graph[i].add(j);
				capacity[i][j] = 1;
			}
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

		if (maxFlow >= sum) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		// Output result

	}

}
