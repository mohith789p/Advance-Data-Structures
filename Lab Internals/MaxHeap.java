class MaxHeap {
    private int[] heap;
    private int size;

    public MaxHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size < heap.length) {
            heap[size] = value;
            int index = size;
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (heap[index] > heap[parent]) {
                    swap(index, parent);
                    index = parent;
                } else {
                    break;
                }
            }
            size++;
        }
    }

    public int delete() {
        if (size == 0)
            return -1;
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;

        int index = 0;
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }
            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }
            if (largest == index) {
                break;
            }

            swap(index, largest);
            index = largest;
        }

        return max;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);
        System.out.print("MaxHeap before delete: ");
        maxHeap.display();
        System.out.println("Deleted from MaxHeap: " + maxHeap.delete());
        System.out.print("MaxHeap after delete: ");
        maxHeap.display();
    }
}
