package Days;

import java.util.Random;
import java.util.Scanner;

public class Day26 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int size = 5;
        char[][] grid = new char[size][size];

        // Randomly place the player
        int playerRow = random.nextInt(size);
        int playerCol = random.nextInt(size);

        // Randomly place the treasure (guaranteeing it's not on top of the player)
        int treasureRow, treasureCol;
        do {
            treasureRow = random.nextInt(size);
            treasureCol = random.nextInt(size);
        } while (treasureRow == playerRow && treasureCol == playerCol);

        boolean foundTreasure = false;
        int steps = 0;

        System.out.println("=== 2D GRID TREASURE HUNT ===");
        System.out.println("Find the hidden treasure! Use W (Up), S (Down), A (Left), D (Right).");

        while (!foundTreasure) {
            // Display grid view (hiding the treasure until found)
            System.out.println("\n--------------------");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == playerRow && j == playerCol) {
                        System.out.print(" @ "); // Player marker
                    } else {
                        System.out.print(" ~  "); // Unexplored map fog
                    }
                }
                System.out.println();
            }
            System.out.println("--------------------");
            System.out.println("Steps taken: " + steps);

            // Proximity hint system (Manhattan distance)
            int distance = Math.abs(playerRow - treasureRow) + Math.abs(playerCol - treasureCol);
            if (distance <= 1) {
                System.out.println(" HOT! You smell gold nearby!");
            } else if (distance <= 2) {
                System.out.println(" Warm. Getting closer...");
            } else {
                System.out.println(" Cold. Keep searching.");
            }

            // Get player movement input
            System.out.print("Move (W/A/S/D): ");
            char move = scanner.next().toUpperCase().charAt(0);

            int newRow = playerRow;
            int newCol = playerCol;

            if (move == 'W') newRow--;       // Move Up
            else if (move == 'S') newRow++;  // Move Down
            else if (move == 'A') newCol--;  // Move Left
            else if (move == 'D') newCol++;  // Move Right
            else {
                System.out.println("❌ Invalid command! Use W, A, S, or D.");
                continue;
            }

            // Boundary check: ensure player doesn't fall off the 5x5 grid
            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                playerRow = newRow;
                playerCol = newCol;
                steps++;

                // Check win condition
                if (playerRow == treasureRow && playerCol == treasureCol) {
                    foundTreasure = true;
                }
            } else {
                System.out.println("⚠️ Ouch! You hit the edge of the map. Try another direction.");
            }
        }

        System.out.println("\n🎉 CONGRATULATIONS! You found the treasure in " + steps + " steps!");
        scanner.close();
    }
}
