import java.io.FileWriter;
import java.io.IOException;

public class CSVLogger {
    private final String fileName;

    public CSVLogger(String fileName) {
        this.fileName = fileName;
    }

    public void writeRow(int counter, int depth) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(counter + "," + depth + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}