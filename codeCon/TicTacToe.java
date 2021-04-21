package codeCon;

import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int[] array = new int[stdin.nextInt()];
		int i = 0;
		int flag = 0;
		while (i < array.length) {
			array[i] = stdin.nextInt();
			i++;
		}
		int temp;
		int steps = 0;
		for (i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) {
				steps++;
			}
		}
		if (steps == 1 && array[array.length - 1] <= array[0]) {
			flag = 1;
		}

		steps = 0;
		for (i = 1; i < array.length; i++) {
			if (array[i - 1] < array[i]) {
				steps++;
			}
		}
		if (steps == 1 && array[array.length - 1] >= array[0]) {
			flag = 1;
		}

		steps = 0;
		for (i = 1; i < array.length; i++) {
			if (array[i - 1] != array[i]) {
				steps++;
			}
		}
		if (steps == 1 || steps == 0) {
			flag = 1;
		}

		if (array.length == 2) {
			flag = 0;
		}

		if (flag == 1) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

		stdin.close();
	}

}
