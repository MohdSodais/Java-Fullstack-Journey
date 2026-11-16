package Days;

import java.util.Scanner;

public class Day30 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parallel arrays linking item names, prices, and stock quantities
        String[] items = {"Cola", "Chips", "Chocolate Bar", "Bottled Water", "Energy Drink", "Pepsi"};
        double[] prices = {1.50, 1.25, 1.75, 1.00, 2.50, 1.49};
        int[] stock = {5, 3, 4, 6, 2, 5};

        boolean running = true;
        System.out.println("=== JAVA VENDING MACHINE ===");

        while (running) {
            System.out.println("\n-----------------------------------------");
            System.out.println("Available Items:");
            for (int i = 0; i < items.length; i++) {
                String stockStatus = (stock[i] > 0) ? (stock[i] + " left") : "SOLD OUT";
                System.out.printf("%d. %-15s | $%.2f | (%s)\n", (i + 1), items[i], prices[i], stockStatus);
            }
            System.out.println((items.length + 1) + ". Exit Machine");
            System.out.println("-----------------------------------------");

            System.out.print("Select an item number: ");
            int choice = scanner.nextInt();

            if (choice == items.length + 1) {
                System.out.println("Thank you for using the Java Vending Machine!");
                running = false;
                continue;
            }

            int index = choice - 1;

            // Validate selection and check stock
            if (index < 0 || index >= items.length) {
                System.out.println(" Invalid selection. Please choose a valid item number.");
            } else if (stock[index] == 0) {
                System.out.println(" Sorry, " + items[index] + " is currently SOLD OUT!");
            } else {
                System.out.printf("You selected: %s ($%.2f)\n", items[index], prices[index]);
                System.out.print("Insert money: $");
                double moneyInserted = scanner.nextDouble();

                if (moneyInserted >= prices[index]) {
                    double change = moneyInserted - prices[index];
                    stock[index]--; // Decrease item stock in the array

                    System.out.println(" Dispensing " + items[index] + "...");
                    if (change > 0) {
                        System.out.printf(" Here is your change: $%.2f\n", change);
                    }
                } else {
                    System.out.printf(" Insufficient funds! You needed $%.2f more. Refunding $%.2f.\n",
                            (prices[index] - moneyInserted), moneyInserted);
                }
            }
        }

        scanner.close();
    }
}
