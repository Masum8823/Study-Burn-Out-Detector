package burnoutdetector;
import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    private JTextField userF;
    private JPasswordField passF;

    public LoginGUI() {
        setTitle("Student Login & Registration");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" Username:")); userF = new JTextField(); add(userF);
        add(new JLabel(" Password:")); passF = new JPasswordField(); add(passF);

        JButton loginBtn = new JButton("Login");
        JButton regBtn = new JButton("Register New User");
        add(loginBtn); add(regBtn);

        loginBtn.addActionListener(e -> {
            String name = userF.getText().trim();
            String pass = new String(passF.getPassword());
            if (FileHandler.loginCheck(name, pass)) {
                new BurnoutAppGUI(name).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        regBtn.addActionListener(e -> {
            try {
                String name = userF.getText().trim();
                String pass = new String(passF.getPassword());
                
                if(name.isEmpty() || pass.isEmpty()) {
                    throw new AuthException("Username and Password cannot be empty!");
                }
                
                if(FileHandler.userExists(name)) {
                    throw new AuthException("Username '" + name + "' is already taken! Please try another.");
                }

                FileHandler.registerUser(name, pass);
                JOptionPane.showMessageDialog(this, "Registration Successful! Now you can Login.");
            } catch (AuthException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Registration Error", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage());
            }
        });

        setLocationRelativeTo(null); 
    }

    public static void main(String[] args) {
        new LoginGUI().setVisible(true);
    }
}
