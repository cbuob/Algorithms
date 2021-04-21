import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

	public static void main(String[] args) {

		// Read from the standard input with java.util.Scanner
		Scanner scanner = new Scanner(System.in);

		// Read the number of paragraphs and page width
		int m = 32768;
		int c = 1021;
		int case_no, cases = scanner.nextInt();

		for (case_no = 1; case_no <= cases; case_no++) {

			// Sizes of array A and array B
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			int[] A = new int[n];
			int[] B = new int[k];
			int i;
			// Read elements of A and elements of B
			for (i = 0; i < n; i++) {
				A[i] = scanner.nextInt();
			}
			for (i = 0; i < k; i++) {
				B[i] = scanner.nextInt();
			}

			// TODO: Put your implementation here
			int bHash = getHash(0, B, k, c, m);
			int aHash = getHash(0, A, k, c, m);
			if (aHash == bHash) {
				System.out.print(0 + " ");
			}
			int cTok;
			for (i = 0; i < k; i++) {

			}
			for (i = 1; i < n - k + 1; i++) {
				// aHash = getHash(i, A, k, c, m);
				aHash = getNext(aHash, i, A, k, c, m);
				// System.out.println(aHash + " " + bHash);
				if (aHash == bHash) {
					System.out.print(i + " ");
				}
			}

			// H = (c * H + A[i+k] + (m - cToK) * A[i]) % m;

			System.out.println("DONE");

		}
		scanner.close();
	}

	public static int getHash(int startIndex, int[] array, int k, int c, int m) {
		int hash = 0;
		int j = 0;
		for (int i = startIndex; i < startIndex + k; i++) {
			hash += (array[i] * (Math.pow(c, k - j - 1) % m)) % m;
			hash = hash % m;
			j++;
		}
		// System.out.println(hash);
		return hash;
	}

	public static int getNext(int hash, int startIndex, int[] array, int k, int c, int m) {
		long old = (int) ((m - ((Math.pow(c, k) % m)) * array[startIndex - 1]) % m);
		long mew = array[startIndex + k - 1];
		long hashl = (hash * c + mew + old) % m;
		// hash = (hash * c + mew + old) % m;

		// hash += ((array[startIndex + k - 1] + c * hash) + (m - ((Math.pow(c,
		// k) % m)) * array[startIndex - 1])) % m;
		return (int) (hashl % m);

	}
}
