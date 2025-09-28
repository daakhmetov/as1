import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    void testEmptyArray() {
        int[] array = {};
        MergeSort sorter = new MergeSort(new Metrics());
        sorter.sort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void testSingleElement() {
        int[] array = {42};
        MergeSort sorter = new MergeSort(new Metrics());
        sorter.sort(array);
        assertArrayEquals(new int[]{42}, array);
    }

    @Test
    void testAlreadySorted() {
        int[] array = {1, 2, 3, 4, 5};
        MergeSort sorter = new MergeSort(new Metrics());
        sorter.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testReverseOrder() {
        int[] array = {5, 4, 3, 2, 1};
        MergeSort sorter = new MergeSort(new Metrics());
        sorter.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testRandomOrder() {
        int[] array = {7, 2, 9, 1, 5};
        MergeSort sorter = new MergeSort(new Metrics());
        sorter.sort(array);
        assertArrayEquals(new int[]{1, 2, 5, 7, 9}, array);
    }

    @Test
    void testLargeArray() {
        int[] array = new int[50];
        Random random = new Random(42);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        int[] expected = array.clone();
        java.util.Arrays.sort(expected);
        MergeSort sorter = new MergeSort(new Metrics());
        sorter.sort(array);

        assertArrayEquals(expected, array);
    }
}
