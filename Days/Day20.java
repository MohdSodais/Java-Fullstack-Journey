package Days;

import java.util.Scanner;

public class Day20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Fixed leaderboard holding top 5 scores
        int[] scores = {1200, 950, 800, 650, 500};

        boolean running = true;

        System.out.println("=== ARCADE HIGH SCORE LEADERBOARD ===");

        while (running) {
            System.out.println("\n----------------------------------");
            System.out.println("🏆 Current Leaderboard (Top 5):");
            for (int i = 0; i < scores.length; i++) {
                System.out.println((i + 1) + ". " + scores[i] + " pts");
            }

            System.out.println("\n1. Submit a New Score");
            System.out.println("2. Reset Leaderboard");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter your game score: ");
                int newScore = scanner.nextInt();

                // Check if score qualifies to beat the lowest score on the board
                if (newScore > scores[scores.length - 1]) {
                    // Replace the lowest score with the new score
                    scores[scores.length - 1] = newScore;

                    // Sort the array in descending order using Bubble Sort
                    for (int i = 0; i < scores.length - 1; i++) {
                        for (int j = 0; j < scores.length - i - 1; j++) {
                            if (scores[j] < scores[j + 1]) {
                                // Swap elements to put the larger score first
                                int temp = scores[j];
                                scores[j] = scores[j + 1];
                                scores[j + 1] = temp;
                            }
                        }
                    }
                    System.out.println("🎉 Awesome! Your score made it onto the leaderboard!");
                } else {
                    System.out.println("❌ Score is too low to make the top 5.");
                }

            } else if (choice == 2) {
                // Reset board back to zeros
                scores = new int[]{0, 0, 0, 0, 0};
                System.out.println("⚠️ Leaderboard has been reset.");

            } else if (choice == 3) {
                System.out.println("Thanks for playing! Goodbye!");
                running = false;
            } else {
                System.out.println("❌ Invalid choice. Please choose 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}