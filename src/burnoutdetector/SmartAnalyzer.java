package burnoutdetector;

public class SmartAnalyzer extends Analyzer {
    @Override
    public String analyzePerformance(double score) {
        if (score > 100) return "Academic Champion!";
        if (score > 0) return "Balanced";
        return "High Burnout Risk";
    }

    @Override
    public String giveSuggestion(double score) {
        return score < 0 ? "Urgent: Cut screen time & get rest!" : "Good job, keep it up!";
    }
}