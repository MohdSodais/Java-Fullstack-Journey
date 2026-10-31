package Days;

import java.util.Scanner;

public class Day17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 2D Array representing the 3x3 game board
        char[][] board = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };

        char currentPlayer = 'X';
        int moves = 0;
        boolean gameWon = false;

        System.out.println("=== MINI TIC-TAC-TOE ===");

        while (moves < 9 && !gameWon) {
            // 1. Display the board grid
            System.out.println("\n-------------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println("\n-------------");
            }

            // 2. Get player move
            System.out.print("Player " + currentPlayer + ", choose a slot (1-9): ");
            int slot = scanner.nextInt();

            if (slot < 1 || slot > 9) {
                System.out.println("❌ Invalid slot! Choose a number between 1 and 9.");
                continue;
            }

            // Map 1-9 to 2D array row and column indices
            int row = (slot - 1) / 3;
            int col = (slot - 1) % 3;

            // Check if slot is already taken ('X' or 'O')
            if (board[row][col] == 'X' || board[row][col] == 'O') {
                System.out.println("❌ That slot is already taken! Try another.");
                continue;
            }

            // Place marker
            board[row][col] = currentPlayer;
            moves++;

            // 3. Check for a win (Rows, Columns, Diagonals)
            if ((board[0][0] == currentPlayer && board[0][1] == currentPlayer && board[0][2] == currentPlayer) ||
                    (board[1][0] == currentPlayer && board[1][1] == currentPlayer && board[1][2] == currentPlayer) ||
                    (board[2][0] == currentPlayer && board[2][1] == currentPlayer && board[2][2] == currentPlayer) ||
                    (board[0][0] == currentPlayer && board[1][0] == currentPlayer && board[2][0] == currentPlayer) ||
                    (board[0][1] == currentPlayer && board[1][1] == currentPlayer && board[2][1] == currentPlayer) ||
                    (board[0][2] == currentPlayer && board[1][2] == currentPlayer && board[2][2] == currentPlayer) ||
                    (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                    (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {

                gameWon = true;

                // Final board display
                System.out.println("\n-------------");
                for (int i = 0; i < 3; i++) {
                    System.out.print("| ");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(board[i][j] + " | ");
                    }
                    System.out.println("\n-------------");
                }

                System.out.println("🎉 CONGRATULATIONS! Player " + currentPlayer + " wins!");
            } else if (moves == 9) {
                System.out.println("🤝 It's a draw!");
            } else {
                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }
}