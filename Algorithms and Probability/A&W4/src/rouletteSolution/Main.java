package rouletteSolution;

import java.math.RoundingMode;
import java.text.DecimalFormat;

// Algoscore: C

import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		// Create a new Scanner object for reading the input
		Scanner sc = new Scanner(System.in);

		// Read the number of testcases to follow
		int t = sc.nextInt();

		// Iterate over the testcases and solve the problem
		for (int i = 0; i < t; i++) {
			testCase(sc);
		}
	}

	public static void testCase(Scanner sc) {
		int n, m, k;
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();

		double probs[] = new double[1001]; // probs[i] contains the probability
											// that the ball
											// land on a slot with number 0 at
											// least k
											// consecutive times after
											// spinning the wheel i times

		int counter = 0; // contains the number of slots with 0 written on them
		for (int i = 0; i < n; i++) {
			int val = sc.nextInt();
			if (val == 0)
				counter++;
		}

		double p0 = counter / ((double) n); // probability to land on 0 in one
											// spin
		double p0k = 1; // p0k contains p0 to the power of k

		for (int i = 0; i < k; i++) { // In the first k - 1 spinnings, prob[i]
										// must be 0
			probs[i] = 0;
			p0k *= p0;
		}

		probs[k] = p0k;

		// The following loop calculates probs[i] using DP
		for (int i = k + 1; i <= m; i++) {
			probs[i] = probs[i - 1]; // 1st possibility: the desired event
										// happened
										// in the first i - 1 spinnings
			probs[i] += (1 - probs[i - k - 1]) * (1 - p0) * p0k; // 2nd.
																	// possibility:
																	// The last
																	// k times
																	// the ball
																	// landed on
																	// a zero
																	// slot, but
																	// this did
																	// not
																	// happen
																	// at any
																	// point
																	// before
		}

		DecimalFormat df = new DecimalFormat("0.0######");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		System.out.println(df.format(probs[m]));
	}
}
