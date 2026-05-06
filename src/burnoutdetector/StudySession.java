package burnoutdetector;

public class StudySession {
    private String date;
    private double stu, scr, hobb, wst, slp, walk;
    private int f, d, a, m, i;

    public StudySession(String date, double stu, double scr, double hobb, double wst, double slp, double walk, int f, int d, int a, int m, int i) {
        this.date = date; 
        this.stu = stu; 
        this.scr = scr;
        this.hobb = hobb; 
        this.wst = wst;
        this.slp = slp;
        this.walk = walk;
        this.f = f; this.d = d; this.a = a; this.m = m; this.i = i;
    }

    public double getNetScore() {
        double studyPts = new StudyActivity(stu).calculatePoints();
        double screenPts = new ScreenActivity(scr).calculatePoints();
        double hobbyPts = new LeisureActivity(hobb).calculatePoints();
        double wastePts = new WasteActivity(wst).calculatePoints();
        double walkPts = new WalkingActivity(walk).calculatePoints();

        double sleepPts = slp * 5; 

        int prayerScore = (f + d + a + m + i) * 5;
        
        return studyPts + screenPts + hobbyPts + wastePts + walkPts + sleepPts + prayerScore;

        }

    public int getPrayerCount() { 
        return f + d + a + m + i; 
        }

    public String getDate() {
         return date; 
        }

    @Override
    public String toString() {
        // Index mapping: 0:Name, 1:Date, 2:Stu, 3:Scr, 4:Hobb, 5:Wst, 6:Slp, 7:Walk, 8-12:Prayers, 13:TotalScore
        return date + "," + stu + "," + scr + "," + hobb + "," + wst + "," + slp + "," + walk + "," + f + "," + d + "," + a + "," + m + "," + i + "," + getNetScore();
    }
}