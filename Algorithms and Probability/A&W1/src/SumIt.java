import java.util.Scanner;

public class SumIt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();
		for (int nr = 0; nr < tests; nr++) {
			int n = console.nextInt();
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += console.nextInt();
			}
			System.out.println(sum);

		}
	}

}
