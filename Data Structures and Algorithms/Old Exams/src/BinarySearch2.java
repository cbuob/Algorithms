import java.util.Scanner;

public class BinarySearch2 {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int m = console.nextInt();
		int[] A = new int[n];

		for (int i = 0; i < n; i++) {
			A[i] = console.nextInt();
		}
		int left = 0;
		int right = n;
		int middle = -1;
		boolean bool = false;
		while (left <= right) {
			middle = (right + left) / 2;
			if (A[middle] == m) {
				bool = true;
				break;

			}
			if (A[middle] > m) {
				right = middle - 1;
				continue;
			}
			if (A[middle] < m) {
				left = middle + 1;
				continue;
			}
		}
		if (bool) {
			System.out.println(middle);
		} else {
			System.out.println(-1);
		}

	}

}
