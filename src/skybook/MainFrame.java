package skybook;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    public static final String LOGIN = "LOGIN";
    public static final String REGISTER = "REGISTER";
    public static final String HOME = "HOME";
    public static final String SEARCH = "SEARCH";
    public static final String RESULTS = "RESULTS";
    public static final String PASSENGER = "PASSENGER";
    public static final String CONFIRMATION = "CONFIRMATION";
    public static final String ETICKET = "ETICKET";
    public static final String BOOKINGS = "BOOKINGS";
    public static final String PROFILE = "PROFILE";

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private HomePanel homePanel;
    private FlightSearchPanel flightSearchPanel;
    private FlightResultsPanel flightResultsPanel;
    private PassengerDetailsPanel passengerDetailsPanel;
    private BookingConfirmationPanel bookingConfirmationPanel;
    private ETicketPanel eTicketPanel;
    private MyBookingsPanel myBookingsPanel;
    private ProfilePanel profilePanel;

    // Currently logged-in user's email
    private String loggedInUserEmail;


    public MainFrame() {

        setTitle("SkyBook - Flight Booking System");

        setSize(
                850,
                600
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        setLocationRelativeTo(null);


        cardLayout = new CardLayout();

        cardPanel = new JPanel(
                cardLayout
        );


        loginPanel =
                new LoginPanel(this);

        registerPanel =
                new RegisterPanel(this);

        homePanel =
                new HomePanel(this);

        flightSearchPanel =
                new FlightSearchPanel(this);

        flightResultsPanel =
                new FlightResultsPanel(this);

        passengerDetailsPanel =
                new PassengerDetailsPanel(this);

        bookingConfirmationPanel =
                new BookingConfirmationPanel(this);

        eTicketPanel =
                new ETicketPanel(this);

        myBookingsPanel =
                new MyBookingsPanel(this);

        profilePanel =
                new ProfilePanel(this);


        cardPanel.add(
                loginPanel,
                LOGIN
        );

        cardPanel.add(
                registerPanel,
                REGISTER
        );

        cardPanel.add(
                homePanel,
                HOME
        );

        cardPanel.add(
                flightSearchPanel,
                SEARCH
        );

        cardPanel.add(
                flightResultsPanel,
                RESULTS
        );

        cardPanel.add(
                passengerDetailsPanel,
                PASSENGER
        );

        cardPanel.add(
                bookingConfirmationPanel,
                CONFIRMATION
        );

        cardPanel.add(
                eTicketPanel,
                ETICKET
        );

        cardPanel.add(
                myBookingsPanel,
                BOOKINGS
        );

        cardPanel.add(
                profilePanel,
                PROFILE
        );


        add(cardPanel);

        showCard(LOGIN);
    }


    public void showCard(String cardName) {

        cardLayout.show(
                cardPanel,
                cardName
        );
    }


    // ==========================================
    // GO TO HOME
    // ==========================================

    public void goToHome(String userEmail) {

        // Remember logged-in user
        loggedInUserEmail = userEmail;

        homePanel.setWelcomeMessage(
                userEmail
        );

        showCard(HOME);
    }


    public void goToHomeDashboard() {

        homePanel.showDashboardScreen();

        showCard(HOME);
    }


    public void goToSearch() {

        showCard(SEARCH);
    }


    public void goToResults() {

        flightResultsPanel.loadFlights();

        showCard(RESULTS);
    }


    public void goToResults(
            List<Flight> searchResults
    ) {

        flightResultsPanel.loadSearchResults(
                searchResults
        );

        showCard(RESULTS);
    }


    public void goToPassengerDetails(
            Flight selectedFlight
    ) {

        passengerDetailsPanel.setFlight(
                selectedFlight
        );

        showCard(PASSENGER);
    }


    public void goToConfirmation(
            Booking booking
    ) {

        bookingConfirmationPanel.setBooking(
                booking
        );

        showCard(CONFIRMATION);
    }


    // ==========================================
    // GO TO E-TICKET FROM CONFIRMATION
    // ==========================================

    public void goToETicket(
            Booking booking
    ) {

        eTicketPanel.setBooking(
                booking
        );

        showCard(ETICKET);
    }


    // ==========================================
    // GO TO E-TICKET FROM MY BOOKINGS
    // ==========================================

    public void goToETicketFromMyBookings(
            Booking booking
    ) {

        eTicketPanel.setBookingFromMyBookings(
                booking
        );

        showCard(ETICKET);
    }


    // ==========================================
    // GO TO MY BOOKINGS
    // ==========================================

    public void goToBookings() {

        myBookingsPanel.loadBookings(
                loggedInUserEmail
        );

        showCard(BOOKINGS);
    }


    public void goToProfile(
            String userEmail
    ) {

        profilePanel.loadProfile(
                userEmail
        );

        showCard(PROFILE);
    }


    public void goToLogin() {

        showCard(LOGIN);
    }


    public void goToRegister() {

        showCard(REGISTER);
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainFrame frame =
                    new MainFrame();

            frame.setVisible(true);
        });
    }
}