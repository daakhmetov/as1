public class Metrics {
    private int counter = 0;
    private int depth = 0;

    public void incrementCounter() {
        counter++;
    }

    public void enterDepth() {
        depth++;
    }

    public void exitDepth() {
        if (depth > 0) {
            depth--;
        }
    }

    public int getCounter() {
        return counter;
    }

    public int getDepth() {
        return depth;
    }
}
