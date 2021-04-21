import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main {

        public static void main(String[] args) {
        	
                // Read from the standard input with java.util.Scanner
                Scanner scanner = new Scanner(System.in);
                int n = scanner.nextInt();
                
                // Create new array for n integers 
                int[] A = new int[n];
                
                // Read n integers into A
                int i;
                for (i = 0; i < n; i++)
                        A[i] = scanner.nextInt();
                scanner.close();

                // Put your implementation here - a working example follows

                // Maximum value seen so far
                int max = 0;
                
                for (i = 0; i < n; i++) {
                        // If the i-th element is bigger, update max 
                        if (A[i] > max)
                                max = A[i];
                }

                // Output the result
                System.out.println(max);

        }
}
