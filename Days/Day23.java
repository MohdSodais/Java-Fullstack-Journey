package Days;

import java.util.Scanner;

public class Day23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parallel arrays for days and temperatures
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        double[] temperatures = new double[days.length];

        System.out.println("=== WEEKLY WEATHER STATION & ANALYZER ===");
        System.out.println("Please enter the temperature (°F) for each day:\n");

        // 1. Input temperatures for the week
        for (int i = 0; i < days.length; i++) {
            System.out.print("Enter temperature for " + days[i] + ": ");
            temperatures[i] = scanner.nextDouble();
        }

        boolean running = true;
        while (running) {
            System.out.println("\n----------------------------------");
            System.out.println("WEATHER ANALYSIS MENU:");
            System.out.println("1. View Full Weekly Log");
            System.out.println("2. Find Hottest and Coldest Days");
            System.out.println("3. Calculate Weekly Average");
            System.out.println("4. Filter Days Above Average");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // View log
                System.out.println("\n--- Weekly Temperature Log ---");
                for (int i = 0; i < days.length; i++) {
                    System.out.println(days[i] + " — " + temperatures[i] + "°F");
                }

            } else if (choice == 2) {
                // Find extremes and track their matching day indices
                int hottestIdx = 0;
                int coldestIdx = 0;

                for (int i = 1; i < temperatures.length; i++) {
                    if (temperatures[i] > temperatures[hottestIdx]) {
                        hottestIdx = i;
                    }
                    if (temperatures[i] < temperatures[coldestIdx]) {
                        coldestIdx = i;
                    }
                }

                System.out.println("\n🔥 Hottest Day: " + days[hottestIdx] + " (" + temperatures[hottestIdx] + "°F)");
                System.out.println("❄️ Coldest Day: " + days[coldestIdx] + " (" + temperatures[coldestIdx] + "°F)");

            } else if (choice == 3) {
                // Compute average
                double sum = 0;
                for (double temp : temperatures) {
                    sum += temp;
                }
                double average = sum / temperatures.length;
                System.out.printf("\n📊 Weekly Average Temperature: %.2f°F\n", average);

            } else if (choice == 4) {
                // Compute average first to use as a dynamic threshold filter
                double sum = 0;
                for (double temp : temperatures) {
                    sum += temp;
                }
                double average = sum / temperatures.length;

                System.out.println("\n--- Days Above Weekly Average (" + String.format("%.1f", average) + "°F) ---");
                boolean found = false;
                for (int i = 0; i < days.length; i++) {
                    if (temperatures[i] > average) {
                        System.out.println("• " + days[i] + " (" + temperatures[i] + "°F)");
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No days exceeded the weekly average.");
                }

            } else if (choice == 5) {
                System.out.println("Exiting Weather Station. Stay safe!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Choose between 1 and 5.");
            }
        }

        scanner.close();
    }
}
