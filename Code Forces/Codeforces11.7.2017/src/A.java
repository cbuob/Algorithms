import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		// ScanTODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int a = console.nextInt();
		int b = console.nextInt();
		int bhalf = 0;
		int count = 0;
		for (int i = 0; i < n; i++) {
			int cur = console.nextInt();
			if (cur == 1) {
				if (a > 0) {
					a--;
				} else if (b > 0) {
					b--;
					bhalf++;
				} else if (bhalf > 0) {
					bhalf--;
				} else {
					count++;
				}
			} else {
				if (b > 0) {
					b--;
				} else {
					count++;
					count++;
				}
			}
		}
		System.out.println(count);

	}

}
