import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);
		int i;

		// Read m and then m integers into new array L
		int m = scanner.nextInt();
		int[] L = new int[m];
		for (i = 0; i < m; i++)
			L[i] = scanner.nextInt();

		// Read n and then n integers into new array A
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (i = 0; i < n; i++)
			A[i] = scanner.nextInt();

		scanner.close();

		// Note that you can use the "System.err" stream for printing any
		// information to be ignored
		// by the judge, but visible in your console. However note that too many
		// printouts
		// can slow down your program during judging.
		// Example (you may delete this):
		System.err.println("Read " + m + " ribbons and " + n + " presents. (This info is ignored by the judge)");

		// Put your implementation here
		int count = 0;
		i = 0;
		int j = 0;
		long temp;
		while (i < m && j < n) {
			if (L[i] % 4 != 0) {
				i++;
				continue;
			}
			temp = ((L[i] / 4) * (L[i] / 4));

			if (temp == A[j]) {
				count++;
				i++;
				j++;
			} else if (temp >= A[j]) {
				j++;
			} else {
				i++;
			}
		}

		// Output the number of matching pairs
		System.out.println(count); // TODO: replace with the right answer

	}
}
