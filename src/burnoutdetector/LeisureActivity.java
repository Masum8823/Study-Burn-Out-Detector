package burnoutdetector;
public class LeisureActivity extends Activity {
    public LeisureActivity(double h) { 
        super(h); 
    }
    @Override
    public double calculatePoints() { 
        return hours * 8; 
    } 
}