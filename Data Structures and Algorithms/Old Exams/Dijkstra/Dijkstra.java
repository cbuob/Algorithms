import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Dijkstra {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int start = console.nextInt();
		int end = console.nextInt();
		int n = console.nextInt();
		int m = console.nextInt();
		HashSet<Integer> done = new HashSet<Integer>();
		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < m; i++) {
			adj.add(new ArrayList<Edge>());
		}

		TreeSet<Edge> E = new TreeSet<Edge>();
		for (int i = 0; i < n; i++) {
			int from = console.nextInt();
			int to = console.nextInt();
			int cost = console.nextInt();
			Edge e = new Edge(from, to, cost);
			adj.get(from).add(e);
			adj.get(to).add(e);
		}
		int cost = 0;
		done.add(start);

		E.addAll(adj.get(start));

		while (!E.isEmpty()) {
			Edge e = E.pollFirst();
			if (done.contains(e.from) && done.contains(e.to)) {
				continue;
			} else if (done.contains(e.from)) {
				cost += e.cost;
				done.add(e.to);
				E.addAll(adj.get(e.to));
			} else if (done.contains(e.to)) {
				cost += e.cost;
				done.add(e.from);
				E.addAll(adj.get(e.from));
			}
			if (done.contains(end)) {
				break;
			}

		}
		System.out.println(cost);
	}

}

class Edge implements Comparable<Edge> {
	int cost;
	int from;
	int to;

	public Edge(int from, int to, int cost) {
		this.cost = cost;
		this.from = from;
		this.to = to;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.cost != o.cost) {
			return this.cost - o.cost;
		} else if (this.from != o.from) {
			return this.from - o.from;
		} else if (this.to != o.to) {
			return this.to - o.to;
		}
		return 0;
	}
}
