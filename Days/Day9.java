package Days;

public class Day9 {

    // 1. Find the Maximum Element
    public static int findMaximum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int maxVal = arr[0];
        for (int num : arr) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        return maxVal;
    }

    // 2. Find the Sum and Average
    public static void printSumAndAverage(int[] arr) {
        if (arr == null || arr.length == 0) return;

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        double average = (double) sum / arr.length;
        System.out.println("Sum: " + sum + ", Average: " + average);
    }

    // 3. Reverse an Array (In-place)
    public static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // Swap elements
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    // 4. Linear Search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return index if found
            }
        }
        return -1; // Return -1 if not found
    }

    // Main method to test all functions
    public static void main(String[] args) {
        int[] numbers = {12, 45, 2, 41, 89, 23};

        // 1. Test Maximum
        System.out.println("Maximum element: " + findMaximum(numbers));

        // 2. Test Sum and Average
        printSumAndAverage(numbers);

        // 3. Test Reverse
        reverseArray(numbers);
        System.out.print("Reversed array: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 4. Test Linear Search
        // Note: Array is currently reversed from the previous step,
        // let's search for 45 in the reversed array: {23, 89, 41, 2, 45, 12}
        int target = 45;
        int index = linearSearch(numbers, target);
        if (index != -1) {
            System.out.println("Element " + target + " found at index: " + index);
        } else {
            System.out.println("Element not found.");
        }
    }
}