import java.util.Scanner;

public class permutaions {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int[] A = new int[n];
		int[] B = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = console.nextInt();
		}
		int first = -1;
		int second = -1;
		int[] P = new int[n];
		boolean[] used = new boolean[n + 1];
		for (int i = 0; i < n; i++) {
			B[i] = console.nextInt();
			if (B[i] != A[i]) {
				if (first == -1) {
					first = i;
				} else {
					second = i;
				}
			} else {
				P[i] = B[i];
				used[B[i]] = true;
			}
		}
		int unused1 = -1;
		int unused2 = -1;
		used[0] = true;
		for (int i = 0; i < n + 1; i++) {
			if (!used[i]) {
				if (unused1 == -1) {
					unused1 = i;
				} else {
					unused2 = i;
				}
			}
		}

		if (unused2 == -1) {
			P[first] = unused1;
		} else if (A[first] == unused1 || B[first] == unused1) {
			P[first] = unused1;
			P[second] = unused2;
		} else {
			P[first] = unused2;
			P[second] = unused1;
		}
		for (int i = 0; i < n; i++) {
			System.out.print(P[i] + " ");
		}

	}

}
