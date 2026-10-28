package Days;

import java.util.Scanner;

public class Day15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalSeats = 10;
        int[] seats = new int[totalSeats]; // 0 means Available, 1 means Booked

        boolean running = true;

        while (running) {
            System.out.println("\n=== CINEMA SEAT BOOKING SYSTEM ===");
            System.out.println("Current Seat Chart (0 = Available, [X] = Booked):");

            // Display seat numbers
            System.out.print("Seat:   ");
            for (int i = 0; i < totalSeats; i++) {
                System.out.print((i + 1) + "\t");
            }
            System.out.println();

            // Display seat statuses
            System.out.print("Status: ");
            for (int i = 0; i < totalSeats; i++) {
                if (seats[i] == 0) {
                    System.out.print("0\t");
                } else {
                    System.out.print("[X]\t");
                }
            }
            System.out.println("\n--------------------------------------------------");

            // Menu options
            System.out.println("1. Book a Seat");
            System.out.println("2. Check Available Seats Count");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter seat number to book (1-10): ");
                int seatNum = scanner.nextInt();

                // Validate range and check if already booked
                if (seatNum < 1 || seatNum > totalSeats) {
                    System.out.println("❌ Invalid seat number! Please choose between 1 and 10.");
                } else if (seats[seatNum - 1] == 1) {
                    System.out.println("❌ Seat " + seatNum + " is already booked! Choose another.");
                } else {
                    seats[seatNum - 1] = 1; // Update array state to Booked
                    System.out.println("Success! Seat " + seatNum + " has been successfully booked.");
                }

            } else if (choice == 2) {
                // Count how many seats are still 0 (available)
                int availableCount = 0;
                for (int status : seats) {
                    if (status == 0) {
                        availableCount++;
                    }
                }
                System.out.println("➡️ Total available seats: " + availableCount + " out of " + totalSeats);

            } else if (choice == 3) {
                System.out.println("Thank you for using the Cinema Booking System. Goodbye!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Please choose 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}
