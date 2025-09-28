import java.util.Random;

public class Utils {
    private static final Random random = new Random();

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(array, i, j);
        }
    }

    public static int partition(int[] array, int left, int right, int pivotIndex, Metrics metrics) {
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incComparisons();
            if (array[i] < pivot) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        swap(array, storeIndex, right);
        return storeIndex;
    }

    public static void checkBounds(int[] array, int left, int right) {
        if (array == null) throw new IllegalArgumentException("Array cannot be null");
        if (left < 0 || right >= array.length || left > right) {
            throw new IllegalArgumentException("Invalid bounds: left=" + left + ", right=" + right);
        }
    }
}
