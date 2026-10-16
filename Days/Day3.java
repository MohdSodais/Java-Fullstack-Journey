package Days;

import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many hours did you study today? ");
        int hoursStudied = scanner.nextInt();

        // 1. Using if/else to make decisions
        if (hoursStudied >= 5) {
            System.out.println("🔥 Amazing dedication! You crushed your daily goal.");
        } else if (hoursStudied > 0) {
            System.out.println("👍 Good effort! Every hour counts.");
        } else {
            System.out.println("⚠️ No study time recorded today. Let's make tomorrow count!");
        }

        // 2. Using a 'for' loop to print a daily motivation streak tracker
        System.out.println("\nYour 3-Day Consistency Check:");
        for (int day = 1; day <= 3; day++) {
            System.out.println("Day " + day + " status: Completed code check-in ✅");
        }

        scanner.close();
    }
}