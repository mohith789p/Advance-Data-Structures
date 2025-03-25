public class MergeSort {

    public static void mergeSort(int[] array) {
        if (array.length < 2)
            return;
        int[] tempArray = new int[array.length];
        mergeSort(array, tempArray, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] tempArray, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, tempArray, left, middle);
            mergeSort(array, tempArray, middle + 1, right);
            merge(array, tempArray, left, middle, right);
        }
    }

    private static void merge(int[] array, int[] tempArray, int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            tempArray[i] = array[i];
        }
        int i = left, j = middle + 1, k = left;
        while (i <= middle && j <= right) {
            if (tempArray[i] <= tempArray[j]) {
                array[k++] = tempArray[i++];
            } else {
                array[k++] = tempArray[j++];
            }
        }
        while (i <= middle) {
            array[k++] = tempArray[i++];
        }
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
        mergeSort(array);
        long endTime = System.nanoTime();

        System.out.println("\nSorted Array:");
        displayArray(array);

        System.out.println("\nExecution Time for Merge Sort: " + (endTime - startTime) + " nanoseconds");
    }
}
