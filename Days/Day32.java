package Days;

import java.util.Scanner;

public class Day32 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacity = 6;
        // Parallel arrays representing parking spots
        String[] licensePlates = new String[capacity];
        int[] entryHours = new int[capacity];
        boolean[] isOccupied = new boolean[capacity];
        double hourlyRate = 5.50;

        boolean running = true;
        System.out.println("=== JAVA CITY PARKING GARAGE ($" + String.format("%.2f", hourlyRate) + "/hr) ===");

        while (running) {
            // Display current garage status
            System.out.println("\n-----------------------------------------");
            System.out.println("Garage Map:");
            for (int i = 0; i < capacity; i++) {
                if (isOccupied[i]) {
                    System.out.printf("Spot %d: [ 🚗 %-8s | Parked since %02d:00 ]\n",
                            (i + 1), licensePlates[i], entryHours[i]);
                } else {
                    System.out.println("Spot " + (i + 1) + ": [ Empty ]");
                }
            }
            System.out.println("-----------------------------------------");

            System.out.println("1. Park a Vehicle (Check-In)");
            System.out.println("2. Retrieve Vehicle & Pay (Check-Out)");
            System.out.println("3. Locate Vehicle by License Plate");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            if (choice == 1) {
                // Find the first empty spot
                int emptySpot = -1;
                for (int i = 0; i < capacity; i++) {
                    if (!isOccupied[i]) {
                        emptySpot = i;
                        break;
                    }
                }

                if (emptySpot == -1) {
                    System.out.println(" Garage is completely full! No spots available.");
                } else {
                    System.out.print("Enter vehicle license plate: ");
                    String plate = scanner.nextLine().toUpperCase();

                    System.out.print("Enter current hour (0-23, 24-hour format): ");
                    int hour = scanner.nextInt();

                    if (hour >= 0 && hour <= 23) {
                        licensePlates[emptySpot] = plate;
                        entryHours[emptySpot] = hour;
                        isOccupied[emptySpot] = true;
                        System.out.println(" Vehicle [" + plate + "] parked in Spot " + (emptySpot + 1) + ".");
                    } else {
                        System.out.println(" Invalid hour. Please enter a value between 0 and 23.");
                    }
                }

            } else if (choice == 2) {
                System.out.print("Enter Spot Number to check out (1-" + capacity + "): ");
                int spotIndex = scanner.nextInt() - 1;

                if (spotIndex >= 0 && spotIndex < capacity && isOccupied[spotIndex]) {
                    System.out.print("Enter exit hour (0-23, 24-hour format): ");
                    int exitHour = scanner.nextInt();

                    if (exitHour >= 0 && exitHour <= 23) {
                        // Calculate duration (handles overnight wrap-around if exitHour < entryHour)
                        int duration = exitHour - entryHours[spotIndex];
                        if (duration <= 0) {
                            duration += 24;
                        }

                        double fee = duration * hourlyRate;
                        System.out.println("\n🧾 --- PARKING RECEIPT ---");
                        System.out.println("Plate:    " + licensePlates[spotIndex]);
                        System.out.println("Duration: " + duration + " hour(s)");
                        System.out.printf("Total Due: $%.2f\n", fee);

                        // Free the spot
                        licensePlates[spotIndex] = null;
                        entryHours[spotIndex] = 0;
                        isOccupied[spotIndex] = false;
                        System.out.println(" Spot " + (spotIndex + 1) + " is now open.");
                    } else {
                        System.out.println(" Invalid hour.");
                    }
                } else {
                    System.out.println(" Invalid spot number or spot is already empty.");
                }

            } else if (choice == 3) {
                System.out.print("Enter license plate to find: ");
                String searchPlate = scanner.nextLine().toUpperCase();
                boolean found = false;

                for (int i = 0; i < capacity; i++) {
                    if (isOccupied[i] && licensePlates[i].equals(searchPlate)) {
                        System.out.println("🔍 Vehicle [" + searchPlate + "] is parked in Spot " + (i + 1) + ".");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println(" No vehicle found with license plate [" + searchPlate + "].");
                }

            } else if (choice == 4) {
                System.out.println("Closing Parking Garage System. Drive safely!");
                running = false;
            } else {
                System.out.println(" Invalid option. Choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}