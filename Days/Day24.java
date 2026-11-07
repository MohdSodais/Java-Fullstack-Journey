package Days;

import java.util.Random;
import java.util.Scanner;

public class Day241 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Array holding song titles
        String[] playlist = {
                "Bohemian Rhapsody",
                "Stairway to Heaven",
                "Hotel California",
                "Imagine",
                "Smells Like Teen Spirit"
        };

        boolean running = true;
        System.out.println("=== MUSIC PLAYLIST & SHUFFLER ===");

        while (running) {
            System.out.println("\n----------------------------------");
            System.out.println("🎶 Current Playlist:");
            for (int i = 0; i < playlist.length; i++) {
                System.out.println((i + 1) + ". " + playlist[i]);
            }

            System.out.println("\n1. Play a Song");
            System.out.println("2. Shuffle Playlist");
            System.out.println("3. Replace a Song");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline character

            if (choice == 1) {
                // Play specific track
                System.out.print("Enter song number to play (1-" + playlist.length + "): ");
                int index = scanner.nextInt() - 1;

                if (index >= 0 && index < playlist.length) {
                    System.out.println("\n▶️ Now playing: " + playlist[index] + " 🎵 (Enjoy the vibe!)");
                } else {
                    System.out.println("❌ Invalid song selection.");
                }

            } else if (choice == 2) {
                // Shuffle playlist using the Fisher-Yates algorithm
                for (int i = playlist.length - 1; i > 0; i--) {
                    int j = random.nextInt(i + 1);
                    // Swap elements at index i and j
                    String temp = playlist[i];
                    playlist[i] = playlist[j];
                    playlist[j] = temp;
                }
                System.out.println("🔀 Playlist shuffled successfully!");

            } else if (choice == 3) {
                // Replace track title
                System.out.print("Enter song number to replace (1-" + playlist.length + "): ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline

                if (index >= 0 && index < playlist.length) {
                    System.out.print("Enter new song title: ");
                    String newSong = scanner.nextLine();
                    playlist[index] = newSong; // Update array value
                    System.out.println("✅ Song replaced successfully!");
                } else {
                    System.out.println("❌ Invalid song selection.");
                }

            } else if (choice == 4) {
                System.out.println("Exiting Playlist Manager. Enjoy your music!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}