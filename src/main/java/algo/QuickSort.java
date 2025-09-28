package algo;
import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private static final Random random = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] array) {
        if (array == null || array.length <= 1) return;
        Utils.shuffle(array);
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        metrics.enterRecursion();
        if (left < right) {
            Utils.checkBounds(array, left, right);
            int pivotIndex = left + random.nextInt(right - left + 1);
            int partitionIndex = Utils.partition(array, left, right, pivotIndex, metrics);
            if (partitionIndex - left < right - partitionIndex) {
                quickSort(array, left, partitionIndex - 1);
                quickSort(array, partitionIndex + 1, right);
            } else {
                quickSort(array, partitionIndex + 1, right);
                quickSort(array, left, partitionIndex - 1);
            }
        }
        metrics.exitRecursion();
    }
}
