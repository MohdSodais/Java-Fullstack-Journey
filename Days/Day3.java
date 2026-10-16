package Days;

import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Day Number: ");
        int day = scanner.nextInt();

        System.out.print("How many hours did you study today? ");
        int hoursStudied = scanner.nextInt();
        int count = 0;

        System.out.println();

        // 1. Using if/else to make decisions
        if (hoursStudied >= 5) {
            System.out.println("🔥 Amazing dedication! You crushed your daily goal.");
            count++;
        } else if (hoursStudied > 0) {
            System.out.println("👍 Good effort! Every hour counts.");
            count++;
        } else {
            System.out.println("⚠️ No study time recorded today. Let's make tomorrow count!");
        }

        // 2. Using a 'for' loop to print a daily motivation streak tracker
        System.out.println("\nYour " + day + " Day Consistency Check:");
        if(count == 1) {
            for (int n = 1; n <= day; n++) {
                System.out.println("Day " + n + " status: Completed code check-in ✅");
            }
        } else {
            for (int n = 1; n <= day-1; n++) {
                System.out.println("Day " + n + " status: Completed code check-in ✅");
            }
            System.out.println("Day " + day + " status: No code check-in ❌");
        }

        scanner.close();
    }
}