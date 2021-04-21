package Artikulations_Knoten2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

class Main {
	static int count;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int nr = 0; nr < tests; nr++) {
			count = 0;
			int n = console.nextInt();
			int m = console.nextInt();
			ArrayList<ArrayList<Node>> G = new ArrayList<ArrayList<Node>>();
			ArrayList<Node> nodes = new ArrayList<Node>();
			for (int i = 0; i < n; i++) {
				G.add(new ArrayList<Node>());
				nodes.add(new Node(i));
			}
			for (int i = 0; i < m; i++) {
				int from = console.nextInt();
				int to = console.nextInt();
				G.get(from).add(nodes.get(to));
				G.get(to).add(nodes.get(from));
			}
			ArrayList<Integer> cutVertexes = new ArrayList<Integer>();
			DFS(cutVertexes, nodes.get(0), G);

			Collections.sort(cutVertexes);
			if (cutVertexes.contains(0)) {
				if (!(nodes.get(0).parentOf.size() > 1)) {
					cutVertexes.remove(0);
				}
			}
			if (cutVertexes.isEmpty()) {
				System.out.print(-1);
			} else {
				for (Integer i : cutVertexes) {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		}

	}

	public static void DFS(ArrayList<Integer> cutVertexes, Node v, ArrayList<ArrayList<Node>> G) {
		v.discovered = true;
		v.dfs = count;
		v.low = count;
		count++;
		for (Node x : G.get(v.id)) {
			if (!x.discovered) {
				v.parentOf.add(x.id);
				DFS(cutVertexes, x, G);
				v.low = Math.min(v.low, x.low);
				if (x.low >= v.dfs) {
					if (!cutVertexes.contains(v.id)) {
						cutVertexes.add(v.id);
					}
				}
			} else if (!x.parentOf.contains(v.id)) {
				v.low = Math.min(v.low, x.dfs);
			}
		}
	}
}

class Node {
	HashSet<Integer> parentOf;
	int id;
	boolean discovered = false;
	int dfs = -1;
	int low;

	public Node(int id) {
		this.id = id;
		parentOf = new HashSet<Integer>();
	}
}
