package Days;

import java.util.Scanner;

public class Day21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxTasks = 5;
        String[] tasks = new String[maxTasks];
        boolean[] completed = new boolean[maxTasks];
        int taskCount = 0; // Tracks current number of active tasks

        boolean running = true;
        System.out.println("=== MINI TASK TRACKER (TO-DO LIST) ===");

        while (running) {
            System.out.println("\n----------------------------------");
            System.out.println("📋 Your Tasks (" + taskCount + "/" + maxTasks + "):");

            // Display all slots
            for (int i = 0; i < maxTasks; i++) {
                if (tasks[i] != null) {
                    String status = completed[i] ? "[X] Done" : "[ ] Pending";
                    System.out.println((i + 1) + ". " + status + " — " + tasks[i]);
                } else {
                    System.out.println((i + 1) + ". [ Empty Slot ]");
                }
            }

            System.out.println("\n1. Add a Task");
            System.out.println("2. Mark Task as Complete");
            System.out.println("3. Delete a Task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline character

            if (choice == 1) {
                if (taskCount >= maxTasks) {
                    System.out.println("❌ Task list is full! Delete a task to add a new one.");
                } else {
                    System.out.print("Enter task description: ");
                    String newTask = scanner.nextLine();

                    // Find the first available (null) slot in the array
                    for (int i = 0; i < maxTasks; i++) {
                        if (tasks[i] == null) {
                            tasks[i] = newTask;
                            completed[i] = false;
                            taskCount++;
                            System.out.println("✅ Task added successfully!");
                            break;
                        }
                    }
                }

            } else if (choice == 2) {
                System.out.print("Enter task number to mark complete (1-" + maxTasks + "): ");
                int index = scanner.nextInt() - 1;

                if (index >= 0 && index < maxTasks && tasks[index] != null) {
                    completed[index] = true;
                    System.out.println("✅ Great job! Task marked as completed.");
                } else {
                    System.out.println("❌ Invalid selection or empty slot.");
                }

            } else if (choice == 3) {
                System.out.print("Enter task number to delete (1-" + maxTasks + "): ");
                int index = scanner.nextInt() - 1;

                if (index >= 0 && index < maxTasks && tasks[index] != null) {
                    tasks[index] = null;      // Clear the task
                    completed[index] = false; // Reset status
                    taskCount--;
                    System.out.println("🗑️ Task deleted successfully!");
                } else {
                    System.out.println("❌ Invalid selection or empty slot.");
                }

            } else if (choice == 4) {
                System.out.println("Closing Task Manager. Have a productive day!");
                running = false;
            } else {
                System.out.println("❌ Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}
