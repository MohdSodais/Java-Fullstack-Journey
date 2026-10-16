package Days;

import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Day Number: ");
        int day = scanner.nextInt();

        System.out.print("How many hours did you study today? ");
        int hoursStudied = scanner.nextInt();

        System.out.println();

        // 1. Using boolean to track if studying happened today
        boolean studiedToday = hoursStudied > 0;   // if true the ans will be 1 and if false ans will be 0

        if (hoursStudied >= 5) {
            System.out.println("🔥 Amazing dedication! You crushed your daily goal.");
        } else if (studiedToday) {
            System.out.println("👍 Good effort! Every hour counts.");
        } else {
            System.out.println("⚠️ No study time recorded today. Let's make tomorrow count!");
        }

        // 2. Using a 'for' loop to print the streak tracker
        System.out.println("\nYour " + day + " Day Consistency Check:");

        for (int n = 1; n <= day; n++) {
            // If it's the current day and you didn't study, show the cross. Otherwise, show the checkmark.
            if (n == day && !studiedToday) {
                System.out.println("Day " + n + " status: No code check-in ❌");
            } else {
                System.out.println("Day " + n + " status: Completed code check-in ✅");
            }
        }

        scanner.close();
    }
}