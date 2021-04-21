package MaxFlowDominosSolution;

import java.util.ArrayList;
import java.util.LinkedList;

// Algoscore: C

import java.util.Scanner;

class Main {

	public static int numVer;
	public static ArrayList<Integer> graph[];
	public static int[][] capacity, flow;

	public static void main(String[] args) {
		// Create a new Scanner object for reading the input
		Scanner sc = new Scanner(System.in);

		// Read the number of test cases to follow
		int t = sc.nextInt();

		// Iterate over the test cases and solve the problem
		for (int i = 0; i < t; i++) {
			testCase(sc);
		}
	}

	public static void testCase(Scanner sc) {
		// Read the width of the board
		int n = sc.nextInt();
		// Read the height of the board
		int m = sc.nextInt();
		// Read the number of obstructions
		int k = sc.nextInt();

		// Initialise the graph (a bipartite graph with parts A and B)
		numVer = n * m + 2;
		capacity = new int[numVer][numVer];
		flow = new int[numVer][numVer];

		// Set source vertex to 0
		int source = 0;
		// Set sink vertex to numVer - 1
		int sink = numVer - 1;

		graph = (ArrayList<Integer>[]) new ArrayList[numVer];
		for (int i = 0; i < numVer; i++)
			graph[i] = new ArrayList<Integer>();

		// Occupied vertices
		boolean[] occupied = new boolean[numVer];
		for (int i = 0; i < k; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			occupied[x * m + y + 1] = true;
		}

		// Add edges to the graph
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// Current vertex
				int v = i * m + j + 1;

				if (occupied[v])
					continue;

				// If current vertex belongs to part A
				// then add the edge from source to it with capacity 1
				if (i % 2 == j % 2) {
					graph[source].add(v);
					capacity[source][v] = 1;
					capacity[v][source] = 0;
					flow[source][v] = 0;
					// Otherwise the current vertex belongs to part B
					// then add the edge from it to the sink with capacity 1
				} else {
					graph[v].add(sink);
					capacity[v][sink] = 1;
					capacity[sink][v] = 0;
					flow[v][sink] = 0;
				}
			}
		}

		// We always add a directed edge from A to B and always only
		// the edge to the right and to the top of the current position
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int u = -1, v = -1;

				// Do not above right of the top-most row
				if (i < n - 1) {
					// If we're currently in a vertex of A
					if (i % 2 == j % 2) {
						u = i * m + j + 1;
						v = (i + 1) * m + j + 1;
						// If we're currently in a vertex of B
					} else {
						v = i * m + j + 1;
						u = (i + 1) * m + j + 1;
					}

					// Add the edge from the current vertex to the vertex above
					// with capacity 0 if one of the endpoints is occupied and 1
					// otherwise
					if (!occupied[u] && !occupied[v]) {
						graph[u].add(v);
						graph[v].add(u);
						capacity[u][v] = 1;
					}
				}

				// Do not go right of the right-most column
				if (j < m - 1) {
					// If we're currently in a vertex of A
					if (i % 2 == j % 2) {
						u = i * m + j + 1;
						v = i * m + j + 2;
						// If we're currently in a vertex of B
					} else {
						v = i * m + j + 1;
						u = i * m + j + 2;
					}

					// Add the edge from the current vertex to the vertex on the
					// right
					// with capacity 0 if one of the endpoints is occupied and 1
					// otherwise
					if (!occupied[u] && !occupied[v]) {
						graph[u].add(v);
						graph[v].add(u);
						capacity[u][v] = 1;
					}
				}
			}
		}

		// Print out the size of the maximum matching in a bipartite graph with
		// parts A and B
		// i.e. the value of the maximum flow in the network
		System.out.println(computeMaximumFlow());
	}

	// Compute a path with positive residual capacity using BFS
	// Return true if such a path exists and false otherwise
	private static boolean augmentingPathExists(int previousVertexOnPath[]) {
		// Allocate space for auxiliary data structures
		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[numVer];

		// Initialization of auxiliary data structures
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
	private static int computeMaximumFlow() {
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
}