public class QuickSort {

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void displayArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = { 56, 23, 78, 12, 34, 89, 45, 67, 90, 10 };

        System.out.println("Original Array:");
        displayArray(array);

        long startTime = System.nanoTime();
        quickSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();

        System.out.println("\nSorted Array:");
        displayArray(array);

        System.out.println("\nExecution Time for Quick Sort: " + (endTime - startTime) + " nanoseconds");
    }
}
