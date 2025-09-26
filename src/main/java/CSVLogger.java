import java.io.FileWriter;
import java.io.IOException;

public class CSVLogger {
    private final String fileName;
    private boolean headerWritten = false;

    public CSVLogger(String fileName) {
        this.fileName = fileName;
    }

    public void writeRow(String algo, int n, Metrics metrics, long timeNs) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            if (!headerWritten) {
                writer.write("algo,n,timeNs,comparisons,allocations,maxDepth\n");
                headerWritten = true;
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
