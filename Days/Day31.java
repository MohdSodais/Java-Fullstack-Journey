package Days;

import java.util.Scanner;

public class Day31 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parallel arrays representing hotel rooms
        int[] roomNumbers = {101, 102, 201, 202, 301, 302};
        String[] roomTypes = {"Standard", "Standard", "Deluxe", "Deluxe", "Suite", "Standard"};
        double[] nightlyRates = {100.00, 100.00, 175.00, 175.00, 300.00, 100.00};
        boolean[] isOccupied = {false, true, false, false, false, true}; // Room 102 starts occupied
        String[] guestNames = {"", "John Doe", "", "", "", "Alex"};

        boolean running = true;
        System.out.println("=== JAVA GRAND HOTEL RESERVATION SYSTEM ===");

        while (running) {
            System.out.println("\n-------------------------------------------------------------");
            System.out.println("Room | Type     | Rate/Night | Status");
            System.out.println("-------------------------------------------------------------");
            for (int i = 0; i < roomNumbers.length; i++) {
                String status = isOccupied[i] ? ("Occupied (" + guestNames[i] + ")") : "Available";
                System.out.printf("%-4d | %-8s | $%-9.2f | %s\n",
                        roomNumbers[i], roomTypes[i], nightlyRates[i], status);
            }
            System.out.println("-------------------------------------------------------------");

            System.out.println("1. Check-In (Book a Room)");
            System.out.println("2. Check-Out (Calculate Bill & Free Room)");
            System.out.println("3. Filter Available Rooms by Type");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            if (choice == 1) {
                System.out.print("Enter room number to book: ");
                int targetRoom = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                int index = findRoomIndex(roomNumbers, targetRoom);

                if (index == -1) {
                    System.out.println(" Room " + targetRoom + " does not exist.");
                } else if (isOccupied[index]) {
                    System.out.println(" Room " + targetRoom + " is already occupied by " + guestNames[index] + ".");
                } else {
                    System.out.print("Enter guest full name: ");
                    String name = scanner.nextLine();
                    isOccupied[index] = true;
                    guestNames[index] = name;
                    System.out.println(" Check-in complete! " + name + " is booked into Room " + targetRoom + ".");
                }

            } else if (choice == 2) {
                System.out.print("Enter room number for check-out: ");
                int targetRoom = scanner.nextInt();

                int index = findRoomIndex(roomNumbers, targetRoom);

                if (index == -1) {
                    System.out.println(" Room " + targetRoom + " does not exist.");
                } else if (!isOccupied[index]) {
                    System.out.println(" Room " + targetRoom + " is currently vacant.");
                } else {
                    System.out.print("How many nights did " + guestNames[index] + " stay? ");
                    int nights = scanner.nextInt();

                    if (nights > 0) {
                        double totalBill = nights * nightlyRates[index];
                        System.out.println("\n --- CHECK-OUT RECEIPT ---");
                        System.out.println("Guest: " + guestNames[index]);
                        System.out.println("Room:  " + roomNumbers[index] + " (" + roomTypes[index] + ")");
                        System.out.printf("Total: $%.2f (%d nights @ $%.2f/night)\n",
                                totalBill, nights, nightlyRates[index]);

                        // Reset room state
                        isOccupied[index] = false;
                        guestNames[index] = "";
                        System.out.println(" Room " + targetRoom + " is now available.");
                    } else {
                        System.out.println(" Number of nights must be at least 1.");
                    }
                }

            } else if (choice == 3) {
                System.out.print("Enter room type to search (Standard / Deluxe / Suite): ");
                String searchType = scanner.nextLine();
                boolean found = false;

                System.out.println("\nAvailable " + searchType + " Rooms:");
                for (int i = 0; i < roomNumbers.length; i++) {
                    if (!isOccupied[i] && roomTypes[i].equalsIgnoreCase(searchType)) {
                        System.out.printf("• Room %d ($%.2f/night)\n", roomNumbers[i], nightlyRates[i]);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No available rooms match that type.");
                }

            } else if (choice == 4) {
                System.out.println("Exiting Hotel Reservation System. Goodbye!");
                running = false;
            } else {
                System.out.println(" Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }

    // Helper method to locate a room's array index by its room number
    public static int findRoomIndex(int[] rooms, int target) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == target) {
                return i;
            }
        }
        return -1;
    }
}