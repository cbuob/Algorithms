package Kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int i = 0; i < tests; i++) {
			Kruskal(console);
		}

	}

	public static void Kruskal(Scanner console) {
		int n = console.nextInt();
		int m = console.nextInt();

		ArrayList<Edge> G = new ArrayList<Edge>();
		UF uf = new UF(n);
		for (int i = 0; i < m; i++) {
			int u = console.nextInt();
			int v = console.nextInt();
			int w = console.nextInt();
			Edge e = new Edge(u, v, w);
			G.add(e);
		}
		Collections.sort(G);
		int sum = 0;
		for (int i = 0; i < m; i++) {
			Edge e = G.remove(0);
			if (uf.find(e.to) != uf.find(e.from)) {
				uf.union(e.to, e.from);
				sum += e.weight;
			}
		}
		System.out.println(sum);
	}

}

class UF {
	int[] A;

	public UF(int size) {
		this.A = new int[size];
		for (int i = 0; i < size; i++) {
			A[i] = i;
		}
	}

	public int find(int i) {
		if (A[i] == i) {
			return i;
		} else {
			A[i] = find(A[i]);
			return A[i];
		}
	}

	public void union(int i, int j) {
		A[find(i)] = find(j);
	}

}

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int weight;

	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		int diffw = this.weight - e.weight;
		if (diffw != 0) {
			return diffw;
		}
		int difff = this.from - e.from;
		int difft = this.to - e.to;
		if (difff != 0) {
			return difff;
		}
		if (difft != 0) {
			return difft;
		}
		return 0;
	}

}