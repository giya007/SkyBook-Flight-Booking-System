package skybook;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private MainFrame mainFrame;

    private JLabel welcomeLabel;

    private JButton searchButton;
    private JButton myBookingsButton;
    private JButton profileButton;
    private JButton logoutButton;

    private String loggedInEmail;

    public HomePanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        // ==========================================
        // PANEL
        // ==========================================

        setLayout(null);


        // ==========================================
        // WELCOME
        // ==========================================

        welcomeLabel = new JLabel(
                "Welcome!",
                SwingConstants.CENTER
        );

        welcomeLabel.setFont(
                new Font("Serif", Font.BOLD, 22)
        );

        welcomeLabel.setBounds(
                225,
                30,
                400,
                40
        );

        add(welcomeLabel);


        // ==========================================
        // SEARCH FLIGHTS
        // ==========================================

        searchButton = new JButton(
                "Search Flights"
        );

        searchButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        searchButton.setBounds(
                300,
                120,
                250,
                40
        );

        add(searchButton);


        // ==========================================
        // MY BOOKINGS
        // ==========================================

        myBookingsButton = new JButton(
                "My Bookings"
        );

        myBookingsButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        myBookingsButton.setBounds(
                230,
                200,
                180,
                40
        );

        add(myBookingsButton);


        // ==========================================
        // PROFILE
        // ==========================================

        profileButton = new JButton(
                "Profile"
        );

        profileButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        profileButton.setBounds(
                440,
                200,
                180,
                40
        );

        add(profileButton);


        // ==========================================
        // LOGOUT
        // ==========================================

        logoutButton = new JButton(
                "Logout"
        );

        logoutButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        logoutButton.setBounds(
                335,
                260,
                180,
                40
        );

        add(logoutButton);


        // ==========================================
        // BUTTON ACTIONS
        // ==========================================

        // Search Flights
        searchButton.addActionListener(e ->
                mainFrame.goToSearch()
        );


        // My Bookings
        myBookingsButton.addActionListener(e ->
                mainFrame.goToBookings()
        );


        // Profile
        profileButton.addActionListener(e ->
                mainFrame.goToProfile(loggedInEmail)
        );


        // Logout
        logoutButton.addActionListener(e ->
                mainFrame.goToLogin()
        );
    }


    // ==========================================
    // SET LOGGED-IN USER
    // ==========================================

    public void setWelcomeMessage(String userEmail) {

        // Keep the email for Profile and other operations
        loggedInEmail = userEmail;

        // Get user's information from MySQL
        String[] user = UserDAO.getUserProfile(userEmail);

        if (user != null) {

            // user[0] = name
            welcomeLabel.setText(
                    "Welcome, " + user[0] + "!"
            );

        } else {

            // Fallback if user cannot be found
            welcomeLabel.setText(
                    "Welcome!"
            );
        }
    }


    // ==========================================
    // SHOW DASHBOARD
    // ==========================================

    public void showDashboardScreen() {

        String[] user = UserDAO.getUserProfile(loggedInEmail);

        if (user != null) {

            welcomeLabel.setText(
                    "Welcome, " + user[0] + "!"
            );

        } else {

            welcomeLabel.setText(
                    "Welcome!"
            );
        }
    }
}