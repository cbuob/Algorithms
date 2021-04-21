import java.util.Scanner;

public class DPMünzen {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int w = console.nextInt();
		int[] M = new int[n];
		int[][] DP = new int[n][w + 1];
		for (int i = 0; i < n; i++) {
			M[i] = console.nextInt();
		}

		for (int i = 0; i < n; i++) {
			DP[i][0] = 1;
		}
		for (int i = 1; i < w + 1; i++) {
			if (i % M[0] == 0) {
				DP[0][i] = 1;
			}
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < w + 1; j++) {
				DP[i][j] = DP[i - 1][j];
				if (j == M[i]) {
					DP[i][j]++;
				}
				if (j > M[i]) {
					DP[i][j] += DP[i][j - M[i]];
				}
			}
		}
		System.out.println(DP[n - 1][w]);
	}

}
