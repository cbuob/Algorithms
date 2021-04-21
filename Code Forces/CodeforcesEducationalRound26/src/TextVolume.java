import java.util.Scanner;

public class TextVolume {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();

		int max = 0;
		console.nextLine();
		String s = console.nextLine();
		int sum = 0;
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				sum++;
			}
			if (c == ' ') {
				max = (sum > max) ? sum : max;
				sum = 0;
			}
			if (i == n - 1) {
				max = (sum > max) ? sum : max;

			}
		}
		System.out.println(max);

	}

}
