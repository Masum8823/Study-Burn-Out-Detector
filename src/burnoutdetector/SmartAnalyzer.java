package burnoutdetector;
public class SmartAnalyzer extends Analyzer {
    @Override
    public String analyzePerformance(double score) {
        if (score > 100) return "Academic Champion!";
        if (score > 50) return "Balanced Student";
        if (score > 0) return "Borderline Burnout";
        return "High Burnout/Unproductive";
    }

    @Override
    public String giveSuggestion(double score) {
        if (score < 0) return "Urgent: Cut mobile/TV and fix your sleep cycle!";
        if (score < 50) return "Reduce wasted time and focus on study/hobbies.";
        return "Excellent! You have a very healthy study routine.";
    }
}