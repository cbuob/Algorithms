package KantenKontraktion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static Random rand = new Random();

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int i = 0; i < tests; i++) {
			testcase(console);
		}
	}

	public static void testcase(Scanner console) {
		int n = console.nextInt();
		int m = console.nextInt();

		ArrayList<ArrayList<Edge>> G = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int e1 = console.nextInt();
			int e2 = console.nextInt();
			int k = console.nextInt();

		}
	}

}

class Edge {
	int from;
	int times;

	public Edge(int from, int to, int times) {
		this.from = from;
		tis.to = to;
		this.times = times;
	}

}
