package Days;

import java.util.Scanner;

public class Day18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // The secret word to guess, converted to a character array
        char[] secretWord = {'j', 'a', 'v', 'a'};

        // A parallel array to display current progress (starts as underscores)
        char[] displayBoard = {'_', '_', '_', '_'};

        int attemptsLeft = 6;
        int correctlyGuessed = 0;
        boolean gameWon = false;

        System.out.println("=== MINI WORD GUESSING GAME ===");
        System.out.println("Can you guess the 4-letter programming language?");

        while (attemptsLeft > 0 && !gameWon) {
            System.out.println("\n----------------------------------");
            System.out.print("Word: ");
            for (char c : displayBoard) {
                System.out.print(c + " ");
            }
            System.out.println("\nAttempts left: " + attemptsLeft);

            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            boolean foundMatch = false;

            // Check if the guessed letter exists in the secret word
            for (int i = 0; i < secretWord.length; i++) {
                // If match found and it hasn't been guessed yet
                if (secretWord[i] == guess && displayBoard[i] == '_') {
                    displayBoard[i] = guess; // Reveal letter in display board
                    correctlyGuessed++;
                    foundMatch = true;
                }
            }

            if (foundMatch) {
                System.out.println("✅ Good job! '" + guess + "' is in the word.");
            } else {
                System.out.println("❌ Sorry, '" + guess + "' is not in the word (or already guessed).");
                attemptsLeft--;
            }

            // Check if all letters have been revealed
            if (correctlyGuessed == secretWord.length) {
                gameWon = true;
            }
        }

        // Final Game Outcome
        System.out.println("\n----------------------------------");
        if (gameWon) {
            System.out.print("🎉 CONGRATULATIONS! You guessed the word: ");
            for (char c : displayBoard) {
                System.out.print(c);
            }
            System.out.println();
        } else {
            System.out.println("💀 Game Over! You ran out of attempts.");
            System.out.print("The secret word was: ");
            for (char c : secretWord) {
                System.out.print(c);
            }
            System.out.println();
        }

        scanner.close();
    }
}