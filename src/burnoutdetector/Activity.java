package burnoutdetector;

abstract class Activity {
    protected double hours;
    public Activity(double hours) { this.hours = hours; }
    public abstract double calculatePoints();
}

class StudyActivity extends Activity {
    public StudyActivity(double h) { super(h); }
    @Override public double calculatePoints() { return hours * 10; }
}

class ScreenActivity extends Activity {
    public ScreenActivity(double h) { super(h); }
    @Override public double calculatePoints() { return hours * -15; }
}

class LeisureActivity extends Activity {
    public LeisureActivity(double h) { super(h); }
    @Override public double calculatePoints() { return hours * 8; }
}

class WasteActivity extends Activity {
    public WasteActivity(double h) { super(h); }
    @Override public double calculatePoints() { return hours * -5; }
}