import java.util.Scanner;

public class Reihe {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Enter N");
		double n = console.nextInt();
		double sum = 0;
		for (double i = 1; i <= n; i++) {
			sum = sum + 1 / xHochy(i, 2);
		}
		System.out.println(sum);

	}

	public static double xHochy(double x, double y) {
		double result = 1;
		for (int i = 0; i < y; i++) {
			result = result * x;
		}
		return result;
	}

}
