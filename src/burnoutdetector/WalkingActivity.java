package burnoutdetector;
public class WalkingActivity extends Activity {
    public WalkingActivity(double h) { super(h); }
    @Override public double calculatePoints() { return hours * 12; }
}