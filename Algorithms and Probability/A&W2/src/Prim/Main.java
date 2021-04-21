package Prim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

class Main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int i = 0; i < tests; i++) {
			Prim(console);
		}
	}

	public static void Prim(Scanner console) {
		int n = console.nextInt();
		int m = console.nextInt();
		ArrayList<ArrayList<Edge>> G = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int u = console.nextInt();
			int v = console.nextInt();
			int w = console.nextInt();
			Edge e1 = new Edge(u, v, w);
			Edge e2 = new Edge(v, u, w);
			G.get(u).add(e1);
			G.get(v).add(e2);
		}
		TreeSet<Edge> edges = new TreeSet<Edge>();
		HashSet<Integer> used = new HashSet<Integer>();
		used.add(0);
		int counter = 1;
		for (Edge e : G.get(0)) {
			edges.add(e);
		}
		int sum = 0;
		while (counter < n) {
			Edge e = edges.pollFirst();
			if (!used.contains(e.to)) {
				sum += e.weight;
				used.add(e.to);
				for (Edge e1 : G.get(e.to)) {
					edges.add(e1);
				}
				counter++;

			}
		}
		System.out.println(sum);

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
