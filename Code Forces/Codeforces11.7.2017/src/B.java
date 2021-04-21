import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int m = console.nextInt();
		String white = "W";
		char W = white.charAt(0);
		int[][] A = new int[n][m];
		int leftMost = -1;
		int rightMost = -1;
		int lowest = -1;
		int highest = -1;
		for (int i = 0; i < n; i++) {
			String cur = console.next();
			for (int j = 0; j < m; j++) {
				A[i][j] = (cur.charAt(j) == W) ? 0 : 1;
				if (A[i][j] == 1) {
					if (leftMost == -1) {
						leftMost = j;
						rightMost = j;
						lowest = i;
						highest = i;
					} else {
						leftMost = leftMost < j ? leftMost : j;
						rightMost = rightMost > j ? rightMost : j;
						lowest = lowest < i ? lowest : i;
						highest = highest > i ? highest : i;
					}
				}
			}
		}
		int count = 0;
		int n1 = (highest - lowest) + 1;
		int m1 = (rightMost - leftMost) + 1;
		int dim = Math.max(n1, m1);
		if (n1 < m1) {
			count = dim * (m1 - n1);
		}
		if (n1 > m1) {
			count = dim * (n1 - m1);
		}
		if (lowest == -1) {
			System.out.println(1);
			return;
		}
		if (m1 > n || n1 > m) {
			System.out.println(-1);
			return;
		}

		for (int i = lowest; i <= highest; i++) {
			for (int j = leftMost; j <= rightMost; j++) {
				if (A[i][j] == 0) {
					count++;
				}
			}
		}
		System.out.println(count);

	}

}
