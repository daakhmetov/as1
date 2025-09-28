package benchmarks;

import algo.DeterministicSelect;
import algo.Metrics;

import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class SelectVsSortBenchmark {

    @Param({"100", "1000", "10000", "100000"})
    public int n;

    int[] data;
    DeterministicSelect selector;

    static class DummyMetrics extends Metrics {
        public DummyMetrics() { super(); }
        @Override public void incAllocations() {}
        @Override public void incComparisons() {}
        @Override public void enterRecursion() {}
        @Override public void exitRecursion() {}
    }

    @Setup(Level.Iteration)
    public void setup() {
        data = ThreadLocalRandom.current().ints(n, 0, 1_000_000).toArray();
        selector = new DeterministicSelect(new DummyMetrics());
    }

    @Benchmark
    public int deterministicSelect() {
        return selector.select(data, n / 2); // median
    }

    @Benchmark
    public int sortThenPick() {
        int[] copy = data.clone();
        Arrays.sort(copy);
        return copy[n / 2];
    }
}
