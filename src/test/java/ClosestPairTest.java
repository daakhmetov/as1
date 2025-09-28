import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import algo.ClosestPair;

public class ClosestPairTest {

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4) // расстояние = 5
        };
        double result = ClosestPair.findClosest(points);
        assertEquals(5.0, result, 1e-9);
    }

    @Test
    void testThreePoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(4, 5)
        };
        double result = ClosestPair.findClosest(points);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    void testSquare() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(0, 1),
                new ClosestPair.Point(1, 0),
                new ClosestPair.Point(1, 1)
        };
        double result = ClosestPair.findClosest(points);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testRandomSet() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(10, 10),
                new ClosestPair.Point(11, 11),
                new ClosestPair.Point(50, 50),
                new ClosestPair.Point(100, 100)
        };
        double result = ClosestPair.findClosest(points);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    void testIdenticalPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(5, 5),
                new ClosestPair.Point(5, 5),
                new ClosestPair.Point(10, 10)
        };
        double result = ClosestPair.findClosest(points);
        assertEquals(0.0, result, 1e-9);
    }
}
