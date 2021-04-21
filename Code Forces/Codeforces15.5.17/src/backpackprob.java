import java.util.Scanner;

public class backpackprob {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int m = console.nextInt();
		int[] Weight = new int[n];
		int[] Cost = new int[n];
		for (int i = 0; i < n; i++) {
			Weight[i] = console.nextInt();
			Cost[i] = console.nextInt();
		}
		int[] T = new int[m + 1];

		for (int i = 1; i < m + 1; i++) {
			int max = 0;
			for (int j = 0; j < n; j++) {
				int temp = (i - Weight[j] < 0) ? 0 : T[i - Weight[j]] + Cost[j];
				max = (max > temp) ? max : temp;
			}
			T[i] = Math.max(max, T[i - 1]);
		}

		System.out.println(T[m]);

	}

}
