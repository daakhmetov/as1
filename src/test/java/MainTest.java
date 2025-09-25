import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void testAdd() {
        // This checks if Main.add(2,3) really returns 5
        assertEquals(5, Main.add(2, 3));
    }
}
