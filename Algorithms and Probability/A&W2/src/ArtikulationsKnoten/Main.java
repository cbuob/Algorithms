package ArtikulationsKnoten;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;



///////////////////////////////////////////////////////
/*

IIIIIII 

FUCKING


GAVE 

UP



THIS  SHIIIIIIT IS NOT FUN. I'M FUCKING DYING HERE WTF




PEOPLE ARE FUCKING DYING HERE
*/
//////////////////////////////////////////////////////////






public class Main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int test = console.nextInt();
		for (int i = 0; i < test; i++) {
			art(console);
		}

	}

	public static void art(Scanner console) {
		int n = console.nextInt();
		int m = console.nextInt();
		int[] dfs = new int[n];
		int[] low = new int[n];

		ArrayList<ArrayList<Edge>> G = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int from = console.nextInt();
			int to = console.nextInt();
			Edge e = new Edge(from, to);
			G.get(from).add(e);
			G.get(to).add(e);
		}
		Stack<Edge> stack = new Stack<Edge>();
		HashSet<Integer> done = new HashSet<Integer>();
		int count = 1;
		for (int i = 0; i < n; i++) {
			if (dfs[i] == 0) {
				count = 1;
				dfs[i] = 1;
				done.add(i);
				for (Edge e : G.get(0)) {
					stack.push(e);
				}
			}

			while (!stack.isEmpty()) {
				Edge e = stack.pop();
				if (done.contains(e.u)) {
					if (!done.contains(e.v)) {
						e.dfsUsed = true;
						count++;
						dfs[e.v] = count;
						done.add(e.v);
						for (Edge e1 : G.get(e.v)) {
							if (!e1.dfsUsed) {
								stack.push(e1);
							}
						}
					}
				} else if (done.contains(e.v)) {
					if (!done.contains(e.u)) {
						e.dfsUsed = true;
						count++;
						dfs[e.u] = count;
						done.add(e.u);
						for (Edge e1 : G.get(e.u)) {
							if (!e1.dfsUsed) {
								stack.push(e1);
							}
						}
					}
				}
			}

		}

	}

}

class Edge {
	int u, v;
	boolean dfsUsed = false;

	public Edge(int u, int v) {
		this.u = u;
		this.v = v;
	}
}
