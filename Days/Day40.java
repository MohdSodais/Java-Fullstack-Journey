package Days;

import java.util.Random;
import java.util.Scanner;

public class Day40 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int size = 3; // 3x3 grid (tiles 1-8 plus 0 as empty space)
        int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        int emptyRow = 2;
        int emptyCol = 2;

        // Scramble the solved board with 40 valid random moves (guarantees solvability)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < 40; i++) {
            int[] dir = directions[random.nextInt(4)];
            int newR = emptyRow + dir[0];
            int newC = emptyCol + dir[1];

            if (newR >= 0 && newR < size && newC >= 0 && newC < size) {
                board[emptyRow][emptyCol] = board[newR][newC];
                board[newR][newC] = 0;
                emptyRow = newR;
                emptyCol = newC;
            }
        }

        int moves = 0;
        boolean solved = false;

        System.out.println("=== 8-TILE SLIDING PUZZLE ===");
        System.out.println("Arrange the numbers in order (1-8) with the empty space at the bottom right.");
        System.out.println("Type the number of a tile next to the empty space to slide it in!");

        while (!solved) {
            printBoard(board);

            if (isSolved(board)) {
                solved = true;
                break;
            }

            System.out.println("Moves taken: " + moves);
            System.out.print("Enter tile number to slide (1-8, or 0 to quit): ");
            int tile = scanner.nextInt();

            if (tile == 0) {
                System.out.println("Puzzle abandoned.");
                break;
            }

            if (tile < 1 || tile > 8) {
                System.out.println("❌ Invalid tile number! Choose between 1 and 8.");
                continue;
            }

            // Locate the chosen tile in the 2D array
            int[] tileCoords = findTile(board, tile);
            int tileRow = tileCoords[0];
            int tileCol = tileCoords[1];

            // Check if the chosen tile is orthogonally adjacent to the empty space
            int manhattanDistance = Math.abs(tileRow - emptyRow) + Math.abs(tileCol - emptyCol);

            if (manhattanDistance == 1) {
                // Swap the tile with the empty space (0)
                board[emptyRow][emptyCol] = tile;
                board[tileRow][tileCol] = 0;
                emptyRow = tileRow;
                emptyCol = tileCol;
                moves++;
            } else {
                System.out.println("⚠️ Tile " + tile + " is not next to the empty space!");
            }
        }

        if (solved) {
            System.out.println("\n🎉 SOLVED! You completed the puzzle in " + moves + " moves!");
        }

        scanner.close();
    }

    // Prints the 3x3 sliding board
    public static void printBoard(int[][] board) {
        System.out.println("\n+---+---+---+");
        for (int r = 0; r < board.length; r++) {
            System.out.print("| ");
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    System.out.print("  | "); // Render 0 as an empty slot
                } else {
                    System.out.print(board[r][c] + " | ");
                }
            }
            System.out.println("\n+---+---+---+");
        }
    }

    // Finds the {row, col} coordinates of a target number
    public static int[] findTile(int[][] board, int target) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == target) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }

    // Checks whether tiles 1-8 are in ascending order with 0 at [2][2]
    public static boolean isSolved(int[][] board) {
        int expected = 1;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (r == board.length - 1 && c == board[r].length - 1) {
                    return board[r][c] == 0;
                }
                if (board[r][c] != expected++) {
                    return false;
                }
            }
        }
        return true;
    }
}