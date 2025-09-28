package algo;
import java.util.ArrayList;
import java.util.Arrays;

public class DeterministicSelect {
    private final Metrics metrics;

    public DeterministicSelect(Metrics metrics) {
        this.metrics = metrics;
    }

    public int select(int[] arr, int k) {
        if (arr == null || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid input array or k");
        }
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        metrics.incAllocations();

        return selectHelper(arrCopy, k, 0, arrCopy.length - 1);
    }

    private int selectHelper(int[] arr, int k, int left, int right) {
        metrics.enterRecursion();

        if (left == right) {
            metrics.exitRecursion();
            return arr[left];
        }

        int[] subarray = Arrays.copyOfRange(arr, left, right + 1);
        metrics.incAllocations();
        int pivot = medianOfMedians(subarray);

        int pivotIndex = partition(arr, left, right, pivot);

        if (k == pivotIndex) {
            metrics.exitRecursion();
            return arr[k];
        } else if (k < pivotIndex) {
            int result = selectHelper(arr, k, left, pivotIndex - 1);
            metrics.exitRecursion();
            return result;
        } else {
            int result = selectHelper(arr, k, pivotIndex + 1, right);
            metrics.exitRecursion();
            return result;
        }
    }

    private int medianOfMedians(int[] arr) {
        metrics.enterRecursion();

        ArrayList<Integer> medians = new ArrayList<>();
        metrics.incAllocations();

        for (int i = 0; i < arr.length; i += 5) {
            int end = Math.min(i + 5, arr.length);
            int[] group = Arrays.copyOfRange(arr, i, end);
            metrics.incAllocations();
            Arrays.sort(group);
            medians.add(group[group.length / 2]);
        }

        if (medians.size() <= 1) {
            metrics.exitRecursion();
            return medians.get(0);
        }

        int[] mediansArray = medians.stream().mapToInt(Integer::intValue).toArray();
        metrics.incAllocations();

        int result = medianOfMedians(mediansArray);
        metrics.exitRecursion();
        return result;
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        int pivotIndex = -1;
        for (int i = left; i <= right; i++) {
            metrics.incComparisons();
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        if (pivotIndex == -1) pivotIndex = right;

        return Utils.partition(arr, left, right, pivotIndex, metrics);
    }
}
