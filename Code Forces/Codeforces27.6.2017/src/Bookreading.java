import java.util.Scanner;

public class Bookreading {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int c = console.nextInt();
		int v0 = console.nextInt();
		int v1 = console.nextInt();
		int a = console.nextInt();
		int l = console.nextInt();

		int current = v0;
		int days = 1;
		int speed = v0;
		while (current < c) {
			current = current - l;
			speed = (speed + a) < v1 ? speed + a : v1;
			current = current + speed;
			days++;
		}
		System.out.println(days);

	}

}
