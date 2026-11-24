package Days;

import java.util.Scanner;

public class Day38 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = 6;
        int cols = 7;
        char[][] board = new char[rows][cols];

        // Initialize board with empty dots
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = '.';
            }
        }

        char currentPlayer = 'R'; // 'R' = Red, 'Y' = Yellow
        int totalMoves = 0;
        int maxMoves = rows * cols;
        boolean gameWon = false;

        System.out.println("=== CONNECT FOUR ===");
        System.out.println("Players take turns dropping discs ('R' and 'Y') into columns 1-7.");

        while (!gameWon && totalMoves < maxMoves) {
            printBoard(board);

            System.out.print("\nPlayer " + currentPlayer + " — Choose a column (1-7): ");
            int col = scanner.nextInt() - 1;

            // Validate column index
            if (col < 0 || col >= cols) {
                System.out.println(" Invalid column! Choose between 1 and 7.");
                continue;
            }

            // Gravity drop: scan from the bottom row up to find the first open slot
            int landedRow = -1;
            for (int r = rows - 1; r >= 0; r--) {
                if (board[r][col] == '.') {
                    board[r][col] = currentPlayer;
                    landedRow = r;
                    break;
                }
            }

            if (landedRow == -1) {
                System.out.println(" Column " + (col + 1) + " is full! Pick another column.");
                continue;
            }

            totalMoves++;

            // Check if this drop created 4 in a row
            if (checkWin(board, landedRow, col, currentPlayer)) {
                printBoard(board);
                System.out.println("\n VICTORY! Player " + currentPlayer + " connects four!");
                gameWon = true;
            } else {
                // Switch active player
                currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
            }
        }

        if (!gameWon) {
            printBoard(board);
            System.out.println("\n DRAW! The board is completely full.");
        }

        scanner.close();
    }

    // Renders the 6x7 Connect Four grid
    public static void printBoard(char[][] board) {
        System.out.println("\n  1   2   3   4   5   6   7");
        System.out.println("-----------------------------");
        for (int r = 0; r < board.length; r++) {
            System.out.print("| ");
            for (int c = 0; c < board[r].length; c++) {
                System.out.print(board[r][c] + " | ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }

    // Checks horizontal, vertical, and both diagonal lines passing through (row, col)
    public static boolean checkWin(char[][] board, int row, int col, char disc) {
        // Direction pairs: {dRow, dCol} for Horizontal, Vertical, Diagonal \, Diagonal /
        int[][] directions = {
                {0, 1},  // Horizontal
                {1, 0},  // Vertical
                {1, 1},  // Diagonal down-right
                {1, -1}  // Diagonal down-left
        };

        for (int[] dir : directions) {
            int count = 1; // Count the newly dropped disc itself
            count += countDirection(board, row, col, dir[0], dir[1], disc);
            count += countDirection(board, row, col, -dir[0], -dir[1], disc);

            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    // Counts consecutive matching discs starting from (row, col) moving by (dRow, dCol)
    public static int countDirection(char[][] board, int row, int col, int dRow, int dCol, char disc) {
        int r = row + dRow;
        int c = col + dCol;
        int streak = 0;

        while (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == disc) {
            streak++;
            r += dRow;
            c += dCol;
        }
        return streak;
    }
}