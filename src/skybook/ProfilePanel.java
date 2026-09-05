package skybook;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    private MainFrame mainFrame;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;

    private String loggedInEmail;

    public ProfilePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Absolute positioning
        setLayout(null);

        JLabel titleLabel = new JLabel("My Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setBounds(280, 20, 300, 40);
        add(titleLabel);

        // Name
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(200, 90, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(330, 90, 250, 25);
        add(nameField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 130, 120, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(330, 130, 250, 25);
        emailField.setEditable(false);
        add(emailField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(200, 170, 120, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(330, 170, 250, 25);
        add(phoneField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 210, 120, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(330, 210, 250, 25);
        add(passwordField);

        // Update button
        JButton updateButton = new JButton("Update Profile");
        updateButton.setBounds(300, 270, 150, 35);
        add(updateButton);

        // Back button
        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(460, 270, 150, 35);
        add(backButton);

        updateButton.addActionListener(e -> handleUpdate());

        backButton.addActionListener(e ->
                mainFrame.showCard(MainFrame.HOME)
        );
    }

    // Called when Profile page is opened
    public void loadProfile(String email) {

        loggedInEmail = email;

        String[] user = UserDAO.getUserProfile(email);

        if (user != null) {

            // Load existing data into the fields
            nameField.setText(user[0]);
            emailField.setText(user[1]);
            phoneField.setText(user[2]);
            passwordField.setText(user[3]);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load profile.",
                    "Profile Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void handleUpdate() {

        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Make sure fields are not empty
        if (name.isEmpty() || phone.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Update Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Update the current values in MySQL
        boolean updated = UserDAO.updateProfile(
                loggedInEmail,
                name,
                phone,
                password
        );

        if (updated) {

            JOptionPane.showMessageDialog(
                    this,
                    "Profile updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Profile update failed.",
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}