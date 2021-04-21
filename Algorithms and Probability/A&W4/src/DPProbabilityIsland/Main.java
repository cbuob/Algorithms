package DPProbabilityIsland;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int test = console.nextInt();
		for (int i = 0; i < test; i++) {
			calculateProbability(console);
		}

	}

	public static void calculateProbability(Scanner console) {
		int bears = console.nextInt();
		int hunters = console.nextInt();
		int ninjas = console.nextInt();

		double[][][] B = new double[bears + 1][hunters + 1][ninjas + 1];
		for (int i = 1; i < bears + 1; i++) {
			for (int j = 0; j < ninjas + 1; j++) {
				B[i][0][j] = 1;
			}
		}

		for (int b = 1; b < bears + 1; b++) {
			for (int h = 1; h < hunters + 1; h++) {
				for (int n = 1; n < ninjas + 1; n++) {
					double total = sum(b, h, n);
					double probBH = (b * h) / total;
					double probHN = (h * n) / total;
					double probNB = (n * b) / total;
					B[b][h][n] = probBH * B[b - 1][h][n] + probHN * B[b][h - 1][n] + probNB * B[b][h][n - 1];
				}
			}
		}
		DecimalFormat df = new DecimalFormat("0.0######");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		System.out.println(df.format(B[bears][hunters][ninjas]));
	}

	public static double sum(int x, int y, int z) {
		return x * y + y * z + x * z;
	}

}
