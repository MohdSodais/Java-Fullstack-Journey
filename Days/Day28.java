package Days;

import java.util.Scanner;

public class Day28 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 2D Array representing an airplane cabin (4 rows, 3 columns)
        // 'O' = Available, 'X' = Booked
        char[][] seats = {
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        };

        boolean running = true;
        System.out.println("=== AIRLINE SEAT BOOKING SYSTEM ===");

        while (running) {
            // Display the cabin seating chart
            System.out.println("\nCurrent Seating Chart:");
            System.out.println("       Col 1   Col 2   Col 3");
            for (int i = 0; i < seats.length; i++) {
                System.out.print("Row " + (i + 1) + " | ");
                for (int j = 0; j < seats[i].length; j++) {
                    System.out.print("  " + seats[i][j] + "    ");
                }
                System.out.println();
            }

            System.out.println("\n1. Book a Seat");
            System.out.println("2. Cancel a Booking");
            System.out.println("3. Check Available Seats Count");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Book a seat
                System.out.print("Enter row number (1-4): ");
                int row = scanner.nextInt() - 1;
                System.out.print("Enter column number (1-3): ");
                int col = scanner.nextInt() - 1;

                // Validate coordinate bounds
                if (row >= 0 && row < seats.length && col >= 0 && col < seats[0].length) {
                    if (seats[row][col] == 'O') {
                        seats[row][col] = 'X'; // Mark as booked
                        System.out.println("✅ Seat Row " + (row + 1) + ", Col " + (col + 1) + " successfully booked!");
                    } else {
                        System.out.println("❌ That seat is already booked! Please choose another.");
                    }
                } else {
                    System.out.println("❌ Invalid row or column selection!");
                }

            } else if (choice == 2) {
                // Cancel a booking
                System.out.print("Enter row number to cancel (1-4): ");
                int row = scanner.nextInt() - 1;
                System.out.print("Enter column number to cancel (1-3): ");
                int col = scanner.nextInt() - 1;

                if (row >= 0 && row < seats.length && col >= 0 && col < seats[0].length) {
                    if (seats[row][col] == 'X') {
                        seats[row][col] = 'O'; // Free up seat
                        System.out.println("🔄 Booking cancelled for Row " + (row + 1) + ", Col " + (col + 1) + ".");
                    } else {
                        System.out.println("❌ That seat is already available!");
                    }
                } else {
                    System.out.println("❌ Invalid row or column selection!");
                }

            } else if (choice == 3) {
                // Count available seats
                int availableCount = 0;
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                        if (seats[i][j] == 'O') {
                            availableCount++;
                        }
                    }
                }
                System.out.println("📊 Available seats remaining: " + availableCount + " out of " + (seats.length * seats[0].length));

            } else if (choice == 4) {
                System.out.println("Thank you for using the Airline Booking System. Safe travels!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}
