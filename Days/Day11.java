package Days;

public class Day11 {
    //Count Occurrences of a Target Element
    public static int countOccurrences(int[] arr, int target) {
        int count = 0;
        for (int num : arr) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }

    //Move All Zeroes to the End
    public static void moveZeroesToEnd(int[] arr) {
        int insertPosition = 0;

        // Move all non-zero elements forward
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[insertPosition] = arr[i];
                insertPosition++;
            }
        }

        // Fill the remaining positions with zero
        while (insertPosition < arr.length) {
            arr[insertPosition] = 0;
            insertPosition++;
        }
    }

    //Find the Missing Number (1 to N)
    public static int findMissingNumber(int[] arr, int n) {
        // Expected sum of numbers from 1 to n
        int expectedSum = n * (n + 1) / 2;

        int actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }
        // Example usage:
        // int[] arr = {1, 2, 4, 5}; // n = 5, missing is 3
        // int missing = findMissingNumber(arr, 5); // Returns 3

    //Check for Duplicates
    public static boolean containsDuplicates(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true; // Duplicate found
                }
            }
        }
        return false; // No duplicates
    }

    //Main
    public static void main(String[] args) {
        int[] numbers = {0, 45, 0, 41, 89, 45};

        // 9. Count Occurrences
        System.out.println("Occurrences of 45: " + countOccurrences(numbers, 45));

        // 10. Move Zeroes
        moveZeroesToEnd(numbers);
        System.out.print("Zeroes moved to end: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 12. Check Duplicates
        System.out.println("Contains duplicates? " + containsDuplicates(numbers));
    }


}
