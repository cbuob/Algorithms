import java.util.Scanner;

public class TEst {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);

		// Read the number of paragraphs and page width
		int paragraphs = scanner.nextInt();

		for (int paragraph_no = 1; paragraph_no <= paragraphs; paragraph_no++) {

			// Each case has its own local variables ...
			int i;

			// Read n, D and then the array d[i] and e[i]
			int n = scanner.nextInt();
			int maxWidth = scanner.nextInt();
			scanner.nextLine(); // Eat the newline after the last number

			int[] words = new int[n];
			for (i = 0; i < n; i++) {
				words[i] = scanner.nextLine().length();
			}

			// TODO: Put your implementation here
			int[][] T = new int[n][n];
			int sum = 0;
			int x = 0;
			for (i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (x = 0; x <= j; x++) {
						sum = (i + x < n) ? sum + words[i + x] : -1;
					}
					sum += x - 1;
					if (i + j >= n) {
						sum = -1;
					}

					T[i][j] = (int) ((sum > maxWidth || sum == -1) ? -1 : Math.pow(maxWidth - sum, 2));
					if (i + j == n - 1 && T[i][j] != -1) {
						T[i][j] = 0;
					}
					x = 0;
					sum = 0;
				}
			}

			int[] array = new int[n];
			sum = 0;
			int smallest = -1;
			for (i = n - 2; i >= 0; i--) {
				smallest = -1;
				for (int j = 0; j < n; j++) {
					if (i + j >= n || T[i][j] == -1) {
						break;
					}
					if (i + j == n - 1) {
						sum = T[i][j];
					} else {
						sum = array[i + j + 1] + T[i][j];
					}
					if (smallest == -1) {
						smallest = sum;
					}
					smallest = Math.min(sum, smallest);
				}
				array[i] = smallest;
			}

			// Output the formatted text
			// TODO: replace with the right answer
			System.out.println(array[0]);
		}

		scanner.close();
	}
}
