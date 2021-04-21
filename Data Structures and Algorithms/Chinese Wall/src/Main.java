import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		// Create new array for n integers
		int[] A = new int[n];

		// Read n integers into A
		int i;
		for (i = 0; i < n; i++)
			A[i] = scanner.nextInt();
		scanner.close();

		// Put your implementation here
		int temp;
		int count = 0;
		int element = -1000;
		int index = 0;
		for (i = 0; i < n; i++) {
			if (A[i] == i) {
				A[i] = -1;

			} else if (A[i] != -1 && A[i] != i) {
				index = i;
				count++;
				while (A[index] != -1) {
					temp = index;
					index = A[index];
					A[temp] = -1;
					count++;
				}
			}

			// Output the number of car moves
			// TODO: replace with the right answer

		}
		System.out.println(count);
	}
}
