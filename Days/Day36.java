package Days;

import java.util.Scanner;

public class Day36 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parallel arrays for the menu
        String[] menuItems = {"Margherita Pizza", "Veggie Burger", "Pasta Alfredo", "Caesar Salad", "Iced Lemon Tea"};
        double[] menuPrices = {12.50, 9.75, 11.00, 7.50, 3.25};

        int numTables = 4;
        // 2D Array: rows = tables (0 to 3), columns = quantities of each menu item (0 to 4)
        int[][] tableOrders = new int[numTables][menuItems.length];

        boolean running = true;
        System.out.println("=== JAVA BISTRO TABLE & ORDER SYSTEM ===");

        while (running) {
            System.out.println("\n-----------------------------------------");
            System.out.println("1. View Menu");
            System.out.println("2. Add Item to Table Order");
            System.out.println("3. View Current Order for a Table");
            System.out.println("4. Print Bill & Clear Table");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("\n--- BISTRO MENU ---");
                for (int i = 0; i < menuItems.length; i++) {
                    System.out.printf("%d. %-18s $%.2f\n", (i + 1), menuItems[i], menuPrices[i]);
                }

            } else if (choice == 2) {
                System.out.print("Enter Table Number (1-" + numTables + "): ");
                int tableIdx = scanner.nextInt() - 1;

                if (tableIdx < 0 || tableIdx >= numTables) {
                    System.out.println(" Invalid table number.");
                    continue;
                }

                System.out.print("Enter Menu Item Number (1-" + menuItems.length + "): ");
                int itemIdx = scanner.nextInt() - 1;

                if (itemIdx < 0 || itemIdx >= menuItems.length) {
                    System.out.println(" Invalid menu item.");
                    continue;
                }

                System.out.print("Enter quantity: ");
                int qty = scanner.nextInt();

                if (qty > 0) {
                    // Increment specific cell in the 2D array
                    tableOrders[tableIdx][itemIdx] += qty;
                    System.out.println(" Added " + qty + "x " + menuItems[itemIdx] + " to Table " + (tableIdx + 1) + ".");
                } else {
                    System.out.println(" Quantity must be at least 1.");
                }

            } else if (choice == 3) {
                System.out.print("Enter Table Number to view (1-" + numTables + "): ");
                int tableIdx = scanner.nextInt() - 1;

                if (tableIdx >= 0 && tableIdx < numTables) {
                    printTableSummary(tableIdx, tableOrders, menuItems, menuPrices);
                } else {
                    System.out.println(" Invalid table number.");
                }

            } else if (choice == 4) {
                System.out.print("Enter Table Number to close out (1-" + numTables + "): ");
                int tableIdx = scanner.nextInt() - 1;

                if (tableIdx >= 0 && tableIdx < numTables) {
                    double total = printTableSummary(tableIdx, tableOrders, menuItems, menuPrices);
                    if (total > 0) {
                        double tax = total * 0.08; // 8% sales tax
                        System.out.printf("Tax (8%%):             $%.2f\n", tax);
                        System.out.printf("GRAND TOTAL:          $%.2f\n", (total + tax));
                        System.out.println("=========================================");

                        // Reset all item quantities for this table row to 0
                        for (int j = 0; j < menuItems.length; j++) {
                            tableOrders[tableIdx][j] = 0;
                        }
                        System.out.println(" Table " + (tableIdx + 1) + " has been cleared for new guests.");
                    }
                } else {
                    System.out.println(" Invalid table number.");
                }

            } else if (choice == 5) {
                System.out.println("Closing Restaurant System. Good shift!");
                running = false;
            } else {
                System.out.println(" Invalid option. Choose between 1 and 5.");
            }
        }

        scanner.close();
    }

    // Helper method to print itemized orders for a single table row and return subtotal
    public static double printTableSummary(int tableIdx, int[][] orders, String[] items, double[] prices) {
        System.out.println("\n=== TABLE " + (tableIdx + 1) + " ORDER SUMMARY ===");
        double subtotal = 0;
        boolean hasItems = false;

        for (int j = 0; j < items.length; j++) {
            int qty = orders[tableIdx][j];
            if (qty > 0) {
                double lineTotal = qty * prices[j];
                subtotal += lineTotal;
                hasItems = true;
                System.out.printf("%-18s x%-2d   $%.2f\n", items[j], qty, lineTotal);
            }
        }

        if (!hasItems) {
            System.out.println("No items ordered yet for this table.");
        } else {
            System.out.println("-----------------------------------------");
            System.out.printf("Subtotal:             $%.2f\n", subtotal);
        }
        return subtotal;
    }
}
