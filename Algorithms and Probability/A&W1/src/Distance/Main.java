package Distance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int nr = 0; nr < tests; nr++) {
			int n = console.nextInt();
			int m = console.nextInt();
			int start = console.nextInt();
			ArrayList<ArrayList<Edge>> G = new ArrayList<ArrayList<Edge>>();
			HashSet<Integer> visited = new HashSet<Integer>();
			Queue<Edge> queue = new LinkedList<Edge>();
			int[] dist = new int[n];

			// wai tsun isch de dnnst
			for (int i = 0; i < n; i++) {
				G.add(new ArrayList<Edge>());
				dist[i] = -1;
			}
			dist[start] = 0;

			for (int i = 0; i < m; i++) {
				int a = console.nextInt();
				int b = console.nextInt();
				Edge e1 = new Edge(a, b);
				Edge e2 = new Edge(b, a);
				G.get(a).add(e1);
				G.get(b).add(e2);
			}
			for (Edge e : G.get(start)) {
				queue.add(e);
			}
			visited.add(start);
			while (!queue.isEmpty()) {
				Edge e = queue.remove();
				if (visited.contains(e.to)) {
					continue;
				}
				dist[e.to] = dist[e.from] + 1;
				visited.add(e.to);
				for (Edge ed : G.get(e.to)) {
					queue.add(ed);
				}
			}
			for (int i = 0; i < n; i++) {
				System.out.print(dist[i] + " ");
			}
			System.out.println();

		}
	}

}

class Edge {
	int from;
	int to;

	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}

}
