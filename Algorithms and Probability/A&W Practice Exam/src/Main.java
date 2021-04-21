import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int x = console.nextInt();
		for (int i = 0; i < x; i++) {
			testcase(console);
		}

	}

	public static void testcase(Scanner console) {
		int n = console.nextInt();
		int k = console.nextInt();

		double[][] T = new double[n][k];
		int[][] A = new int[n][k];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				A[i][j] = console.nextInt();
			}
		}

		for (int i = 0; i < k; i++) {
			T[0][i] = 1.0 / k;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < k; j++) {
				double sum = 0;
				for (int x = 0; x < k; x++) {
					if (A[i - 1][x] <= A[i][j]) {
						sum += T[i - 1][x];
					}
				}
				T[i][j] = ((double) 1.0 / (double) k) * sum;
			}
		}
		double sol = 0;
		for (int i = 0; i < k; i++) {
			sol += T[n - 1][i];
		}
		DecimalFormat df = new DecimalFormat("0.0####");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		System.out.println(df.format(sol));
	}

}
