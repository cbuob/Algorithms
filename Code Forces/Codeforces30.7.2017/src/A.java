import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int k = console.nextInt();
		int[] A = new int[n];
		int[] count = new int[26];
		String s = console.next();
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);

			A[i] = (int) ch - (int) 'A';
			count[A[i]]++;
		}
		int[] open = new int[26];
		for (int i = 0; i < n; i++) {
			int cur = A[i];
			if (open[cur] == 0) {
				open[cur] = 1;
				k--;
				count[cur]--;
				if (k < 0) {
					System.out.println("yes");
					return;
				}
				if (count[cur] == 0) {
					k++;
					open[cur] = 0;
				}
			} else {
				count[cur]--;
				if (count[cur] == 0) {
					k++;
					open[cur] = 0;
				}
			}
		}
		System.out.println("no");

	}

}
