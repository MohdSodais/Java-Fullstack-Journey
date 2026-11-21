package Days;

import java.util.Random;
import java.util.Scanner;

public class Day35 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int size = 5;
        int totalMines = 4;
        boolean[][] mines = new boolean[size][size];
        char[][] visibleBoard = new char[size][size];

        // Fill visible board with hidden tiles ('?')
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                visibleBoard[r][c] = '?';
            }
        }

        // Randomly place mines
        int placed = 0;
        while (placed < totalMines) {
            int r = random.nextInt(size);
            int c = random.nextInt(size);
            if (!mines[r][c]) {
                mines[r][c] = true;
                placed++;
            }
        }

        int safeCellsRemaining = (size * size) - totalMines;
        boolean hitMine = false;

        System.out.println("=== MINI 2D MINESWEEPER ===");
        System.out.println("Clear all " + safeCellsRemaining + " safe tiles without hitting the " + totalMines + " hidden mines!");

        while (safeCellsRemaining > 0 && !hitMine) {
            // Print board with row/column headers
            System.out.println("\n    1  2  3  4  5");
            System.out.println("  ---------------");
            for (int r = 0; r < size; r++) {
                System.out.print((r + 1) + " | ");
                for (int c = 0; c < size; c++) {
                    System.out.print(visibleBoard[r][c] + "  ");
                }
                System.out.println();
            }

            System.out.println("\nSafe tiles left to clear: " + safeCellsRemaining);
            System.out.print("Enter row (1-5): ");
            int row = scanner.nextInt() - 1;
            System.out.print("Enter col (1-5): ");
            int col = scanner.nextInt() - 1;

            // Validate coordinates
            if (row < 0 || row >= size || col < 0 || col >= size) {
                System.out.println("❌ Out of bounds! Enter numbers between 1 and 5.");
                continue;
            }

            // Prevent selecting an already revealed cell
            if (visibleBoard[row][col] != '?') {
                System.out.println("⚠️ That tile is already revealed! Pick another.");
                continue;
            }

            // Check if player hit a mine
            if (mines[row][col]) {
                hitMine = true;
                visibleBoard[row][col] = '*';
            } else {
                // Count mines in the 8 surrounding cells
                int nearbyMines = countAdjacentMines(mines, row, col);
                visibleBoard[row][col] = (char) ('0' + nearbyMines);
                safeCellsRemaining--;
            }
        }

        // Reveal full board at game end
        System.out.println("\n=== FINAL BOARD ===");
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (mines[r][c]) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" " + visibleBoard[r][c] + " ");
                }
            }
            System.out.println();
        }

        if (hitMine) {
            System.out.println("\n💥 BOOM! You hit a mine. Game Over!");
        } else {
            System.out.println("\n🎉 VICTORY! You cleared every safe tile!");
        }

        scanner.close();
    }

    // Helper method: scans all 8 neighbors around (row, col) safely
    public static int countAdjacentMines(boolean[][] mines, int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue; // Skip the cell itself

                int checkRow = row + dr;
                int checkCol = col + dc;

                // Ensure neighbor coordinates stay inside array bounds
                if (checkRow >= 0 && checkRow < mines.length &&
                        checkCol >= 0 && checkCol < mines[0].length) {
                    if (mines[checkRow][checkCol]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
