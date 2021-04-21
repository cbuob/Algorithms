package codeCon;
//Problem        : Messed up Rugby

//Language       : Java
//Compiled Using : javac
//Version        : Java 1.7.0_75
//Input for your program will be provided from STDIN
//Print out all output from your program to STDOUT

import java.util.Scanner;

//Your submission should *ONLY* use the following class name
public class Rugby {
	public static void main(String[] args) {
		int a = 7;
		int b = 3;
		int c = 2;

		Scanner stdin = new Scanner(System.in);
		while (stdin.hasNextLine()) {
			int x = stdin.nextInt();
			for (int i = 0; i * a <= x; i++) {
				for (int j = 0; j * b + a + i <= x; j++) {
					for (int h = 0; j * b + a + i + c + h <= x; h++) {

					}
				}
			}
		}
		stdin.close();

	}

}
