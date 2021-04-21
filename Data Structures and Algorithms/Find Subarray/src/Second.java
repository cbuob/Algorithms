import java.util.Scanner;

class Second {

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

			int[] cArray = new int[k + 1];
			cArray[0] = 1;
			for (i = 1; i < cArray.length; i++) {
				cArray[i] = (cArray[i - 1] * c) % m;
			}
			int bHash = 0;
			for (i = 0; i < k; i++) {
				bHash += B[i] * cArray[k - i - 1] % m;
				bHash = bHash % m;
			}
			int aHash = 0;
			for (i = 0; i < k; i++) {
				aHash += A[i] * cArray[k - i - 1] % m;
				aHash = aHash % m;
			}

			if (aHash == bHash) {
				System.out.print(0 + " ");
			}
			int old;
			int mew;
			boolean bool = true;
			for (i = 1; i < n - k + 1; i++) {
				old = (A[i - 1] * ((m - cArray[k]) % m)) % m;
				mew = (A[k + i - 1]) % m;

				aHash = (((c * aHash) % m) + old + mew) % m;
				if (aHash == bHash) {
					for (int j = 0; j < k; j++) {
						if (A[i + j] != B[j]) {
							bool = false;
						}

					}
					if (bool) {
						System.out.print(i + " ");
					}
					bool = true;
				}
			}

			System.out.println("DONE");

		}
	}
}