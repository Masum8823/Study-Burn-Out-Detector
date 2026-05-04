package burnoutdetector;

interface Suggestable { 
    String giveSuggestion(double score);
 }

abstract class Analyzer implements Suggestable {
    public abstract String analyzePerformance(double score);
}

class SmartAnalyzer extends Analyzer {
    @Override
    public String analyzePerformance(double score) {
        if (score > 100) return "Academic Champion!";
        if (score > 0) return "Balanced";
        return "High Burnout Risk";
    }
    @Override
    public String giveSuggestion(double score) {
        return score < 0 ? "Urgent: Cut screen time!" : "Good job, keep it up!";
    }
}