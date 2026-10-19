package Days;

import java.util.ArrayList;
import java.util.Scanner;

// A simple Task class
class TaskItem {
    String title;
    boolean isDone;

    public TaskItem(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void display() {
        String status = isDone ? "[X]" : "[ ]";
        System.out.println(status + " " + title);
    }
}

public class Day6 {
    public static void main(String[] args) {
        // 1. Create an ArrayList to store multiple TaskItem objects
        ArrayList<TaskItem> taskList = new ArrayList<>();

        // Adding some initial tasks
        taskList.add(new TaskItem("Learn Java Syntax"));
        taskList.add(new TaskItem("Understand OOP Concepts"));
        taskList.add(new TaskItem("Master Java Collections"));

        System.out.println("=== INITIAL TASK LIST ===");
        // Using a for-each loop to display items
        for (TaskItem task : taskList) {
            task.display();
        }

        // 2. Dynamically add a new task from code
        taskList.add(new TaskItem("Build Spring Boot API"));

        // Mark the first task as done
        taskList.get(0).isDone = true;

        System.out.println("\n=== UPDATED TASK LIST ===");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ". ");
            taskList.get(i).display();
        }
    }
}