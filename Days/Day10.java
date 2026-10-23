package Days;

public class Day10 {

    //Find Minimum Element
    public static int findMinimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int minVal = arr[0];
        for (int num : arr) {
            if (num < minVal) {
                minVal = num;
            }
        }
        return minVal;
    }

    //Count Even and Odd
    public static void countEvenOdd(int[] arr) {
        int evenCount = 0;
        int oddCount = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        System.out.println("Even numbers: " + evenCount + ", Odd numbers: " + oddCount);
    }

    //To check if array is sorted
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false; // Found an element out of order
            }
        }
        return true;
    }

    //To find the second largest element
    public static int findSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements");
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }
        return second;
    }


    public static void main(String[] args) {
        int[] numbers = {12, 45, 2, 41, 89, 23};

        System.out.println("Minimum element: " + findMinimum(numbers));

        countEvenOdd(numbers);

        System.out.println("Is array sorted? " + isSorted(numbers));

        System.out.println("Second largest element: " + findSecondLargest(numbers));
    }
}
