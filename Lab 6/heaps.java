import java.util.ArrayList;
import java.util.Scanner;

class heapManager {
    private ArrayList<Integer> heap;

    //Initializing the heap
    public heapManager() {
        heap = new ArrayList<>();
    }

    //Swaps the elements at indices i and j
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    //Inserts a new value into the heap
    public void insert(int value) {
        //Add the new value at the end of the heap
        heap.add(value); 
        //Get the index of the new value
        int currIdx = heap.size() - 1; 
        //Place the new value in the correct position
        while (currIdx > 0 && heap.get(currIdx) < heap.get(parent(currIdx))) {
            //If the current value is less than its parent, swap them
            swap(currIdx, parent(currIdx)); 
            currIdx = parent(currIdx); 
        }
    }

    //Returns the index of the parent node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    //Returns the minimum value from the heap
    public int findMin() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        return heap.get(0);
    }

    //Deletes the minimum value from the heap
    public void extractMin() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        // The minimum value is at the root
        int min = heap.get(0); 
        // Remove the last element
        int lastElement = heap.remove(heap.size() - 1); 

        if (!heap.isEmpty()) {
            //Move the last element to the root
            heap.set(0, lastElement); 

            int currIdx = 0;
            //Sorts the heap from down to up
            while (true) {
                int leftIdx = (2 * currIdx) + 1;
                int rightIdx = (2 * currIdx) + 2;

                int smallest = currIdx;
                if (leftIdx < heap.size() && heap.get(leftIdx) < heap.get(smallest)) {
                    smallest = leftIdx;
                }

                if (rightIdx < heap.size() && heap.get(rightIdx) < heap.get(smallest)) {
                    smallest = rightIdx;
                }

                if (smallest == currIdx) {
                    //Finished the re-sort so breaks out of while loop
                    break; 
                }

                // Swap with the smallest child
                swap(currIdx, smallest); 
                // Move down to the smallest child's index
                currIdx = smallest; 
            }
        }

        System.out.println("Deleted value: " + min);
    }

    //Prints the heap
    public void printHeap() {
        //Checks if heap is empty
        if (heap.isEmpty()) {
            System.out.println("Heap is empty");
            return;
        } else {
            System.out.println("Heap: ");
            for (int i = 0; i < heap.size(); i++) {
                System.out.print(heap.get(i) + " ");
            }
            System.out.println();    
        }
    }
}

public class heaps {
    public static void main(String[] args) {
        heapManager heap = new heapManager();
        System.out.println("Welcome to the Minimum Heaps Program!");
        System.out.println("This program demonstrates the implementation of a minimum heap.");
        boolean go = true;
        Scanner scan = new Scanner(System.in);
        while (go) {
            System.out.println("What would you like to do?");
            System.out.println("1. Insert a value into the heap");
            System.out.println("2. Find the minimum value of the heap");
            System.out.println("3. Extract the minimum value from the heap");
            System.out.println("4. Print the heap");
            System.out.println("5. Exit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the value to insert: ");
                    int value = scan.nextInt();
                    scan.nextLine();
                    heap.insert(value);
                    System.out.println("Success!");
                    break;
                case 2:
                    try {
                        int min = heap.findMin();
                        System.out.println("Minimum value of the heap: " + min);
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        heap.extractMin();
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    heap.printHeap();
                    break;
                case 5:
                    go = false;
                    break;
                default:
                    break;
            }
        }
        scan.close();
    }
}
