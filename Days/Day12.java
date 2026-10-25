package Days;

import java.util.Scanner;

public class Day12 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of terms for Fibonacci series: ");
            int n = scanner.nextInt();

            long first = 0, second = 1;
            System.out.print("Fibonacci Series: " + first + " " + second + " ");

            for (int i = 3; i <= n; i++) {
                long next = first + second;
                System.out.print(next + " ");
                first = second;
                second = next;
            }
            System.out.println();
            scanner.close();
        }
    }

