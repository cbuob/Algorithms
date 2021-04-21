import java.util.Scanner;

public class Sieb {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Enter a number; ");
		int n = console.nextInt();
		boolean[] array = new boolean[n + 1];
		for (int i = 2; i < (n + 1) / 2; i++) {
			for (int j = 2; i * j <= n; j++) {
				array[i * j] = true; // set element to true if it has a divisor
			}
		}
		// count number of primes
		int count = 0;
		for (int i = 2; i < n + 1; i++) {
			if (array[i] == false) {
				count++;
				// System.out.println(i);
			}
		}
		System.out.println(count + " primes were found.");
	}

}
