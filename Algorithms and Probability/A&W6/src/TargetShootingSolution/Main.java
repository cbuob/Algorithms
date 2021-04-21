package TargetShootingSolution;

import java.util.Random;

// Algoscore C C

import java.util.Scanner;

class Main {
	public static Random rand = new Random();

	public static void main(String[] args) {
		// Create a new Scanner object for reading the input
		Scanner sc = new Scanner(System.in);

		// Read the number of test cases to follow
		int t = sc.nextInt();

		// Iterate over the test cases and solve the problem
		for (int i = 0; i < t; i++) {
			testCase(sc);
		}
	}

	public static void testCase(Scanner sc) {
		// Read the number of dimensions
		int d = sc.nextInt();
		// Read the number of constraints
		int n = sc.nextInt();

		// Read the constraints
		int[][] body = new int[n][d + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d + 1; j++) {
				body[i][j] = sc.nextInt();
			}
		}

		// Read the supposed volume of the body
		double target_volume = sc.nextDouble();

		// As we do not have a lower bound on the volume of the body
		// we first try with some number of iterations to get an estimate
		long numIterations = 10000;
		long volume = 0;
		for (int i = 0; i < numIterations; i++) {
			// Sample a point inside of the [0, 1]^d space uniformly at random
			double[] point = getRandomPoint(d);
			// If the point is in the body, cout it as a hit
			if (isInBody(point, body, d)) {
				volume++;
			}
		}

		// First estimate of the volume computed as the number of hits divided
		// by the number of iterations
		double alpha = (double) volume / (double) numIterations;
		// The real number of iterations depends on the error term and the first
		// estimation
		// of the volume
		numIterations = (long) (100000 * (double) 1.0 / (double) alpha);
		volume = 0;
		for (int i = 0; i < numIterations; i++) {
			// Sample a point inside of the [0, 1]^d space uniformly at random
			double[] point = getRandomPoint(d);
			// If the point is in the body, cout it as a hit
			if (isInBody(point, body, d)) {
				volume++;
			}
		}

		// The estimated volume is computed as the number of hits divided
		// by the number of iterations
		double estimate = (double) volume / (double) numIterations;
		double upper_bound = (double) target_volume * 1.01;
		double lower_bound = (double) target_volume * 0.99;
		// If the supposed volume is close enough to the real body volume
		// estimate we return a "yes"
		if (estimate >= lower_bound && estimate <= upper_bound) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}

	// Sample a point from [0, 1]^d uniformly at random
	private static double[] getRandomPoint(int d) {
		double[] point = new double[d];
		for (int i = 0; i < d; i++) {
			point[i] = rand.nextDouble();
		}

		return point;
	}

	// Check if a point p is inside of the body
	private static boolean isInBody(double[] p, int[][] body, int d) {
		// If the point satisfies all the constraints of the body then it
		// belongs to it
		for (int i = 0; i < body.length; i++) {
			double sum = 0;
			for (int j = 0; j < d; j++) {
				sum += (double) body[i][j] * p[j];
			}
			if (sum > body[i][d]) {
				return false;
			}
		}

		return true;
	}
}