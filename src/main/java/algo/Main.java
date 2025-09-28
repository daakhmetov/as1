package algo;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java Main <algo> <n> <csvFile>");
            System.exit(1);
        }

        String algo = args[0];
        int n = Integer.parseInt(args[1]);
        String csvFile = args[2];

        Metrics metrics = new Metrics();
        long start, end;
        Random random = new Random();

        try {
            switch (algo.toLowerCase()) {
                case "mergesort": {
                    int[] array = random.ints(n, 0, 100000).toArray();
                    MergeSort sorter = new MergeSort(metrics);
                    start = System.nanoTime();
                    sorter.sort(array);
                    end = System.nanoTime();
                    break;
                }
                case "quicksort": {
                    int[] array = random.ints(n, 0, 100000).toArray();
                    QuickSort sorter = new QuickSort(metrics);
                    start = System.nanoTime();
                    sorter.sort(array);
                    end = System.nanoTime();
                    break;
                }
                case "select": {
                    int[] array = random.ints(n, 0, 100000).toArray();
                    DeterministicSelect selector = new DeterministicSelect(metrics);
                    int k = n / 2;
                    start = System.nanoTime();
                    selector.select(array, k);
                    end = System.nanoTime();
                    break;
                }
                case "closest": {
                    ClosestPair.Point[] points = new ClosestPair.Point[n];
                    for (int i = 0; i < n; i++) {
                        points[i] = new ClosestPair.Point(random.nextDouble() * 10000, random.nextDouble() * 10000);
                    }
                    start = System.nanoTime();
                    ClosestPair.findClosest(points); // если хочешь → переименуем в closest()
                    end = System.nanoTime();
                    break;
                }
                default:
                    System.err.println("Unknown algorithm: " + algo);
                    return;
            }

            long timeNs = end - start;
            appendCsv(csvFile, algo, n, metrics, timeNs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void appendCsv(String fileName, String algo, int n, Metrics metrics, long timeNs) {
        boolean headerNeeded = !new java.io.File(fileName).exists();

        try (FileWriter writer = new FileWriter(fileName, true)) {
            if (headerNeeded) {
                writer.write("algo,n,timeNs,comparisons,allocations,maxDepth\n");
            }
            writer.write(String.format("%s,%d,%d,%d,%d,%d\n",
                    algo, n, timeNs,
                    metrics.getComparisons(),
                    metrics.getAllocations(),
                    metrics.getMaxDepth()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
