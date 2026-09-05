package skybook;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {

    private MainFrame mainFrame;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;

    public RegisterPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(null);

        JLabel titleLabel = new JLabel("Create an Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setBounds(280, 20, 300, 40);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(200, 90, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(330, 90, 250, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 130, 120, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(330, 130, 250, 25);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(200, 170, 120, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(330, 170, 250, 25);
        add(phoneField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 210, 120, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(330, 210, 250, 25);
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(300, 270, 120, 30);
        add(registerButton);

        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(440, 270, 140, 30);
        add(backButton);

        registerButton.addActionListener(e -> handleRegister());

        backButton.addActionListener(e -> {
            clearFields();
            mainFrame.goToLogin();
        });
    }

    private void handleRegister() {

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Registration Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Save user into MySQL database
        boolean registered = UserDAO.registerUser(
                name,
                email,
                phone,
                password
        );

        if (registered) {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration successful! Please log in.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();
            mainFrame.goToLogin();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration failed. Email may already exist.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
    }
}