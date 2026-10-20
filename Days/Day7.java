package Days;

import java.util.HashMap;
import java.util.Map;

public class Day7 {
    public static void main(String[] args) {
        // 1. Create a HashMap (Key: String student ID, Value: String student Name)
        HashMap<String, String> students = new HashMap<>();

        // 2. Add key-value pairs using .put()
        students.put("JAVA101", "Rahul Sharma");
        students.put("JAVA102", "Priya Patel");
        students.put("JAVA103", "Amit Kumar");

        System.out.println("=== STUDENT REGISTRY ===");

        // 3. Retrieve a specific value using its key
        String searchId = "JAVA102";
        System.out.println("Looking up ID " + searchId + ": " + students.get(searchId));

        // 4. Check if a key or value exists
        if (students.containsKey("JAVA101")) {
            System.out.println("JAVA101 is registered in the system.");
        }

        // 5. Loop through all key-value pairs in the HashMap
        System.out.println("\n--- All Registered Students ---");
        for (Map.Entry<String, String> entry : students.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " | Name: " + entry.getValue());
        }

        // 6. Remove an entry
        students.remove("JAVA103");
        System.out.println("\nTotal students after removal: " + students.size());
    }
}
