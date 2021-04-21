package codeCon;

import java.util.Scanner;

public class friends {
	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int counter = 0;
		if (stdin.hasNextLine()) {
			String[] array = new String[stdin.nextInt()];
			int i = 0;
			if (stdin.hasNextLine())
				stdin.nextLine();
			while (i < array.length) {
				array[i] = stdin.nextLine();
				i++;
			}

			boolean bool = false;

			for (i = 0; i < array.length; i++) {
				for (int j = i + 1; j < array.length; j++) {

					char[] first = array[i].toLowerCase().toCharArray();
					char[] second = array[j].toLowerCase().toCharArray();
					for (int h = 0; h < first.length; h++) {
						for (int k = 0; k < second.length; k++) {
							if (first[h] == second[k]) {
								bool = true;
							}
						}
					}

					if (bool) {
						bool = false;
					} else {
						counter++;
					}
				}
			}
		} else {
			System.out.println(0);
		}
		System.out.println(counter - 2);
		stdin.close();
	}

}
