package MST;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			testCase(sc);
		}

	}

	public static void testCase(Scanner sc) {
		int n = sc.nextInt();
		int m = sc.nextInt();

		ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
		// Initialise the graph
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Edge>());
		}

		// Read the graph
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int weight = sc.nextInt();

			// Store the weighted edges into the graph
			graph.get(u).add(new Edge(u, v, weight));
			graph.get(v).add(new Edge(v, u, weight));
		}

		PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();

		int weight = 0;
		int visited[] = new int[n];

		for (int i = 0; i < n; i++) {
			visited[i] = 0;
		}

		// At the beginning only the root is in the MST
		visited[0] = 1;

		// Add all the incident edges to the min-heap
		for (Edge e : graph.get(0)) {
			minHeap.add(e);
		}

		// Prim MST algorithm
		while (minHeap.size() != 0) {
			Edge current = minHeap.poll();

			if (visited[current.v] != 1) {
				// Add the weight of the current edge to the MST weight
				weight += current.weight;
				// Add the new vertex to the MST
				visited[current.v] = 1;

				// Add all the incident edges of the new vertex to the min-heap
				for (Edge e : graph.get(current.v)) {
					if (visited[e.v] != 1) {
						minHeap.add(e);
					}
				}
			}
		}

		System.out.println(weight);
	}
}

// Edge class
class Edge implements Comparable<Edge> {
	public int u;
	public int v;
	public int weight;

	public Edge(int u, int v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	// Needed for the min-heap to function properly
	@Override
	public int compareTo(Edge that) {
		return Integer.valueOf(this.weight).compareTo(Integer.valueOf(that.weight));
	}

}