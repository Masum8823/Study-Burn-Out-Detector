package burnoutdetector;
public class WasteActivity extends Activity {
    public WasteActivity(double h) {
         super(h); 
        }
    @Override
    public double calculatePoints() {
         return hours * -5; 
    } 
}