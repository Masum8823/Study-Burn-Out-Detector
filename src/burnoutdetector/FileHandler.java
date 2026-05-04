package burnoutdetector;
import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String USER_FILE = "users.txt";
    private static final String DATA_FILE = "burnout_records.txt";

    public static boolean userExists(String name) {
        File f = new File(USER_FILE);
        if (!f.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 1 && d[0].equalsIgnoreCase(name)) {
                    return true; 
                }
            }
        } catch (IOException e) {}
        return false;
    }

    public static void registerUser(String name, String pass) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(USER_FILE, true)))) {
            out.println(name + "," + pass);
        }
    }

    public static boolean loginCheck(String name, String pass) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 2 && d[0].equals(name) && d[1].equals(pass)) return true;
            }
        } catch (IOException e) {}
        return false;
    }

    public static void saveRecord(String name, StudySession s) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DATA_FILE, true)))) {
            out.println(name + "," + s.toString());
        }
    }

    public static List<String> getUserRecords(String name) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(name + ",")) list.add(line);
            }
        } catch (IOException e) {}
        return list;
    }

    public static boolean isDuplicate(String name, String date) {
        for (String r : getUserRecords(name)) {
            if (r.contains("," + date + ",")) return true;
        }
        return false;
    }
}