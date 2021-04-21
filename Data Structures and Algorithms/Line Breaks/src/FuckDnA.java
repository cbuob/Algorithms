import java.util.Scanner;

public class FuckDnA {

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
				// strings in längen verwandeln
				words[i] = scanner.nextLine().length();
			}

			int tableWidth = maxWidth / 2 + 1;
			int[][] T = new int[n][tableWidth];
			int onoOneLine = 0;
			for (i = 0; i < n; i++) {
				for (int j = 0; j < tableWidth; j++) {
					onoOneLine = words[i];
					if (i + j >= n) {
						T[i][j] = -1;
						continue;
					}
					for (int x = 1; x <= j; x++) {
						onoOneLine += words[i + x] + 1;
					}
					if (onoOneLine > maxWidth) {
						T[i][j] = -1;
						continue;
					}
					T[i][j] = (int) Math.pow(maxWidth - onoOneLine, 2);
					if (i + j == n - 1) {
						T[i][j] = 0;
					}
				}
			}

			int[] array = new int[n + 1];
			int smallest = 0;
			int sum = 0;
			int j = 0;
			for (i = n - 1; i >= 0; i--) {
				smallest = T[i][0] + array[i + 1];
				for (j = 1; j < tableWidth; j++) {
					if (T[i][j] == -1) {
						break;
					}
					if (i + j == n - 1) {
						sum = T[i][j];
					} else if (i + j >= n) {
						break;
					} else {
						sum = T[i][j] + array[i + j + 1];
					}
					smallest = Math.min(smallest, sum);
				}
				array[i] = smallest;
				j = 0;
			}

			// Output the formatted text
			// TODO: replace with the right answer
			System.out.println(array[0]);
		}

		scanner.close();
	}
}
