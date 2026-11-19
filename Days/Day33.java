package Days;

import java.util.Scanner;

public class Day33 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parallel arrays representing the library catalog
        String[] titles = {
                "1984",
                "To Kill a Mockingbird",
                "The Great Gatsby",
                "Dune",
                "The Hobbit"
        };
        String[] authors = {
                "George Orwell",
                "Harper Lee",
                "F. Scott Fitzgerald",
                "Frank Herbert",
                "J.R.R. Tolkien"
        };
        boolean[] isCheckedOut = {false, false, true, false, false}; // The Great Gatsby starts checked out
        String[] borrowerNames = {"", "", "Sarah Jenkins", "", ""};

        boolean running = true;
        System.out.println("=== JAVA COMMUNITY LIBRARY CATALOG ===");

        while (running) {
            System.out.println("\n-------------------------------------------------------------------------");
            System.out.println("ID | Title                  | Author               | Status");
            System.out.println("-------------------------------------------------------------------------");
            for (int i = 0; i < titles.length; i++) {
                String status = isCheckedOut[i] ? ("Checked Out (" + borrowerNames[i] + ")") : "Available";
                System.out.printf("%-2d | %-22s | %-20s | %s\n",
                        (i + 1), titles[i], authors[i], status);
            }
            System.out.println("-------------------------------------------------------------------------");

            System.out.println("1. Borrow a Book");
            System.out.println("2. Return a Book");
            System.out.println("3. Search Books by Keyword");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            if (choice == 1) {
                System.out.print("Enter Book ID to borrow (1-" + titles.length + "): ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline

                if (index >= 0 && index < titles.length) {
                    if (!isCheckedOut[index]) {
                        System.out.print("Enter your name: ");
                        String borrower = scanner.nextLine();
                        isCheckedOut[index] = true;
                        borrowerNames[index] = borrower;
                        System.out.println(" '" + titles[index] + "' has been checked out to " + borrower + ".");
                    } else {
                        System.out.println(" Sorry, '" + titles[index] + "' is currently checked out by " + borrowerNames[index] + ".");
                    }
                } else {
                    System.out.println(" Invalid Book ID.");
                }

            } else if (choice == 2) {
                System.out.print("Enter Book ID to return (1-" + titles.length + "): ");
                int index = scanner.nextInt() - 1;

                if (index >= 0 && index < titles.length) {
                    if (isCheckedOut[index]) {
                        System.out.println(" Thank you, " + borrowerNames[index] + "! '" + titles[index] + "' has been returned.");
                        isCheckedOut[index] = false;
                        borrowerNames[index] = "";
                    } else {
                        System.out.println(" '" + titles[index] + "' is already on the shelf.");
                    }
                } else {
                    System.out.println(" Invalid Book ID.");
                }

            } else if (choice == 3) {
                System.out.print("Enter a title or author keyword to search: ");
                String keyword = scanner.nextLine().toLowerCase();
                boolean found = false;

                System.out.println("\n🔍 Search Results:");
                for (int i = 0; i < titles.length; i++) {
                    // Partial, case-insensitive matching across two parallel arrays
                    if (titles[i].toLowerCase().contains(keyword) || authors[i].toLowerCase().contains(keyword)) {
                        String availability = isCheckedOut[i] ? "Checked Out" : "Available";
                        System.out.printf("• [%d] %s by %s — (%s)\n", (i + 1), titles[i], authors[i], availability);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No books matched '" + keyword + "'.");
                }

            } else if (choice == 4) {
                System.out.println("Exiting Library System. Happy reading!");
                running = false;
            } else {
                System.out.println(" Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}
