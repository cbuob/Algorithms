package Eulerpfad;

import java.util.HashMap;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int i = 0; i < tests; i++) {
			eulerpfad(console);
		}

	}

	public static void eulerpfad(Scanner console) {
		int n = console.nextInt();
		HashMap<String, Node> M = new HashMap<String, Node>();
		int nr = -1;
		UF uf = new UF(n * 2);
		for (int i = 0; i < n; i++) {
			String s = console.next();
			String s1 = s.substring(0, 2);
			String s2 = s.substring(1, 3);
			if (M.containsKey(s1)) {
				M.get(s1).out++;
			} else {
				nr++;
				Node node = new Node(s1, nr);
				M.put(s1, node);
				M.get(s1).out++;
			}
			if (M.containsKey(s2)) {
				M.get(s2).in++;
			} else {
				nr++;
				Node node = new Node(s2, nr);
				M.put(s2, node);
				M.get(s2).in++;
			}
			uf.union(M.get(s1).nr, M.get(s2).nr);

		}

		boolean boolUF = true;
		boolean boolDeg = true;
		for (Node node : M.values()) {
			uf.find(node.nr);
		}
		int parent = uf.find(0);
		for (Node node : M.values()) {
			if (uf.find(node.nr) != parent) {
				boolUF = false;
			}
		}
		boolean hasFirst = false;
		boolean hasLast = false;
		for (Node node : M.values()) {
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

	}
}

class UF {
	int[] A;

	public UF(int size) {
		A = new int[size];
		for (int i = 0; i < size; i++) {
			A[i] = i;
		}
	}

	public int find(int i) {
		if (A[i] == i) {
			return i;
		}
		A[i] = find(A[i]);
		return A[i];
	}

	public void union(int i, int j) {
		A[find(i)] = find(j);
	}
}

class Node {
	String id;
	int nr;
	int in, out = 0;

	public Node(String id, int nr) {
		this.id = id;
		this.nr = nr;
	}
}
