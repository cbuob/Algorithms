import java.util.Scanner;

public class GGT {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Enter x");
		int x = console.nextInt();
		System.out.println("Enter y");
		int y = console.nextInt();
		int[] xy = new int[2];
		xy[0] = x; // set x and y
		xy[1] = y;
		System.out.println(ggtRecursive(xy));

	}

	// recursive function that gets ggt
	public static int ggtRecursive(int[] xy) {
		if (xy[0] % xy[1] == 0) { // if y divides x the ggt is y
			return xy[1];
		} else {
			// swap x and y
			int temp = xy[0] % xy[1];
			xy[0] = xy[1];
			xy[1] = temp;
			return ggtRecursive(xy);

		}

	}

}
