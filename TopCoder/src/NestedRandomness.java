
public class NestedRandomness {

	public static void main(String[] args) {

	}

	public double probability(int start, int N, int target) {
		double[][] T = new double[start][N + 1];
		for (int i = 0; i < start; i++) {
			T[i][1] = ((double) 1) / start;
		}
		for (int n = 2; n < N; n++) {
			for (int i = start - n; i > target; i--) {
				int y = start - n + 1;
				for (int j = i + 1; j <= y; j++) {
					T[i][n] += T[j][n - 1] * ((double) 1) / j;
				}
			}
		}
		int y = start - N + 1;
		for (int i = target + 1; i <= y; i++) {
			T[target][N] += T[i][N - 1] * ((double) 1) / i;
		}

		return T[target][N];
	}

}
