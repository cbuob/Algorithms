package Artikulations_Knoten;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int count;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int nr = 0; nr < tests; nr++) {
			count = 0;
			int n = console.nextInt();
			int m = console.nextInt();
			ArrayList<ArrayList<Edge>> G = new ArrayList<ArrayList<Edge>>();
			for (int i = 0; i < n; i++) {
				G.add(new ArrayList<Edge>());
			}
			for (int i = 0; i < m; i++) {
				int from = console.nextInt();
				int to = console.nextInt();
				Edge e = new Edge(from, to);
				G.get(from).add(e);
				G.get(to).add(e);
			}
			int[] low = new int[n];
			int[] dfs = new int[n];
			int[] children = new int[n];
			boolean[] root = new boolean[n];
			boolean[] visited = new boolean[n];
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					root[i] = true;
					kackShit(i, low, dfs, G, visited, children);
				}

			}

			ArrayList<Integer> cutVert = new ArrayList<Integer>();
			for (int i = 0; i < n; i++) {
				if (root[i]) {
					if (children[i] > 1) {
						cutVert.add(i);
					}
				} else {
					for (Edge e : G.get(i)) {
						if (e.to == i && dfs[e.from] + 1 != dfs[e.to])
							;
					}
				}

			}

		}
	}

	public static void kackShit(int current, int[] low, int[] dfs, ArrayList<ArrayList<Edge>> G, boolean[] visited,
			int[] children) {
		visited[current] = true;
		dfs[current] = count;
		low[current] = count;
		count++;
		for (Edge e : G.get(current)) {
			if (e.to == current && !visited[e.from]) {
				e.dfsEdge = true;
				children[e.to]++;
				kackShit(e.from, low, dfs, G, visited, children);
			} else if (e.from == current && !visited[e.to]) {
				e.dfsEdge = true;
				children[e.from]++;
				kackShit(e.to, low, dfs, G, visited, children);
			}
		}
		int lowest = dfs[current];
		for (Edge e : G.get(current)) {
			if (e.to == current && !(dfs[current] > dfs[e.from] && e.dfsEdge)) {
				lowest = (lowest < dfs[e.from]) ? lowest : dfs[e.from];
			} else if (e.from == current && !(dfs[current] > dfs[e.to] && e.dfsEdge)) {
				lowest = (lowest < dfs[e.to]) ? lowest : dfs[e.to];
			}
		}
		low[current] = lowest;

	}

}

class Edge {
	boolean dfsEdge;
	int from;
	int to;

	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
		dfsEdge = false;
	}
}
