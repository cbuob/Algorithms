import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

	/*
	 * Do not modify this function. You also do not need to understand it and
	 * may treat it just as a blackbox.
	 */
	public static int f(int x) {
		int r = 0, z = x;
		while (z > 0) {
			r = r + x;
			z = z / 2;
		}
		return r;
	}

	public static int find(int a, int x_max) {
		int l = 0;
		int r = x_max;
		int middle;
		while (true) {
			middle = f((l + r) / 2);
			if (middle == a) {
				return ((l + r) / 2);
			} else if (a > middle) {
				l = ((l + r) / 2) + 1;
			} else if (a < middle) {
				r = ((l + r) / 2) - 1;
			}
			if (l > r) {
				break;
			}

		}
		return -1;
	}

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		// x_max from the task description
		int x_max = 20000000;

		// Read n integers and for each run the search
		// Note: we do not allocate an array for them, since we only need
		// to process one value at a time.
		int i;

		for (i = 0; i < n; i++) {
			// Load the next value (a_i) into a
			int a = scanner.nextInt();

			int result = find(a, x_max);
			if (result == -1) {
				System.out.println("NO");
			} else {
				System.out.println(result);
			}

			// Note: To print the string "NO", use:

		}
		scanner.close();
	}
}
