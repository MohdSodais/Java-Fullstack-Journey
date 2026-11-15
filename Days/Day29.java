package Days;

import java.util.Random;
import java.util.Scanner;

public class Day29 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int size = 4; // 4x4 grid
        char[][] displayBoard = new char[size][size];
        boolean[][] shipBoard = new boolean[size][size];

        // Initialize display board with fog ('~') and place 3 hidden ship parts
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                displayBoard[i][j] = '~';
            }
        }

        // Randomly place 3 battleship segments
        int shipsPlaced = 0;
        while (shipsPlaced < 3) {
            int r = random.nextInt(size);
            int c = random.nextInt(size);
            if (!shipBoard[r][c]) {
                shipBoard[r][c] = true; // True means a ship is here
                shipsPlaced++;
            }
        }

        int torpedoes = 5;
        int hits = 0;
        boolean gameWon = false;

        System.out.println("=== MINI BATTLESHIP TARGET STRIKE ===" );
        System.out.println("Enemy ships detected! You have " + torpedoes + " torpedoes to sink 3 ship targets.");

        while (torpedoes > 0 && !gameWon) {
            // Display current grid
            System.out.println("\n    Col 1  Col 2  Col 3  Col 4");
            for (int i = 0; i < size; i++) {
                System.out.print("Row " + (i + 1) + " ");
                for (int j = 0; j < size; j++) {
                    System.out.print("   " + displayBoard[i][j] + "   ");
                }
                System.out.println();
            }

            System.out.println("\nTorpedoes left: " + torpedoes);
            System.out.print("Enter target row (1-4): ");
            int row = scanner.nextInt() - 1;
            System.out.print("Enter target col (1-4): ");
            int col = scanner.nextInt() - 1;

            // Validate coordinates
            if (row < 0 || row >= size || col < 0 || col >= size) {
                System.out.println(" Out of bounds! Choose coordinates between 1 and 4.");
                continue;
            }

            // Check if already fired at this spot
            if (displayBoard[row][col] == 'X' || displayBoard[row][col] == 'O') {
                System.out.println(" You already fired at coordinates (" + (row + 1) + ", " + (col + 1) + ")! Try a new spot.");
                continue;
            }

            torpedoes--;

            // Check hit or miss
            if (shipBoard[row][col]) {
                System.out.println(" DIRECT HIT! You struck an enemy ship!");
                displayBoard[row][col] = 'X'; // Mark hit on display board
                hits++;
                if (hits == 3) {
                    gameWon = true;
                }
            } else {
                System.out.println(" MISS! Water splashing into the open sea.");
                displayBoard[row][col] = 'O'; // Mark miss on display board
            }
        }

        // Final game outcome display
        System.out.println("\n----------------------------------");
        if (gameWon) {
            System.out.println(" VICTORY! You successfully sank all enemy ships!");
        } else {
            System.out.println(" DEFEAT! You ran out of torpedoes and the fleet escaped.");
            // Reveal where the ships actually were
            System.out.println("Actual ship locations were marked with 'S':");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (shipBoard[i][j]) {
                        System.out.print(" [S] ");
                    } else {
                        System.out.print(" [~] ");
                    }
                }
                System.out.println();
            }
        }

        scanner.close();
    }
}
