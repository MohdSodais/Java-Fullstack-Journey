package Days;

import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        String learnerName = "Java Explorer";
        int targetHours = 5;
        boolean readyToLearn = true;

        System.out.println("=== USER PROFILE ===");
        System.out.println("Name: " + learnerName);
        System.out.println("Daily Study Goal: " + targetHours + " hours");
        System.out.println("Ready to code: " + readyToLearn);

        // 2. Interactive part: Take input from the terminal
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter your actual name: ");
        String actualName = scanner.nextLine();

        System.out.print("How many hours can you study today? ");
        int hours = scanner.nextInt();

        System.out.println("\nAwesome, " + actualName + "! Let's crush those " + hours + " hours of coding today.");

        scanner.close();
    }
}
