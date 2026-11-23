package Days;

import java.util.Random;
import java.util.Scanner;

public class Day37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int rows = 4;
        int cols = 4;
        char[][] board = new char[rows][cols];
        boolean[][] revealed = new boolean[rows][cols];

        // 8 pairs of symbols (16 cards total)
        char[] symbols = {
                'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D',
                'E', 'E', 'F', 'F', 'G', 'G', 'H', 'H'
        };

        // Shuffle the 1D symbols array using Fisher-Yates
        for (int i = symbols.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = symbols[i];
            symbols[i] = symbols[j];
            symbols[j] = temp;
        }

        // Populate the 4x4 2D board from the shuffled 1D array
        int idx = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = symbols[idx++];
            }
        }

        int pairsFound = 0;
        int totalPairs = (rows * cols) / 2;
        int attempts = 0;

        System.out.println("=== 2D MEMORY CARD MATCHING GAME ===");
        System.out.println("Match all " + totalPairs + " pairs of letters on the 4x4 grid!");

        while (pairsFound < totalPairs) {
            printBoard(board, revealed, -1, -1, -1, -1);

            // Pick first card
            System.out.print("\nCard 1 — Enter row (1-4) and col (1-4) separated by space: ");
            int r1 = scanner.nextInt() - 1;
            int c1 = scanner.nextInt() - 1;

            if (!isValidPick(r1, c1, rows, cols, revealed)) {
                System.out.println(" Invalid or already matched card. Try again.");
                continue;
            }

            // Show board with Card 1 temporarily flipped
            printBoard(board, revealed, r1, c1, -1, -1);

            // Pick second card
            System.out.print("\nCard 2 — Enter row (1-4) and col (1-4) separated by space: ");
            int r2 = scanner.nextInt() - 1;
            int c2 = scanner.nextInt() - 1;

            if (!isValidPick(r2, c2, rows, cols, revealed) || (r1 == r2 && c1 == c2)) {
                System.out.println(" Invalid pick or same card selected twice. Turn forfeited.");
                continue;
            }

            attempts++;
            // Show board with both cards temporarily flipped
            printBoard(board, revealed, r1, c1, r2, c2);

            // Check if the two cards match
            if (board[r1][c1] == board[r2][c2]) {
                System.out.println(" MATCH! You found a pair of '" + board[r1][c1] + "'s!");
                revealed[r1][c1] = true;
                revealed[r2][c2] = true;
                pairsFound++;
            } else {
                System.out.println(" Not a match (" + board[r1][c1] + " vs " + board[r2][c2] + "). Memorize their spots!");
            }
        }

        System.out.println("\n=========================================");
        System.out.println(" CONGRATULATIONS! You cleared the board in " + attempts + " attempts!");
        scanner.close();
    }

    // Validates coordinates and ensures the card isn't already permanently revealed
    public static boolean isValidPick(int r, int c, int rows, int cols, boolean[][] revealed) {
        return r >= 0 && r < rows && c >= 0 && c < cols && !revealed[r][c];
    }

    // Renders the 2D board, showing permanently matched cards plus up to two temporary picks
    public static void printBoard(char[][] board, boolean[][] revealed, int tempR1, int tempC1, int tempR2, int tempC2) {
        System.out.println("\n    1  2  3  4");
        System.out.println("  ------------");
        for (int r = 0; r < board.length; r++) {
            System.out.print((r + 1) + " | ");
            for (int c = 0; c < board[r].length; c++) {
                boolean isTemp1 = (r == tempR1 && c == tempC1);
                boolean isTemp2 = (r == tempR2 && c == tempC2);

                if (revealed[r][c] || isTemp1 || isTemp2) {
                    System.out.print(board[r][c] + "  ");
                } else {
                    System.out.print("?  ");
                }
            }
            System.out.println();
        }
    }
}
