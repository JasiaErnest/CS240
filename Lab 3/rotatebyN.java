import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class rotatebyN {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Enter the number of positions to rotate the array:");
        int n = input.nextInt(); // Number of positions to rotate
        System.out.println("Original array:");
        printArray(arr);
        rotate(arr, n);
        System.out.println("Array after rotating by " + n + " positions:");
        printArray(arr);
        input.close();
    }
    
    // Function to rotate array by N positions
    public static void rotate(ArrayList<Integer> arr, int n) {
        int len = arr.size();
        int totalRotationsNeeded = len/n; 
        for (int i = 0; i < totalRotationsNeeded; i++) {
            int firstPos;
            if (i == 0) {
                firstPos = 0;
            } else {
                firstPos = i * n;
            }
            int lastPos = firstPos + n - 1;
            for (int forSwitchPos = firstPos; forSwitchPos < lastPos; forSwitchPos++) {
                Collections.swap(arr, forSwitchPos, lastPos);
            }
        }
    }
    
    // Function to print the array
    public static void printArray(ArrayList<Integer> arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    
}