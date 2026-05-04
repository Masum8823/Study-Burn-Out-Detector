package burnoutdetector;
public class ScreenActivity extends Activity {
    public ScreenActivity(double h) { 
        super(h); 
    }
    @Override
    public double calculatePoints() { 
        return hours * -15;
    } 
}