package MaxFlowDominos;

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
		int k = sc.nextInt();

		numVer = n *m +2;

		// Don't forget to initialize numVer
		// Initialize the graph
		capacity = new int[numVer][numVer];
		flow = new int[numVer][numVer];

		graph = (ArrayList<Integer>[]) new ArrayList[numVer];
		for (int i = 0; i < numVer; i++)
			graph[i] = new ArrayList<Integer>();

		boolean[][] blacked = new boolean[n][m];
		for(int i = 0;i<k;i++){
			int x = sc.nextInt();
			int y = sc.nextInt();
			blacked[x][y] = true;
		}
		boolean bool = true;
		for(int x = 0;x<n;x++){
			for (int y = 0; y<m;y=2){
				if(blacked[x][y]){
					break;
				}
				if(bool){
					graph.
				}
				
				if(!leftEdge(x,y,n,m)&&!blacked[x][y-1]){
					graph[1+x*m+y].add(x*m+y);
					graph[x*m+y].add(1+x*m+y);
					if(bool){
						capacity[1+x*m+y][x*m+y]=1;
					}
				}
				if(!rightEdge(x,y,n,m)&&!blacked[x][y+1]){
					graph[1+x*m+y].add(2+x*m+y);
					graph[2+x*m+y].add(1+x*m+y);
					if(bool){
						capacity[1+x*m+y][x*m+y]=1;
					}
				}
				if(!leftEdge(x,y,n,m)&&!blacked[x-1][y]){
					graph[1+x*m+y].add(x*m+y);
					graph[x*m+y].add(1+x*m+y);
					if(bool){
						capacity[1+x*m+y][x*m+y]=1;
					}
				}
				if(!leftEdge(x,y,n,m)&&!blacked[x-1][y]){
					graph[1+x*m+y].add(x*m+y);
					graph[x*m+y].add(1+x*m+y);
					if(bool){
						capacity[1+x*m+y][x*m+y]=1;
					}
				}
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

		System.out.println(maxFlow);
		// Output result

	}

	static boolean leftEdge(int x, int y, int n, int m) {
		if (y == 0) {
			return true;
		} else {
			return false;
		}
	}

	static boolean rightEdge(int x, int y, int n, int m) {
		if (y == m - 1) {
			return true;
		} else {
			return false;
		}
	}

	static boolean bottomEdge(int x, int y, int n, int m) {
		if (x == 0) {
			return true;
		} else {
			return false;
		}
	}

	static boolean topEdge(int x, int y, int n, int m) {
		if (x == n - 1) {
			return true;
		} else {
			return false;
		}
	}

}
