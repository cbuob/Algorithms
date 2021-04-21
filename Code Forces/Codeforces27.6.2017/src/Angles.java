import java.util.Scanner;

public class Angles {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int a = console.nextInt();

		double total = (n - 2) * 180;
		double biggest = total / n;
		double smallest = ((total / n) / (n - 2));

		if (a > biggest) {
			System.out.println("1 2 3");
		} else {
			double guess = a / smallest;
			double floor = Math.floor(guess);
			double ceil = Math.ceil(guess);
			double floorguess = floor * smallest;
			double ceilguess = ceil * smallest;
			double nrOfSmallest = ((Math.abs(a - floorguess) < (Math.abs(a - ceilguess))) ? floor : ceil);
			double res = (guess >= 1) ? 2 + nrOfSmallest : 3;
			System.out.println("2 1 " + (int) res);
		}

	}

}
