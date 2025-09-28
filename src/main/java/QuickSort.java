import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random random = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) return;

        metrics.enterRecursion();

        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(array, pivotIndex, right);
        int pivot = array[right];

        int partitionIndex = partition(array, left, right, pivot);

        if (partitionIndex - left < right - partitionIndex) {
            quickSort(array, left, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, right);
        } else {
            quickSort(array, partitionIndex + 1, right);
            quickSort(array, left, partitionIndex - 1);
        }

        metrics.exitRecursion();
    }

    private int partition(int[] array, int left, int right, int pivot) {
        int i = left - 1;
        for (int j = left; j < right; j++) {
            metrics.incComparisons();
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }
}
