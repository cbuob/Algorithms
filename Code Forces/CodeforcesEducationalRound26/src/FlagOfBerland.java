import java.util.Scanner;

public class FlagOfBerland {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int m = console.nextInt();

		if (n % 3 != 0 && m % 3 != 0) {
			System.out.println("NO");
			return;
		}
		int[][] A = new int[n][m];
		console.nextLine();
		for (int i = 0; i < n; i++) {
			String s = console.nextLine();
			for (int j = 0; j < m; j++) {
				A[i][j] = (int) s.charAt(j);
			}
		}
		boolean nAxis = false;
		boolean mAxis = false;
		boolean booth = false;
		if (n % 3 == 0) {
			nAxis = true;
		}
		if (m % 3 == 0) {
			mAxis = true;
		}
		if (nAxis && mAxis) {
			booth = true;
		}
		if (nAxis && !mAxis) {
			int size = n % 3;
		}

	}

}
