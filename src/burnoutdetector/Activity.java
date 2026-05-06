package burnoutdetector;

abstract class Activity {
    protected double hours;
    public Activity(double hours){ 
        this.hours = hours; 
    }
    public abstract double calculatePoints();
}
