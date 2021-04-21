import java.util.Scanner;

public class increasingSequence {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int a = console.nextInt();
		int b = console.nextInt();
		int[] A = new int[a];
		int[] B = new int[b];
		boolean bool = false;
		for (int i = 0; i < a; i++) {
			A[i] = console.nextInt();
			if (i > 0 && A[i] != 0 && A[i - 1] != 0) {
				if (A[i - 1] > A[i]) {
					bool = true;
				}
			}
		}
		for (int i = 0; i < b; i++) {
			B[i] = console.nextInt();
		}

		if (b > 1 || bool) {
			System.out.println("Yes");
		} else {
			for (int i = 0; i < a; i++) {
				if (A[i] == 0) {
					if (i != 0 && A[i - 1] > B[0]) {
						System.out.println("Yes");
						break;
					}
					if (i < a - 1 && A[i + 1] < B[0]) {
						System.out.println("Yes");
						break;
					} else {
						System.out.println("No");
					}
				}
			}
		}

	}

}
