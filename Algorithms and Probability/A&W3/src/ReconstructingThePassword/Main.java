package ReconstructingThePassword;

import java.util.HashMap;
import java.util.Scanner;

class Main {
	static int count;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();

		for (int nr = 0; nr < tests; nr++) {
			HashMap<String, Node> map = new HashMap<String, Node>();

			int n = console.nextInt();
			UF uf = new UF(2 * n);

			for (int i = 0; i < n; i++) {
				String s = console.next();
				String first = s.substring(0, 2);
				String second = s.substring(1, 3);

				if (!map.containsKey(first)) {
					Node Nfirst = new Node(first, 0, 1, 1);
					map.put(first, Nfirst);
				} else {
					Node node = map.get(first);
					node.out++;
					node.deg++;
				}
				if (!map.containsKey(second)) {
					Node Nsecond = new Node(second, 1, 0, 1);
					map.put(second, Nsecond);
				} else {
					Node node = map.get(second);
					node.in++;
					node.deg++;
				}
				Node n1 = map.get(first);
				Node n2 = map.get(second);
				uf.find(n1.id);
				uf.find(n2.id);
				uf.union(n1.id, n2.id);

			}
			boolean boolUF = true;
			boolean boolDeg = true;
			for (Node node : map.values()) {
				uf.find(node.id);
			}
			int parent = uf.find(0);
			for (Node node : map.values()) {
				if (uf.find(node.id) != parent) {
					boolUF = false;
				}
			}
			boolean hasFirst = false;
			boolean hasLast = false;
			for (Node node : map.values()) {
				if (node.in != node.out) {
					if (node.in - 1 == node.out) {
						if (hasLast) {
							boolDeg = false;
						} else {
							hasLast = true;
						}
					} else if (node.in == node.out - 1) {
						if (hasFirst) {
							boolDeg = false;
						} else {
							hasFirst = true;
						}
					} else {
						boolDeg = false;
					}

				}

			}
			if (boolDeg && boolUF) {
				System.out.println("yes");
			} else if (!boolDeg && boolUF) {

				System.out.println("no");
			} else if (boolDeg && !boolUF) {
				System.out.println("no");
			} else {
				System.out.println("no");
			}
			Node.count = 0;

		}

	}

}

class Node implements Comparable<Node> {
	public static int count = 0;
	String str;
	int in = 0;
	int out = 0;
	int deg = 0;
	int id;

	public Node(String str, int in, int out, int deg) {
		this.str = str;
		this.in = in;
		this.out = out;
		this.deg = deg;
		this.id = count;
		count++;
	}

	@Override
	public int compareTo(Node n) {
		return this.str.compareTo(n.str);
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
		// int findI = find(i);
		// A[findI] = find(j);
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