import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);

		// Read C into 'cases' and loop over the cases
		int case_no, cases = scanner.nextInt();
		for (case_no = 0; case_no < cases; case_no++) {

			// Each case has its own local variables i, n, d, e, ...
			int i;

			// Read n, D and then the array d[i] and e[i]
			int n = scanner.nextInt();
			int mindist = scanner.nextInt();
			int[] d = new int[n];
			for (i = 0; i < n; i++)
				d[i] = scanner.nextInt();
			int[] e = new int[n];
			for (i = 0; i < n; i++)
				e[i] = scanner.nextInt();

			// Note that you can use the "System.err" stream for printing any
			// information to be ignored by the judge, but visible in your
			// console. However note that too many printouts can slow down
			// your program during judging.
			// Example (you may delete this):
			// System.err.println("Case #" + case_no + ": read n=" + n + "
			// turbine locations with min distance D="
			// + mindist + " (this info is ignored by the judge).");

			// TODO: Put your implementation here
			// Create DP Table
			int[] t = new int[n];
			t[0] = e[0];
			int k = 0;
			for (int j = 1; j < n; j++) {
				if (d[j] - d[j - 1] >= mindist) {
					t[j] = t[j - 1] + e[j];
				} else {
					while (d[j] - d[j - k] < mindist) {
						if (j - k >= 1) {
							k++;
						} else {
							break;
						}
					}
					if (d[j] - d[j - k] < mindist) {
						t[j] = t[j - 1] > e[j] ? t[j - 1] : e[j];
					} else {
						t[j] = (t[j - k] + e[j] > t[j - 1]) ? t[j - k] + e[j] : t[j - 1];
					}
					k = 0;
				}
			}

			// Output the maximum energy output
			System.out.println(t[n - 1]); // TODO: replace with the right answer
		}

		scanner.close();
	}
}
