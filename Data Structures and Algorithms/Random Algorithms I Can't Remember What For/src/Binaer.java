import java.util.Scanner;

public class Binaer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Binär ist fertig aber ich versteh nicht wiso es nicht mehr geht wenn
		// N grösser als 1000 ist :(

		Scanner console = new Scanner(System.in);
		System.out.println("Enter a number to generate Binary representation");
		int z = console.nextInt();
		int binary = 0;
		if (z > 1) {
			while (z > 1) {
				int power = highestPowerOf2(z);
				binary = binary + xHochy(10, power);
				z = z - xHochy(2, power);
			}
		} else {
			binary = 1;
		}
		System.out.println(binary);
	}

	// berechnet x hoch y
	public static int xHochy(int x, int y) {
		int result = 1;
		for (int i = 0; i < y; i++) {
			result = result * x;
		}
		return result;
	}

	public static int highestPowerOf2(int z) {
		int i = 1;
		while (xHochy(2, i) <= z) {
			i++;
		}
		return i - 1;
	}

}
