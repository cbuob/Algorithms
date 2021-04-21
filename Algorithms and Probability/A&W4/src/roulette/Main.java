package roulette;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
		int n = console.nextInt();
		int m = console.nextInt();
		int k = console.nextInt();

		int zeros = 0;
		int notZeros = 0;
		for (int i = 0; i < n; i++) {
			if (console.nextInt() == 0) {
				zeros++;
			} else {
				notZeros++;
			}
		}
		if (notZeros == 0) {
			System.out.println(1.0);
			return;
		}
		double p = (double) zeros / (double) (notZeros + zeros);

		double[][] T = new double[m + 1][k + 1];
		int M = m + 1;
		int K = k + 1;

		for (int i = 0; i < M; i++) {
			T[i][k] = 1;
		}

		for (int i = 1; i < M; i++) {
			for (int j = 0; j < k; j++) {
				T[i][j] = T[i - 1][j + 1] * p + T[i - 1][0] * (1 - p);
			}
		}

		DecimalFormat df = new DecimalFormat("0.0######");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		System.out.println(df.format(T[m][0]));

	}
}
