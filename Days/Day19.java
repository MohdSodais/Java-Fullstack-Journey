package Days;

import java.util.Random;
import java.util.Scanner;

public class Day19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Array of possible slot machine symbols
        String[] symbols = {"Cherry", "Lemon", "Bell", "Star", "Diamond"};
        int balance = 100; // Starting money

        System.out.println("=== CASINO MINI SLOT MACHINE ===");
        System.out.println("You start with $" + balance + "!");
        System.out.println("Cost per spin: $10. Match 2 for $20, match all 3 for $50!\n");

        while (balance >= 10) {
            System.out.println("----------------------------------------");
            System.out.println("Current Balance: $" + balance);
            System.out.print("Press ENTER to spin (or type 'quit' to exit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                break;
            }

            // Deduct cost of spin
            balance -= 10;

            // Array to store the 3 reels for this spin
            String[] spinResult = new String[3];

            // Randomly select 3 symbols from our symbols array
            for (int i = 0; i < 3; i++) {
                int randomIndex = random.nextInt(symbols.length); // 0 to symbols.length - 1
                spinResult[i] = symbols[randomIndex];
            }

            // Display the spin results
            System.out.println("\n🎲 SPINNING REELS...");
            System.out.println("╔═══════════════════════════════════════════╗");
            System.out.println("║  [ " + spinResult[0] + " ]  [ " + spinResult[1] + " ]  [ " + spinResult[2] + " ]  ║");
            System.out.println("╚═══════════════════════════════════════════╝");

            // Check win conditions
            if (spinResult[0].equals(spinResult[1]) && spinResult[1].equals(spinResult[2])) {
                System.out.println("🎉 JACKPOT! All three matched! You win $50!");
                balance += 50;
            } else if (spinResult[0].equals(spinResult[1]) || spinResult[1].equals(spinResult[2]) || spinResult[0].equals(spinResult[2])) {
                System.out.println("✨ Nice! Two symbols matched. You win $20!");
                balance += 20;
            } else {
                System.out.println("❌ No match. Better luck next time!");
            }

            System.out.println();
        }

        if (balance < 10) {
            System.out.println("💸 You ran out of money! Game Over.");
        } else {
            System.out.println("Thanks for playing! You walked away with: $" + balance);
        }

        scanner.close();
    }
}