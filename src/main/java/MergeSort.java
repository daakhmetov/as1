public class MergeSort {
    private final Metrics metrics;
    private static final int CUTOFF = 20; // используем insertion sort для маленьких массивов

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] array) {
        int[] buffer = new int[array.length];
        metrics.incAllocations(); // учёт выделенной памяти
        mergeSort(array, buffer, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int[] buffer, int left, int right) {
        metrics.enterRecursion();
        if (right - left < CUTOFF) {
            insertionSort(array, left, right);
            metrics.exitRecursion();
            return;
        }

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, buffer, left, mid);
            mergeSort(array, buffer, mid + 1, right);
            merge(array, buffer, left, mid, right);
        }
        metrics.exitRecursion();
    }

    private void merge(int[] array, int[] buffer, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.incComparisons();
            if (array[i] <= array[j]) {
                buffer[k++] = array[i++];
            } else {
                buffer[k++] = array[j++];
            }
        }

        while (i <= mid) buffer[k++] = array[i++];
        while (j <= right) buffer[k++] = array[j++];

        for (int p = left; p <= right; p++) {
            array[p] = buffer[p];
        }
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incComparisons();
                if (array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }
}
