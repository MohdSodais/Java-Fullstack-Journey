package Days;

import java.util.Scanner;

public class Day27 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parallel arrays for accounts, PINs, and balances
        String[] accountNumbers = {"101-ABC", "202-XYZ", "303-DEF"};
        int[] pins = {1234, 5678, 9999};
        double[] balances = {500.50, 1250.00, 300.25};

        boolean systemRunning = true;
        System.out.println("=== WELCOME TO JAVA BANK ATM ===");

        while (systemRunning) {
            System.out.println("\n----------------------------------");
            System.out.print("Enter Account Number (or type 'exit'): ");
            String inputAcc = scanner.nextLine();

            if (inputAcc.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using Java Bank. Goodbye!");
                break;
            }

            System.out.print("Enter 4-digit PIN: ");
            int inputPin = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline character

            // Search parallel arrays for a matching account and PIN
            int userIndex = -1;
            for (int i = 0; i < accountNumbers.length; i++) {
                if (accountNumbers[i].equals(inputAcc) && pins[i] == inputPin) {
                    userIndex = i;
                    break;
                }
            }

            // Authentication check
            if (userIndex != -1) {
                System.out.println("\n✅ Login Successful! Welcome, Account #" + accountNumbers[userIndex]);
                boolean sessionActive = true;

                while (sessionActive) {
                    System.out.println("\n--- ATM MENU ---");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit Money");
                    System.out.println("3. Withdraw Money");
                    System.out.println("4. Logout");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();

                    if (choice == 1) {
                        System.out.printf("💰 Current Balance: $%.2f\n", balances[userIndex]);

                    } else if (choice == 2) {
                        System.out.print("Enter amount to deposit: $");
                        double deposit = scanner.nextDouble();
                        if (deposit > 0) {
                            balances[userIndex] += deposit; // Update array value
                            System.out.printf("✅ Successfully deposited $%.2f. New Balance: $%.2f\n", deposit, balances[userIndex]);
                        } else {
                            System.out.println("❌ Invalid deposit amount.");
                        }

                    } else if (choice == 3) {
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawal = scanner.nextDouble();
                        if (withdrawal > 0 && withdrawal <= balances[userIndex]) {
                            balances[userIndex] -= withdrawal; // Update array value
                            System.out.printf("✅ Successfully withdrew $%.2f. Remaining Balance: $%.2f\n", withdrawal, balances[userIndex]);
                        } else {
                            System.out.println("❌ Insufficient funds or invalid amount!");
                        }

                    } else if (choice == 4) {
                        System.out.println("🔒 Logged out successfully.");
                        sessionActive = false;
                    } else {
                        System.out.println("❌ Invalid option. Choose between 1 and 4.");
                    }
                }
                scanner.nextLine(); // Clear buffer before next login prompt
            } else {
                System.out.println("❌ Authentication Failed: Invalid account number or PIN.");
            }
        }

        scanner.close();
    }
}