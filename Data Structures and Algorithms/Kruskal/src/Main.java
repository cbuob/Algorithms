import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		int test, ntest, n, m, u, v, w;
		Scanner sc = new Scanner(System.in);
		ntest = sc.nextInt();
		for (test = 1; test <= ntest; ++test) {
			// Read the number of vertices.
			n = sc.nextInt();
			// Read the number of edges.
			m = sc.nextInt();
			// Read the edges.
			ArrayList<Edge> edgeList = new ArrayList<Edge>();
			for (int i = 0; i < m; ++i) {
				u = sc.nextInt();
				v = sc.nextInt();
				w = sc.nextInt();
				// We store nodes as numbers from 0 to n-1
				edgeList.add(new Edge(u, v, w));
			}

			// Sort the edge list by cost.
			Collections.sort(edgeList);

			// creates a new UF data structure with at most n elements.
			UF UF = new UF(n);

			// variable that holds the cost of the MST.
			int costMST = 0;

			// implement Kruskal's algorithm here...
			while (!edgeList.isEmpty()) {
				Edge edge = edgeList.remove(0);
				if (UF.find(edge.u) != UF.find(edge.v)) {
					UF.union(edge.u, edge.v);
					costMST += edge.w;
				}
			}

			System.out.println(costMST);

			//
		}
	}
}

class UF {

	public int find(int i) {
		if (A[i] == i) {
			return i;
		}
		A[i] = find(A[i]);
		return A[i];
	}

	public void union(int i, int j) {
		A[A[i]] = find(j);
	}

	// constructor. imlement the Makeset function in here.
	// much bullshit
	// n is the maximum size of the UnionFind data structure.
	public int[] A;

	public UF(int n) {
		this.A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = i;
		}
	}
}

class Edge implements Comparable<Edge> {
	public int u, v, w;
	public Edge parent;

	Edge(int u, int v, int c) {
		this.u = u;
		this.v = v;
		this.w = c;
	}

	public int compareTo(Edge e) {
		return this.w - e.w;
	}
}
