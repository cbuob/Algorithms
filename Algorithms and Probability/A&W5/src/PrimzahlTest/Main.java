package PrimzahlTest;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int tests = console.nextInt();

		for (int nr = 0; nr < tests; nr++) {
			int answer = 1;
			long n = console.nextLong();
			for (int i = 0; i < 100; i++) {
				answer *= test(n);
			}
			if (answer == 1) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}

		}
	}

	public static int test(long n) {

		if (n == 2) {
			return 1;
		} else if ((n == 1) || (n % 2 == 0)) {
			return 0;
		}
		Random rand = new Random();
		long a = (long) rand.nextDouble() * (n - 3) + 2;
		BigInteger BigA = BigInteger.valueOf(a);

		long n1 = n - 1;
		long k = 0;
		long d = n1;
		while (n1 % 2 == 0) {
			k++;
			n1 = n1 / 2;
			d = d / 2;
		}
		n1 = n - 1;
		BigInteger BigD = BigInteger.valueOf(d);
		BigInteger BigN = BigInteger.valueOf(n);
		BigInteger BigX = BigA.modPow(BigD, BigN);

		if (BigX.equals(BigInteger.valueOf(1)) || BigX.equals(BigInteger.valueOf(n1))) {
			return 1;
		}
		for (long i = k - 1; i > 0; i--) {
			BigX = BigX.modPow(BigInteger.valueOf(2), BigN);
			if (BigX.equals(BigInteger.valueOf(1))) {
				return 0;
			}
			if (BigX.equals(BigInteger.valueOf(n1))) {
				return 1;
			}
		}

		return 0;

	}
}
