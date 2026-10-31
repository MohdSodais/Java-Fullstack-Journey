package Days;

import java.util.Scanner;

public class Day16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parallel arrays to store item names and their corresponding stock quantities
        String[] products = {"Apples", "Bananas", "Milk", "Bread", "Eggs"};
        int[] stock = {15, 8, 4, 2, 30};
        int lowStockThreshold = 5;

        boolean running = true;

        while (running) {
            System.out.println("\n=== STORE INVENTORY MANAGER ===");
            System.out.println("1. View All Inventory");
            System.out.println("2. Restock Product");
            System.out.println("3. Sell Product");
            System.out.println("4. Check Low Stock Alerts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // View Inventory
                System.out.println("\nCurrent Inventory:");
                System.out.println("--------------------------------");
                for (int i = 0; i < products.length; i++) {
                    System.out.println((i + 1) + ". " + products[i] + " — " + stock[i] + " units");
                }

            } else if (choice == 2) {
                // Restock
                System.out.println("\nSelect product to restock (1-" + products.length + "):");
                for (int i = 0; i < products.length; i++) {
                    System.out.println((i + 1) + ". " + products[i]);
                }
                System.out.print("Enter choice: ");
                int index = scanner.nextInt() - 1;

                if (index >= 0 && index < products.length) {
                    System.out.print("Enter quantity to add: ");
                    int qty = scanner.nextInt();
                    if (qty > 0) {
                        stock[index] += qty; // Update array value
                        System.out.println("✅ Success! Added " + qty + " to " + products[index] + ". New stock: " + stock[index]);
                    } else {
                        System.out.println("❌ Quantity must be greater than zero.");
                    }
                } else {
                    System.out.println("❌ Invalid product selection.");
                }

            } else if (choice == 3) {
                // Sell / Decrease Stock
                System.out.println("\nSelect product to sell (1-" + products.length + "):");
                for (int i = 0; i < products.length; i++) {
                    System.out.println((i + 1) + ". " + products[i] + " (Stock: " + stock[i] + ")");
                }
                System.out.print("Enter choice: ");
                int index = scanner.nextInt() - 1;

                if (index >= 0 && index < products.length) {
                    System.out.print("Enter quantity to sell: ");
                    int qty = scanner.nextInt();
                    if (qty > 0 && qty <= stock[index]) {
                        stock[index] -= qty; // Update array value
                        System.out.println("✅ Sold " + qty + " " + products[index] + "(s). Remaining stock: " + stock[index]);
                    } else {
                        System.out.println("❌ Invalid quantity or not enough stock available!");
                    }
                } else {
                    System.out.println("❌ Invalid product selection.");
                }

            } else if (choice == 4) {
                // Low Stock Warning Filter
                System.out.println("\n--- LOW STOCK ALERTS (< " + lowStockThreshold + " units) ---");
                boolean alertFound = false;
                for (int i = 0; i < products.length; i++) {
                    if (stock[i] < lowStockThreshold) {
                        System.out.println("⚠️ " + products[i] + " is running low! Only " + stock[i] + " left.");
                        alertFound = true;
                    }
                }
                if (!alertFound) {
                    System.out.println("👍 All items are well-stocked!");
                }

            } else if (choice == 5) {
                System.out.println("Exiting Inventory Manager. Goodbye!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Please choose between 1 and 5.");
            }
         }

        scanner.close();
    }
}
