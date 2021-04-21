package DiningTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();

		for (int nr = 0; nr < tests; nr++) {

			int n = console.nextInt();
			int m = console.nextInt();
			int r = console.nextInt();
			ArrayList<ArrayList<Edge>> G = new ArrayList<ArrayList<Edge>>();
			LinkedList<Integer> queue = new LinkedList<Integer>();
			int[] side = new int[n];
			for (int i = 0; i < n; i++) {
				G.add(new ArrayList<Edge>());
				side[i] = -1;
			}

			for (int i = 0; i < m; i++) {
				int a = console.nextInt();
				int b = console.nextInt();
				Edge e1 = new Edge(a, b);
				Edge e2 = new Edge(b, a);
				G.get(a).add(e1);
				G.get(b).add(e2);
			}
			int current;
			queue.add(0);
			side[0] = 1;
			int prev = 0;
			boolean bool = true;
			int[] q = new int[n];
			q[0] = 1;
			while (!queue.isEmpty()) {
				current = queue.remove();
				for (Edge ed : G.get(current)) {
					int to = ed.to;
					if (side[current] != side[to]) {
						side[to] = (side[current] == 1) ? 2 : 1;
						if (q[to] == 0) {
							queue.add(to);
							q[to] = 1;
						}
					} else {
						bool = false;
						break;
					}
				}

			}

			if (bool) {
				int s = side[r];
				for (int i = 0; i < n; i++) {
					if (side[i] == s) {
						System.out.print(i + " ");
					}
				}
			} else {
				System.out.print("no");
			}
			System.out.println();

		}
	}
}

class Edge {
	int from;
	int to;
	boolean used = false;

	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}

}
