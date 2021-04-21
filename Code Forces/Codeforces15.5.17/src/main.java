import java.util.Scanner;

public class main {
	int node = 0;

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		if (n > 9) {
			int rest = 0;
			int count = 1;
			while (n > 9) {
				rest += (n % 10) * count;
				n /= 10;
				count *= 10;

			}
			System.out.println(count - rest);

		} else {
			System.out.println(1);

		}
		console.close();

	}

}
