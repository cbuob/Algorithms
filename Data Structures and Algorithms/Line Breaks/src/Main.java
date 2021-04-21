import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main { /*
				 * 
				 * public static void main(String[] args) {
				 * 
				 * // Read from the standard input with java.util.Scanner
				 * Scanner scanner = new Scanner(System.in);
				 * 
				 * // Read the number of paragraphs and page width int
				 * paragraphs = scanner.nextInt();
				 * 
				 * for (int paragraph_no = 1; paragraph_no <= paragraphs;
				 * paragraph_no++) {
				 * 
				 * // Each case has its own local variables ... int i;
				 * 
				 * // Read n, D and then the array d[i] and e[i] int n =
				 * scanner.nextInt(); int maxWidth = scanner.nextInt();
				 * scanner.nextLine(); // Eat the newline after the last number
				 * 
				 * int[] words = new int[n]; for (i = 0; i < n; i++) { words[i]
				 * = scanner.nextLine().length(); }
				 * 
				 * // TODO: Put your implementation here int bug = n; int[][] T
				 * = new int[n][maxWidth];// n int sum = 0; for (i = 0; i < n;
				 * i++) { sum = 0; for (int j = 0; j < maxWidth; j++) { // n if
				 * (i + j < n && sum != -1) { sum += ((j == 0) ? 0 : 1) +
				 * words[i + j]; if (sum > maxWidth) { sum = -1; } } else { sum
				 * = -1; } T[i][j] = (sum == -1) ? -1 : ((int) Math.pow(maxWidth
				 * - sum, 2));
				 * 
				 * } }
				 * 
				 * int[] array = new int[n]; array[n - 1] = T[n - 1][0]; int
				 * smallest = Integer.MAX_VALUE; int temp = 0; for (i = n - 2; i
				 * >= 0; i--) { for (int j = 0; j < maxWidth; j++) {// n if
				 * (T[i][j] != -1 && i + j + 1 < n) { temp = T[i][j] + array[i +
				 * j + 1]; } else { temp = T[i][j]; } smallest = (temp == -1) ?
				 * smallest : Math.min(smallest, temp); } array[i] = smallest;
				 * smallest = Integer.MAX_VALUE; } int lastRow = 0; i = n - 1;
				 * while (i - 1 >= 0 && array[i] > array[i - 1]) { lastRow =
				 * Math.min(array[i], array[i - 1]); i--; } lastRow =
				 * Math.min(array[i], array[i - 1]);
				 * 
				 * // Output the formatted text // TODO: replace with the right
				 * answer System.out.println(array[0] - lastRow); }
				 * 
				 * scanner.close(); }
				 */

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

			int width = 2 * maxWidth;
			// TODO: Put your implementation here
			int[][] T = new int[n][width]; // n
			int sum = 0;
			int x = 0;
			for (i = 0; i < n; i++) {
				for (int j = 0; j < width; j++) { // n
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
