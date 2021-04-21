import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kruskal {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int m = console.nextInt();
		ArrayList<Edge2> E = new ArrayList<Edge2>();
		for (int i = 0; i < n; i++) {
			E.add(new Edge2(console.nextInt(), console.nextInt(), console.nextInt()));
		}
		Collections.sort(E);

		int[] V = new int[m];
		for (int i = 0; i < m; i++) {
			V[i] = i;
		}
		UnionFind unionFind = new UnionFind(V);
		int cost = 0;

		while (!E.isEmpty()) {
			Edge2 e = E.remove(0);
			if (unionFind.find(e.from) == unionFind.find(e.to)) {
				continue;
			} else {
				unionFind.union(e.from, e.to);
				cost += e.cost;
			}
		}
		System.out.println(cost);
	}

}

class UnionFind {
	int[] V;

	public UnionFind(int[] V) {
		this.V = V;
	}

	public int find(int i) {
		if (V[i] == i) {
			return i;
		} else {
			V[i] = find(V[i]);
			return V[i];
		}
	}

	public void union(int from, int to) {
		int i = find(from);
		V[i] = find(to);
	}
}

class Edge2 implements Comparable<Edge2> {
	int cost;
	int from;
	int to;

	public Edge2(int from, int cost, int to) {
		this.cost = cost;
		this.from = from;
		this.to = to;
	}

	@Override
	public int compareTo(Edge2 o) {
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
