package Days;

class Task {
    // Instance variables (Attributes)
    String title;
    boolean isCompleted;

    // Constructor (Used to create objects)
    public Task(String taskTitle) {
        title = taskTitle;
        isCompleted = false; // Default state
    }

    // Method to mark task as done
    public void completeTask() {
        isCompleted = true;
    }

    // Method to display task details
    public void displayTask() {
        String status = isCompleted ? "✅ Completed" : "⏳ Pending";
        System.out.println("Task: " + title + " | Status: " + status);
    }
}

// 2. Main execution class
public class Day5 {
    public static void main(String[] args) {
        System.out.println("--- TASK MANAGER (OOPs) ---");

        // Creating Objects (Instances of the Task class)
        Task task1 = new Task("Learn Java Basics");
        Task task2 = new Task("Master Git & GitHub");
        Task task3 = new Task("Build Spring Boot API");

        // Displaying initial state
        task1.displayTask();
        task2.displayTask();
        task3.displayTask();

        // Completing the first two tasks
        System.out.println("\n-- Updating Progress --");
        task1.completeTask();
        task2.completeTask();

        // Displaying updated state
        task1.displayTask();
        task2.displayTask();
        task3.displayTask();
    }
}
