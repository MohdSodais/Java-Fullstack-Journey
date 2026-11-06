package Days;

import java.util.Scanner;

public class Day22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parallel arrays for candidates and their vote counts
        String[] candidates = {"Alice", "Bob", "Charlie", "Diana"};
        int[] voteCounts = new int[candidates.length];
        int totalVotes = 0;

        boolean running = true;
        System.out.println("=== COMMUNITY VOTING SYSTEM ===");

        while (running) {
            System.out.println("\n----------------------------------");
            System.out.println("🗳️ Current Standings (Total Votes: " + totalVotes + "):");

            // Display standings with dynamic percentage calculations
            for (int i = 0; i < candidates.length; i++) {
                double percentage = (totalVotes == 0) ? 0.0 : ((double) voteCounts[i] / totalVotes) * 100;
                System.out.printf("%d. %s — %d votes (%.1f%%)\n", (i + 1), candidates[i], voteCounts[i], percentage);
            }

            System.out.println("\n1. Cast a Vote");
            System.out.println("2. Declare Winner");
            System.out.println("3. Reset Poll");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Cast vote
                System.out.println("\nSelect a candidate to vote for:");
                for (int i = 0; i < candidates.length; i++) {
                    System.out.println((i + 1) + ". " + candidates[i]);
                }
                System.out.print("Enter choice (1-" + candidates.length + "): ");
                int voteIndex = scanner.nextInt() - 1;

                if (voteIndex >= 0 && voteIndex < candidates.length) {
                    voteCounts[voteIndex]++;
                    totalVotes++;
                    System.out.println("✅ Vote successfully cast for " + candidates[voteIndex] + "!");
                } else {
                    System.out.println("❌ Invalid candidate choice.");
                }

            } else if (choice == 2) {
                // Determine winner or check for ties
                if (totalVotes == 0) {
                    System.out.println("⚠️ No votes have been cast yet!");
                } else {
                    int maxVotes = voteCounts[0];
                    int winnerIndex = 0;
                    boolean tie = false;

                    for (int i = 1; i < voteCounts.length; i++) {
                        if (voteCounts[i] > maxVotes) {
                            maxVotes = voteCounts[i];
                            winnerIndex = i;
                            tie = false; // Reset tie flag if a higher score is found
                        } else if (voteCounts[i] == maxVotes) {
                            tie = true;  // Mark tie if another candidate matches top score
                        }
                    }

                    System.out.println("\n🏆 --- OFFICIAL POLL RESULTS ---");
                    if (tie) {
                        System.out.println("🤝 It's currently a tie with " + maxVotes + " votes!");
                    } else {
                        System.out.println("🎉 Winner: " + candidates[winnerIndex] + " with " + maxVotes + " votes!");
                    }
                }

            } else if (choice == 3) {
                // Reset poll data
                for (int i = 0; i < voteCounts.length; i++) {
                    voteCounts[i] = 0;
                }
                totalVotes = 0;
                System.out.println("⚠️ Poll has been successfully reset.");

            } else if (choice == 4) {
                System.out.println("Exiting Voting System. Goodbye!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}
