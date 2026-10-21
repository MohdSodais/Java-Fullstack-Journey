package Days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
    public static void main(String[] args) {
        // 1. Create a list of daily study hours logged over a week
        List<Integer> studyHours = Arrays.asList(4, 6, 2, 8, 5, 3, 7);

        System.out.println("=== ALL LOGGED HOURS ===");
        System.out.println(studyHours);

        // 2. FILTER STREAM: Find days where study hours were >= 5
        // Using a Lambda expression: (hours -> hours >= 5)
        List<Integer> productiveDays = studyHours.stream()
                .filter(hours -> hours >= 5)
                .collect(Collectors.toList());

        System.out.println("\n=== PRODUCTIVE DAYS (>= 5 hrs) ===");
        System.out.println(productiveDays);

        // 3. MAP STREAM: Multiply each value by 2 (e.g., calculating double points)
        List<Integer> doubledHours = studyHours.stream()
                .map(hours -> hours * 2)
                .collect(Collectors.toList());

        System.out.println("\n=== DOUBLED HOURS (Map) ===");
        System.out.println(doubledHours);

        // 4. REDUCE STREAM: Calculate total hours studied in the week
        int totalHours = studyHours.stream()
                .reduce(0, (subtotal, element) -> subtotal + element);

        System.out.println("\nTotal Weekly Study Hours: " + totalHours);
    }
}
