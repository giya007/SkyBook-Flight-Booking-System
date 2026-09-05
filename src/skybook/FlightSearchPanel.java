package skybook;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FlightSearchPanel extends JPanel {

    private MainFrame mainFrame;

    private JLabel titleLabel;

    private JLabel fromLabel;
    private JComboBox<String> fromCombo;

    private JLabel toLabel;
    private JComboBox<String> toCombo;

    private JLabel dateLabel;
    private JTextField dateField;

    private JButton searchButton;
    private JButton backButton;


    public FlightSearchPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        // ==========================================
        // PANEL
        // ==========================================

        setLayout(null);


        // ==========================================
        // TITLE
        // ==========================================

        titleLabel = new JLabel(
                "Search Flights",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 24)
        );

        titleLabel.setBounds(
                225,
                30,
                400,
                40
        );

        add(titleLabel);


        // ==========================================
        // FROM
        // ==========================================

        fromLabel = new JLabel("From:");

        fromLabel.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        fromLabel.setBounds(
                210,
                100,
                100,
                25
        );

        add(fromLabel);


        fromCombo = new JComboBox<>(
                SampleData.CITIES
        );

        fromCombo.setBounds(
                350,
                100,
                220,
                30
        );

        add(fromCombo);


        // ==========================================
        // TO
        // ==========================================

        toLabel = new JLabel("To:");

        toLabel.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        toLabel.setBounds(
                210,
                145,
                100,
                25
        );

        add(toLabel);


        toCombo = new JComboBox<>(
                SampleData.CITIES
        );

        toCombo.setSelectedIndex(1);

        toCombo.setBounds(
                350,
                145,
                220,
                30
        );

        add(toCombo);


        // ==========================================
        // TRAVEL DATE
        // ==========================================

        dateLabel = new JLabel("Travel Date:");

        dateLabel.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        dateLabel.setBounds(
                210,
                190,
                100,
                25
        );

        add(dateLabel);


        dateField = new JTextField();

        dateField.setBounds(
                350,
                190,
                220,
                30
        );

        add(dateField);


        // ==========================================
        // SEARCH BUTTON
        // ==========================================

        searchButton = new JButton(
                "Search Flights"
        );

        searchButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        searchButton.setBounds(
                300,
                250,
                250,
                40
        );

        add(searchButton);


        // ==========================================
        // BACK BUTTON
        // ==========================================

        backButton = new JButton(
                "Back"
        );

        backButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        backButton.setBounds(
                350,
                310,
                150,
                40
        );

        add(backButton);


        // ==========================================
        // SEARCH ACTION
        // ==========================================

        searchButton.addActionListener(e ->
                handleSearch()
        );


        // ==========================================
        // BACK ACTION
        // ==========================================

        backButton.addActionListener(e ->
                mainFrame.goToHomeDashboard()
        );
    }


    // ==========================================
    // HANDLE SEARCH
    // ==========================================

    private void handleSearch() {

        String from =
                (String) fromCombo.getSelectedItem();

        String to =
                (String) toCombo.getSelectedItem();

        String date =
                dateField.getText().trim();


        // ==========================================
        // CHECK SAME CITY
        // ==========================================

        if (from.equals(to)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Origin and destination cannot be the same.",
                    "Invalid Search",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // ==========================================
        // CHECK DATE
        // ==========================================

        if (date.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the travel date.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // ==========================================
        // SEARCH DATABASE
        // ==========================================

        List<Flight> searchResults =
                FlightDAO.searchFlights(
                        from,
                        to,
                        date
                );


        // ==========================================
        // CHECK SEARCH RESULTS
        // ==========================================

        if (searchResults.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No flights found for the selected search.",
                    "No Flights",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        // ==========================================
        // GO TO RESULTS
        // ==========================================

        mainFrame.goToResults(
                searchResults
        );
    }
}