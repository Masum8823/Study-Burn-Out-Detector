package burnoutdetector;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BurnoutAppGUI extends JFrame {
    private String currentUser;
    private JTextField dateF, studyF, screenF, hobbyF, walkF, wasteF, sleepF;
    private JTextField fajrF, dhuhrF, asrF, maghribF, ishaF;
    private JTextArea output;
    private SmartAnalyzer engine = new SmartAnalyzer();

    public BurnoutAppGUI(String user) {
        this.currentUser = user;
        setTitle("Dashboard Pro - " + currentUser);
        setSize(850, 900); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Left Panel: Input Form ---
        JPanel inputPanel = new JPanel(new GridLayout(18, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Daily Journal Entry"));
        inputPanel.setPreferredSize(new Dimension(350, 900));

        inputPanel.add(new JLabel(" User: " + currentUser));
        JButton logoutBtn = new JButton("Logout System"); 
        logoutBtn.setBackground(new Color(255, 150, 150));
        inputPanel.add(logoutBtn);

        inputPanel.add(new JLabel(" Date (DD-MM-YYYY):")); dateF = new JTextField("06-05-2026"); inputPanel.add(dateF);
        inputPanel.add(new JLabel(" Study Hours (+10):")); studyF = new JTextField("0"); inputPanel.add(studyF);
        inputPanel.add(new JLabel(" Screen Time (-15):")); screenF = new JTextField("0"); inputPanel.add(screenF);
        inputPanel.add(new JLabel(" Hobby Hours (+8):")); hobbyF = new JTextField("0"); inputPanel.add(hobbyF);
        inputPanel.add(new JLabel(" Walking Hours (+12):")); walkF = new JTextField("0"); inputPanel.add(walkF);
        inputPanel.add(new JLabel(" Travel/Waste (-5):")); wasteF = new JTextField("0"); inputPanel.add(wasteF);
        inputPanel.add(new JLabel(" Sleep Hours (+5):")); sleepF = new JTextField("0"); inputPanel.add(sleepF);

        inputPanel.add(new JLabel("--- PRAYER CHECKLIST (1/0) ---")); inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(" Fajr:")); fajrF = new JTextField("0"); inputPanel.add(fajrF);
        inputPanel.add(new JLabel(" Dhuhr:")); dhuhrF = new JTextField("0"); inputPanel.add(dhuhrF);
        inputPanel.add(new JLabel(" Asr:")); asrF = new JTextField("0"); inputPanel.add(asrF);
        inputPanel.add(new JLabel(" Maghrib:")); maghribF = new JTextField("0"); inputPanel.add(maghribF);
        inputPanel.add(new JLabel(" Isha:")); ishaF = new JTextField("0"); inputPanel.add(ishaF);

        JButton saveBtn = new JButton("Save & Analyze Today");
        JButton historyBtn = new JButton("Interactive History Viewer");
        JButton statsBtn = new JButton("Average Performance Stats");

        saveBtn.setBackground(new Color(180, 255, 180));
        inputPanel.add(saveBtn); inputPanel.add(historyBtn);
        inputPanel.add(statsBtn); 

        add(inputPanel, BorderLayout.WEST);

        // --- Center Panel: Output Display ---
        output = new JTextArea();
        output.setFont(new Font("Monospaced", Font.BOLD, 14));
        output.setBackground(new Color(240, 245, 240));
        output.setEditable(false);
        output.setBorder(BorderFactory.createTitledBorder("Analysis & Reports"));
        add(new JScrollPane(output), BorderLayout.CENTER);

        // --- Actions ---
        saveBtn.addActionListener(e -> processDailySave());
        historyBtn.addActionListener(e -> openInteractiveHistory());
        statsBtn.addActionListener(e -> showAverageStats());
        logoutBtn.addActionListener(e -> { new LoginGUI().setVisible(true); this.dispose(); });
    }

    private void processDailySave() {
        try {
            if (FileHandler.isDuplicate(currentUser, dateF.getText())) throw new InvalidInputException("Date already exists!");
            
            StudySession s = new StudySession(dateF.getText(),
                Double.parseDouble(studyF.getText()), Double.parseDouble(screenF.getText()),
                Double.parseDouble(hobbyF.getText()), Double.parseDouble(wasteF.getText()),
                Double.parseDouble(sleepF.getText()), Double.parseDouble(walkF.getText()),
                Integer.parseInt(fajrF.getText()), Integer.parseInt(dhuhrF.getText()),
                Integer.parseInt(asrF.getText()), Integer.parseInt(maghribF.getText()), Integer.parseInt(ishaF.getText()));

            FileHandler.saveRecord(currentUser, s);
            
            StringBuilder sb = new StringBuilder();
            sb.append("========== TODAY'S DETAILED REPORT ==========\n");
            sb.append("Date: ").append(s.getDate()).append("\n");
            sb.append("---------------------------------------------\n");
            sb.append(String.format("%-20s : +%.1f\n", "Study Points", Double.parseDouble(studyF.getText())*10));
            sb.append(String.format("%-20s : %.1f\n", "Screen Points", Double.parseDouble(screenF.getText())*-15));
            sb.append(String.format("%-20s : +%.1f\n", "Walking Points", Double.parseDouble(walkF.getText())*12));
            sb.append(String.format("%-20s : +%d\n", "Prayer Points", s.getPrayerCount()*5));
            sb.append("---------------------------------------------\n");
            sb.append("🏆 FINAL NET SCORE : ").append(s.getNetScore()).append("\n");
            sb.append("📊 PERFORMANCE     : ").append(engine.analyzePerformance(s.getNetScore())).append("\n");
            sb.append("💡 SYSTEM ADVICE   : ").append(engine.giveSuggestion(s.getNetScore())).append("\n");
            output.setText(sb.toString());

        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
    }

    private void openInteractiveHistory() {
        List<String> records = FileHandler.getUserRecords(currentUser);
        if (records.isEmpty()) { JOptionPane.showMessageDialog(this, "No history found."); return; }

        JDialog diag = new JDialog(this, "History Explorer", true);
        diag.setSize(650, 450);
        diag.setLayout(new BorderLayout());

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String r : records) model.addElement(r.split(",")[1]); // তারিখ

        JList<String> list = new JList<>(model);
        JTextArea detail = new JTextArea();
        detail.setFont(new Font("Monospaced", Font.BOLD, 13));
        detail.setEditable(false);

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedIndex() != -1) {
                String[] d = records.get(list.getSelectedIndex()).split(",");
                StringBuilder sb = new StringBuilder("--- Story of " + d[1] + " ---\n\n");
                sb.append("📖 Study       : ").append(d[2]).append(" Hrs\n");
                sb.append("📱 Screen      : ").append(d[3]).append(" Hrs\n");
                sb.append("🚶 Walking     : ").append(d[7]).append(" Hrs\n");
                sb.append("🕌 Prayers     : ").append(Integer.parseInt(d[8])+Integer.parseInt(d[9])+Integer.parseInt(d[10])+Integer.parseInt(d[11])+Integer.parseInt(d[12])).append("/5\n");
                sb.append("-----------------------------\n");
                sb.append("🏆 SCORE       : ").append(d[13]).append("\n");
                sb.append("📊 RANK        : ").append(engine.analyzePerformance(Double.parseDouble(d[13]))).append("\n");
                detail.setText(sb.toString());
            }
        });

        diag.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(list), new JScrollPane(detail)), BorderLayout.CENTER);
        diag.setLocationRelativeTo(this); diag.setVisible(true);
    }

    private void showAverageStats() {
        List<String> records = FileHandler.getUserRecords(currentUser);
        if (records.isEmpty()) return;

        double scoreSum = 0, studySum = 0, walkSum = 0, prayerSum = 0;
        int days = records.size();

        for (String r : records) {
            String[] d = r.split(",");
            if (d.length >= 14) {
                studySum += Double.parseDouble(d[2]);
                walkSum += Double.parseDouble(d[7]);
                prayerSum += (Integer.parseInt(d[8]) + Integer.parseInt(d[9]) + Integer.parseInt(d[10]) + Integer.parseInt(d[11]) + Integer.parseInt(d[12]));
                scoreSum += Double.parseDouble(d[13]);
            }
        }

        double avgScore = scoreSum / days;
        StringBuilder sb = new StringBuilder();
        sb.append("========== AVERAGE PROGRESS SUMMARY ==========\n");
        sb.append("Tracking Period  : ").append(days).append(" Days\n");
        sb.append("Avg Daily Score  : ").append(String.format("%.2f", avgScore)).append("\n");
        sb.append("Avg Study Time   : ").append(String.format("%.2f", studySum/days)).append(" Hrs\n");
        sb.append("Avg Walking      : ").append(String.format("%.2f", walkSum/days)).append(" Hrs\n");
        sb.append("Avg Daily Prayer : ").append(String.format("%.1f", prayerSum/days)).append("/5\n");
        sb.append("----------------------------------------------\n");
        sb.append("📢 OVERALL STATUS: ").append(engine.analyzePerformance(avgScore)).append("\n");
        sb.append("💡 SMART ADVICE  : ").append(engine.giveSuggestion(avgScore)).append("\n");
        
        if (prayerSum/days < 4) sb.append("\n⚠️ Tip: Focus more on your 5 daily prayers for mental stability.");
        if (walkSum/days < 0.5) sb.append("\n⚠️ Tip: Try to walk at least 30 mins to avoid study burnout.");

        output.setText(sb.toString());
    }
}