class MinHeap {
    private int[] heap;
    private int size;

    public MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size < heap.length) {
            heap[size] = value;
            int index = size;
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (heap[index] < heap[parent]) { // Change: Min-heap condition
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
            return -1; // or throw an exception

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;

        int index = 0;
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap[left] < heap[smallest]) { // Change: Min-heap condition
                smallest = left;
            }
            if (right < size && heap[right] < heap[smallest]) { // Change: Min-heap condition
                smallest = right;
            }
            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }

        return min;
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
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        System.out.print("MinHeap before delete: ");
        minHeap.display();
        System.out.println("Deleted from MinHeap: " + minHeap.delete());
        System.out.print("MinHeap after delete: ");
        minHeap.display();
    }
}
