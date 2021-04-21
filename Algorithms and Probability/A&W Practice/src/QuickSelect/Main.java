package QuickSelect;

import java.util.Random;

public class Main {

	static int right, left;
	static Random rand;
	static int mid;

	public static void main(String[] args) {
		while (true) {
			int[] A = { 1, 8, 5, 9, 3, 0, 2, 4, 7, 6 };
			rand = new Random();
			left = 0;
			right = A.length - 1;
			mid = (right - left) / 2;

			int sol = sort(left, right, A, rand);
			System.out.println(sol);
		}
	}

	public static int sort(int left, int right, int[] A, Random rand) {
		int p = rand.nextInt(right - left + 1);
		p += left;
		int pivot = A[p];
		A[p] = A[right];
		A[right] = pivot;
		int l = left;
		int r = right;
		while (l < r) {
			while (true) {
				if (l >= r || A[l] > pivot)
					break;
				l++;
			}
			while (true) {
				if (l >= r || A[r] < pivot)
					break;
				r--;
			}
			if (A[r] < pivot && A[l] > pivot) {
				int temp = A[l];
				A[l] = A[r];
				A[r] = temp;
			}

		}
		A[right] = A[r];
		A[r] = pivot;
		if (r >= mid) {
			right = r;
		} else {
			left = l;
		}
		if (right == mid) {
			return A[mid];
		}

		return sort(left, right, A, rand);
	}

}
