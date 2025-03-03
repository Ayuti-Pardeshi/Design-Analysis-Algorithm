import java.math.BigInteger;

public class Twenty_DigitSquare {

    // Function to merge and concatenate two halves
    public static String mergeAndConcatenate(String left, String right) {
        return left + right; // Simply concatenate
    }

    // Divide and Conquer approach for merging numbers as strings
    public static String divideAndMerge(int[] arr, int left, int right) {
        // Base case: If there's only one element, return it as a string
        if (left == right) {
            return String.valueOf(arr[left]);
        }

        // Find the middle index
        int mid = left + (right - left) / 2;

        // Recursively divide and merge the two halves
        String leftHalf = divideAndMerge(arr, left, mid);
        String rightHalf = divideAndMerge(arr, mid + 1, right);

        // Merge step (concatenation)
        return mergeAndConcatenate(leftHalf, rightHalf);
    }

    public static void main(String[] args) {
        // Given array
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        // Get the merged concatenated number as a string
        String mergedString = divideAndMerge(arr, 0, arr.length - 1);

        // Convert to BigInteger and compute square
        BigInteger finalValue = new BigInteger(mergedString);
        BigInteger squaredValue = finalValue.multiply(finalValue);

        // Output result
        System.out.println("Final Merged String:\n" + mergedString);
        System.out.println("\nFinal Squared Value:\n" + squaredValue);
    }
}
