package skybook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {

    private MainFrame mainFrame;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(null); // absolute positioning — no layout manager

        JLabel titleLabel = new JLabel("SkyBook", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setBounds(300, 30, 250, 50);
        add(titleLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(250, 140, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(360, 140, 220, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(250, 180, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(360, 180, 220, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(320, 240, 100, 30);
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(440, 240, 120, 30);
        add(registerButton);

        loginButton.addActionListener(this::handleLogin);

        registerButton.addActionListener(e -> mainFrame.goToRegister());
    }

    private void handleLogin(ActionEvent e) {

        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Check if fields are empty
        if (email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both email and password.",
                    "Login Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Check email and password in MySQL
        boolean loginSuccessful = UserDAO.loginUser(
                email,
                password
        );

        if (loginSuccessful) {

            // Login successful
            passwordField.setText("");

            mainFrame.goToHome(email);

        } else {

            // Login failed
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid email or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");
        }
    }
}