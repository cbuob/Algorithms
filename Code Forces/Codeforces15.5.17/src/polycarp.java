import java.util.Scanner;

public class polycarp {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int k = console.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = console.nextInt();
		}
		double sum = 0;
		for (int i = 0; i < k; i++) {
			sum += A[i];
		}
		double up = sum;
		sum /= (n - k + 1);
		for (int i = k; i < n; i++) {
			up += A[i];
			up -= A[i - k];
			sum += up / (n + 1 - k);
		}
		System.out.println(sum);

	}

}
