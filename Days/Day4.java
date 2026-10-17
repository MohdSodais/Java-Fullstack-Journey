package Days;

public class Day4 {
    // 1. A method that calculates weekly study hours (takes inputs, returns a value)
    public static int calculateTotalHours(int dailyHours, int days) {
        return dailyHours * days;
    }

    // 2. A method that prints a motivational message (does not return anything, uses 'void')
    public static void printMotivation(int totalHours) {
        if (totalHours >= 25) {
            System.out.println("🔥 Incredible! You are on track to master full-stack development.");
        } else {
            System.out.println("👍 Good progress! Consistency is key.");
        }
    }

    public static void main(String[] args) {
        int hoursPerDay = 4;
        int activeDays = 6;

        // Calling our method to calculate total hours
        int totalWeeklyHours = calculateTotalHours(hoursPerDay, activeDays);

        System.out.println("--- STUDY HELPER ---");
        System.out.println("Studying " + hoursPerDay + " hours for " + activeDays + " days.");
        System.out.println("Total Expected Hours: " + totalWeeklyHours + " hours.");

        // Calling our method to print motivation
        printMotivation(totalWeeklyHours);
    }
}
