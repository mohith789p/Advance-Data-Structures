import java.util.*;

public class maxHeap {
    public static void main(String args[]) {
        List<Integer> heap = new ArrayList<>();

        insert(heap, 3);
        insert(heap, 1);
        insert(heap, 6);
        insert(heap, 5);
        insert(heap, 2);
        insert(heap, 4);

        int max = extractMax(heap);
        if (max != -1) {
            System.out.println("The Maximum Element in MaxHeap is " + max);
        }
        System.out.println("The Max Heap after extraction: ");
        System.out.print(heap + " ");
    }

    public static void insert(List<Integer> heap, int val) {
        heap.add(val);
        int index = heap.size() - 1;
        // Bubble up to maintain heap property
        while (index > 0) {
            int child = index;
            int parentIndex = (index - 1) / 2;

            if (heap.get(child) <= heap.get(parentIndex)) {
                break;
            }
            // Swap the child with its parent
            Collections.swap(heap, child, parentIndex);
            index = parentIndex;
        }
    }

    public static int extractMax(List<Integer> heap) {
        if (heap.size() == 0) {
            System.out.println("Heap is empty!");
            return -1;
        }
        int max = heap.get(0);
        // Move the last element to the root
        heap.set(0, heap.remove(heap.size() - 1));
        // Bubble down to maintain heap property
        int index = 0;
        int lastIndex = heap.size() - 1;
        while (index <= lastIndex) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild <= lastIndex && heap.get(leftChild) > heap.get(largest)) {
                largest = leftChild;
            }
            if (rightChild <= lastIndex && heap.get(rightChild) > heap.get(largest)) {
                largest = rightChild;
            }
            // If the current index is the largest, we are done
            if (largest == index) {
                break;
            }
            // Swap the current index with the largest index found
            Collections.swap(heap, index, largest);
            index = largest;
        }
        return max;
    }
}