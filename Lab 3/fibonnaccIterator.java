import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class fibonnaccIterator{
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        Queue<Integer> storage = new LinkedList<>(); //Queue to store the fibonnaci numbers
        storage.add(1);
        storage.add(2);
        System.out.println("This is a fibonnaci iterator program that will show all fibonnaci numbers below a certain number.");
        System.out.println("To use this program, please insert a number.");
        int maxNum = read.nextInt();
        iterator(maxNum, storage);
        read.close();
    }

    public static void iterator(int maxNum, Queue<Integer> storage) {
        int lastInt = 2;
        while (lastInt <= maxNum) {
            int firstInt = storage.poll();
            if (lastInt == 2) { //To print the first number (1)
                System.out.println(firstInt);
            }
            System.out.println(lastInt);
            lastInt = firstInt + lastInt;
            storage.add(lastInt);
        }
    }
}