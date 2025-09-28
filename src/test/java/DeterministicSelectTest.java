import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSingleElement() {
        int[] array = {42};
        DeterministicSelect selector = new DeterministicSelect(new Metrics());
        assertEquals(42, selector.select(array, 0));
    }

    @Test
    void testTwoElements() {
        int[] array = {7, 3};
        DeterministicSelect selector = new DeterministicSelect(new Metrics());
        assertEquals(3, selector.select(array, 0));
        assertEquals(7, selector.select(array, 1));
    }

    @Test
    void testSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        DeterministicSelect selector = new DeterministicSelect(new Metrics());
        assertEquals(1, selector.select(array, 0));
        assertEquals(3, selector.select(array, 2));
        assertEquals(5, selector.select(array, 4));
    }

    @Test
    void testUnsortedArray() {
        int[] array = {9, 1, 5, 7, 3};
        DeterministicSelect selector = new DeterministicSelect(new Metrics());
        assertEquals(1, selector.select(array, 0));
        assertEquals(3, selector.select(array, 1));
        assertEquals(5, selector.select(array, 2));
        assertEquals(7, selector.select(array, 3));
        assertEquals(9, selector.select(array, 4));
    }

    @Test
    void testWithDuplicates() {
        int[] array = {4, 2, 2, 9, 7, 2};
        DeterministicSelect selector = new DeterministicSelect(new Metrics());
        assertEquals(2, selector.select(array, 0));
        assertEquals(2, selector.select(array, 1));
        assertEquals(2, selector.select(array, 2));
        assertEquals(4, selector.select(array, 3));
        assertEquals(7, selector.select(array, 4));
        assertEquals(9, selector.select(array, 5));
    }

    @Test
    void testLargeArray() {
        int[] array = new int[50];
        java.util.Random random = new java.util.Random(123);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }

        int[] sorted = array.clone();
        java.util.Arrays.sort(sorted);

        DeterministicSelect selector = new DeterministicSelect(new Metrics());

        assertEquals(sorted[0], selector.select(array, 0));
        assertEquals(sorted[10], selector.select(array, 10));
        assertEquals(sorted[25], selector.select(array, 25));
        assertEquals(sorted[49], selector.select(array, 49));
    }
}
