package Days;

import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int days = 7;
        double[] expenses = new double[days];

        System.out.println("=== MINI EXPENSE TRACKER (7 Days) ===");

        // 1. Input: Collect expenses for each day
        for (int i = 0; i < days; i++) {
            System.out.print("Enter expense for Day " + (i + 1) + ": $");
            expenses[i] = scanner.nextDouble();
        }

        System.out.println("\n----------------------------------");
        System.out.println("PROCESSING YOUR EXPENSES...");
        System.out.println("----------------------------------");

        // 2. Display all expenses
        System.out.print("Your expenses: ");
        for (int i = 0; i < expenses.length; i++) {
            System.out.print("$" + expenses[i] + (i < expenses.length - 1 ? ", " : ""));
        }
        System.out.println();

        // 3. Calculate Total & Average
        double total = 0;
        for (double exp : expenses) {
            total += exp;
        }
        double average = total / days;
        System.out.printf("Total Spent: $%.2f\n", total);
        System.out.printf("Average Daily Spend: $%.2f\n", average);

        // 4. Find Highest Expense (Max)
        double maxExpense = expenses[0];
        int maxDay = 1;
        for (int i = 1; i < expenses.length; i++) {
            if (expenses[i] > maxExpense) {
                maxExpense = expenses[i];
                maxDay = i + 1;
            }
        }
        System.out.printf("Highest Expense: $%.2f on Day %d\n", maxExpense, maxDay);

        // 5. Count days over a specific budget (e.g., $50)
        double budgetThreshold = 50.0;
        int overBudgetCount = 0;
        for (double exp : expenses) {
            if (exp > budgetThreshold) {
                overBudgetCount++;
            }
        }
        System.out.println("Days spent over $" + budgetThreshold + ": " + overBudgetCount + " day(s)");

        scanner.close();
    }
}

