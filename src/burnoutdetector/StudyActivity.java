package burnoutdetector;
public class StudyActivity extends Activity {
    public StudyActivity(double h) {
         super(h); 
        }
        
    @Override
    public double calculatePoints() { 
        return hours * 10; 
    } 
}