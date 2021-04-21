package CheckBipartit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int i = 0; i < tests; i++) {
			checkBipartit(console);
		}

	}

	public static void checkBipartit(Scanner console) {
		int n = console.nextInt();
		int m = console.nextInt();
		int r = console.nextInt();

		ArrayList<ArrayList<Edge>> G = new ArrayList<ArrayList<Edge>>();

		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int from = console.nextInt();
			int to = console.nextInt();
			Edge e1 = new Edge(from, to);
			Edge e2 = new Edge(to, from);
			G.get(from).add(e1);
			G.get(to).add(e2);
		}
		int[] color = new int[n];
		color[r] = 1;
		Queue<Edge> queue = new LinkedList<Edge>();
		boolean works = true;
		for (Edge e : G.get(r)) {
			queue.add(e);
		}
		for (int i = 0; i < n; i++) {
			while (!queue.isEmpty()) {
				Edge e = queue.remove();
				if (color[e.from] == color[e.to]) {
					works = false;
					break;
				}
				if (color[e.to] == 0) {
					color[e.to] = (color[e.from] == 1) ? 2 : 1;
					for (Edge e1 : G.get(e.to)) {
						queue.add(e1);
					}
				}

			}
			for (int j = 0; j < n; j++) {
				if (color[j] == 0) {
					color[j] = 1;
					for (Edge e1 : G.get(j)) {
						queue.add(e1);
					}
				}
			}
		}
		if (!works) {
			System.out.println("no");
		} else {
			for (int i = 0; i < n; i++) {
				if (color[i] == 1) {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		}

	}

}

class Edge {
	int from, to;

	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}
}
