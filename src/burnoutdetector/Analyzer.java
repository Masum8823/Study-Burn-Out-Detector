package burnoutdetector;

public abstract class Analyzer implements Suggestable {
    public abstract String analyzePerformance(double score);
}