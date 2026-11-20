package Days;

import java.util.Scanner;

public class Day34 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 2D Array representing the dungeon map
        // '#' = Wall, '.' = Floor, 'K' = Key, 'T' = Spike Trap, 'E' = Locked Exit
        char[][] dungeon = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '#', 'K', '.', '#'},
                {'#', '.', '#', '#', '.', 'T', '#'},
                {'#', '.', '.', '.', '.', '#', '#'},
                {'#', 'T', '#', '.', '#', '.', '#'},
                {'#', '.', '.', '.', '.', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        int playerRow = 1;
        int playerCol = 1;
        int health = 100;
        boolean hasKey = false;
        boolean escaped = false;

        System.out.println("=== 2D DUNGEON MAZE ESCAPE ===");
        System.out.println("Grab the Key (K) and reach the Exit (E). Watch out for Traps (T)!");
        System.out.println("Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)");

        while (!escaped && health > 0) {
            // Render the dungeon grid
            System.out.println("\n---------------------------");
            for (int r = 0; r < dungeon.length; r++) {
                for (int c = 0; c < dungeon[r].length; c++) {
                    if (r == playerRow && c == playerCol) {
                        System.out.print(" P "); // Render player at current coordinates
                    } else {
                        System.out.print(" " + dungeon[r][c] + " ");
                    }
                }
                System.out.println();
            }
            System.out.println("---------------------------");
            System.out.println(" Health: " + health + " |  Key: " + (hasKey ? "Collected" : "Missing"));

            System.out.print("Move (W/A/S/D): ");
            char move = scanner.next().toUpperCase().charAt(0);

            if (move == 'Q') {
                System.out.println("You abandoned the dungeon.");
                break;
            }

            int nextRow = playerRow;
            int nextCol = playerCol;

            if (move == 'W') nextRow--;
            else if (move == 'S') nextRow++;
            else if (move == 'A') nextCol--;
            else if (move == 'D') nextCol++;
            else {
                System.out.println(" Invalid input! Use W, A, S, or D.");
                continue;
            }

            // Inspect the target tile in the 2D array before moving
            char targetTile = dungeon[nextRow][nextCol];

            if (targetTile == '#') {
                System.out.println(" Bonk! That's a solid stone wall.");
            } else if (targetTile == 'E' && !hasKey) {
                System.out.println(" The exit door is locked! Find the Key (K) first.");
            } else {
                // Valid move: update player coordinates
                playerRow = nextRow;
                playerCol = nextCol;

                // Handle tile events
                if (targetTile == 'K') {
                    hasKey = true;
                    dungeon[playerRow][playerCol] = '.'; // Remove key from map once picked up
                    System.out.println(" You picked up the Dungeon Key!");
                } else if (targetTile == 'T') {
                    health -= 40;
                    System.out.println(" OUCH! You stepped on a spike trap (-40 HP)!");
                } else if (targetTile == 'E' && hasKey) {
                    escaped = true;
                }
            }
        }

        // Game over summary
        System.out.println("\n===========================");
        if (escaped) {
            System.out.println(" VICTORY! You unlocked the door and escaped with " + health + " HP!");
        } else if (health <= 0) {
            System.out.println(" GAME OVER! You ran out of health in the dungeon.");
        }

        scanner.close();
    }
}
