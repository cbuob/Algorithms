package islandTribe;

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
		int B = console.nextInt(); // 0
		int H = console.nextInt(); // 1
		int N = console.nextInt(); // 2
		double[][][] tableB = new double[B + 1][N + 1][H + 1];
		double[][][] tableN = new double[B + 1][N + 1][H + 1];
		double[][][] tableH = new double[B + 1][N + 1][H + 1];
		// init B
		for (int i = 1; i < B + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				tableB[i][j][0] = 1;
			}
		}
		// init N
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < H + 1; j++) {
				tableN[0][i][j] = 1;
			}
		}
		// init H
		for (int i = 1; i < H + 1; i++) {
			for (int j = 0; j < B + 1; j++) {
				tableH[j][0][i] = 1;
			}
		}

		for (int b = 1; b < B + 1; b++) {
			for (int n = 1; n < N + 1; n++) {
				for (int h = 1; h < H + 1; h++) {
					double total = total(b, n, h);
					double probBN = b * n / total;
					double probNH = n * h / total;
					double probHB = h * b / total;
					tableB[b][n][h] = probBN * tableB[b][n - 1][h] + probNH * tableB[b][n][h - 1]
							+ probHB * tableB[b - 1][n][h];

					tableN[b][n][h] = probBN * tableN[b][n - 1][h] + probNH * tableN[b][n][h - 1]
							+ probHB * tableN[b - 1][n][h];

					tableH[b][n][h] = probBN * tableH[b][n - 1][h] + probNH * tableH[b][n][h - 1]
							+ probHB * tableH[b - 1][n][h];
				}
			}
		}
		DecimalFormat df = new DecimalFormat("0.0######");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		System.out.print(df.format(tableB[B][N][H]) + " ");
		System.out.print(df.format(tableH[B][N][H]) + " ");
		System.out.println(df.format(tableN[B][N][H]) + " ");

	}

	public static double total(int x, int y, int z) {
		return (x * y + y * z + x * z);

	}
}