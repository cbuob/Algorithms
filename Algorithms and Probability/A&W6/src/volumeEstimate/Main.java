package volumeEstimate;

import java.util.Random;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();

		for (int nr = 0; nr < tests; nr++) {
			test(console);
		}
	}

	public static void test(Scanner console) {
		int dimensions = console.nextInt();
		int n = console.nextInt();

		int[][] A = new int[n][dimensions + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < (dimensions + 1); j++) {
				A[i][j] = console.nextInt();
			}
		}
		double v = console.nextDouble();

		double S = 0;
		double U = 0;
		int times = dimensions * 52000;
		U = times;
		Random r = new Random(2);
		for (int i = 0; i < times; i++) {
			S += check(A, dimensions, n, r);
		}
		double B = S / U;

		double X = Math.abs(B - v);
		if (X <= 0.01 * v) {
			System.out.println("yes");
			// System.out.println(B + " " + v);
		} else {
			System.out.println("no");
			// System.out.println(B + " " + v);
		}

	}

	public static int check(int[][] A, int dimensions, int n, Random r) {

		double[] xi = new double[dimensions];
		for (int i = 0; i < dimensions; i++) {
			xi[i] = r.nextDouble();
		}
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum = 0;
			for (int j = 0; j < dimensions; j++) {
				sum += xi[j] * A[i][j];
			}
			if (sum >= A[i][dimensions]) {
				return 0;
			}
		}

		return 1;
	}
}
