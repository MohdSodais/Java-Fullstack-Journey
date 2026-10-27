package Days;

import java.util.Scanner;

public class Day14 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numStudents = 5;
        int[] scores = new int[numStudents];

        System.out.println("=== QUIZ SCORE & GRADE ANALYZER ===");

        // 1. Input: Collect scores for each student
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter score for Student " + (i + 1) + " (0-100): ");
            scores[i] = scanner.nextInt();

            // Basic validation to keep scores realistic
            while (scores[i] < 0 || scores[i] > 100) {
                System.out.print("Invalid score. Enter a value between 0 and 100: ");
                scores[i] = scanner.nextInt();
            }
        }

        System.out.println("\n----------------------------------");
        System.out.println("CLASS PERFORMANCE REPORT");
        System.out.println("----------------------------------");

        // 2. Display all scores
        System.out.print("All Scores: ");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + (i < scores.length - 1 ? ", " : ""));
        }
        System.out.println();

        // 3. Calculate Total and Class Average
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = (double) sum / numStudents;
        System.out.printf("Class Average: %.2f\n", average);

        // 4. Find Highest and Lowest Scores
        int highScore = scores[0];
        int lowScore = scores[0];
        int topStudent = 1;
        int lowStudent = 1;

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > highScore) {
                highScore = scores[i];
                topStudent = i + 1;
            }
            if (scores[i] < lowScore) {
                lowScore = scores[i];
                lowStudent = i + 1;
            }
        }
        System.out.println("Highest Score: " + highScore + " (Student " + topStudent + ")");
        System.out.println("Lowest Score:  " + lowScore + " (Student " + lowStudent + ")");

        // 5. Pass/Fail Count (Passing score threshold = 50)
        int passingThreshold = 50;
        int passCount = 0;
        for (int score : scores) {
            if (score >= passingThreshold) {
                passCount++;
            }
        }
        int failCount = numStudents - passCount;
        System.out.println("Passed (>= 50): " + passCount + " student(s)");
        System.out.println("Failed (< 50):  " + failCount + " student(s)");

        scanner.close();
    }
}
