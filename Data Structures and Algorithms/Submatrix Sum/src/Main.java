import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);

		// Read the number of cases and loop over the cases
		int case_no, cases = scanner.nextInt();
		for (case_no = 0; case_no < cases; case_no++) {

			// Read the matrix size
			int n = scanner.nextInt();

			// Create a new 2D array
			int[][] A = new int[n][n];

			// Read the matrix
			int i, j;
			boolean greaterThan0 = false;
			;
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					A[i][j] = scanner.nextInt();
					if (A[i][j] > 0) {
						greaterThan0 = true;
					}
				}
			}
			// TODO: Implement your solution

			// TODO: Replace with the right answer - the maximum obtainable sum

			System.out.println(maxSubMatrix(A));
		}
		scanner.close();
	}

	public static int maxSubArray(int[] B) {
		int max = 0;
		int randMax = 0;
		for (int j = 0; j < B.length; j++) {
			randMax = randMax + B[j];
			if (randMax > max) {
				max = randMax;
			}
			if (randMax < 0) {
				randMax = 0;
			}
		}
		return max;
	}

	public static int maxSubMatrix(int[][] A) {
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			int[] B = new int[A.length];
			for (int j = i; j >= 0; j--) {

				if (j == i) {
					for (int k = 0; k < A.length; k++) {
						B[k] = A[i][k];
					}
				} else {
					for (int k = 0; k < A.length; k++) {
						B[k] += A[j][k];
					}
				}
				int temp = maxSubArray(B);
				max = (temp > max) ? temp : max;
			}
		}
		return max;
	}
}
