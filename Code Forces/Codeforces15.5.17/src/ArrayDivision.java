import java.util.Scanner;

public class ArrayDivision {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int[] A = new int[n];
		int sumLeft = 0;
		int sumRight = 0;
		for (int i = 0; i < n; i++) {
			A[i] = console.nextInt();
			sumLeft += A[i];
		}
		boolean bool = false;
		for (int i = n - 1; i >= 0; i--) {
			sumLeft -= A[i];
			sumRight += A[i];
			double sum = (double) (sumLeft - sumRight) / 2;
			for (int j = 0; j < n; j++) {
				if (A[j] == sum || sumLeft == sumRight) {
					bool = true;
				}
			}
		}
		System.out.println((bool) ? "YES" : "NO");

	}

}
