package skybook;

import javax.swing.*;
import java.awt.*;

public class PassengerDetailsPanel extends JPanel {

    private MainFrame mainFrame;
    private Flight selectedFlight;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField dobField;

    private JComboBox<String> genderCombo;
    private JComboBox<String> seatCombo;

    private JLabel totalFareLabel;


    public PassengerDetailsPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        // ==========================================
        // PANEL
        // ==========================================

        setLayout(null);


        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel =
                new JLabel(
                        "Passenger Details",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 20)
        );

        titleLabel.setBounds(
                280,
                10,
                300,
                40
        );

        add(titleLabel);


        // ==========================================
        // PASSENGER NAME
        // ==========================================

        JLabel nameLabel =
                new JLabel("Passenger Name:");

        nameLabel.setBounds(
                150,
                70,
                220,
                25
        );

        add(nameLabel);


        nameField =
                new JTextField();

        nameField.setBounds(
                380,
                70,
                250,
                25
        );

        add(nameField);


        // ==========================================
        // PASSENGER EMAIL
        // ==========================================

        JLabel emailLabel =
                new JLabel("Passenger Email:");

        emailLabel.setBounds(
                150,
                110,
                220,
                25
        );

        add(emailLabel);


        emailField =
                new JTextField();

        emailField.setBounds(
                380,
                110,
                250,
                25
        );

        add(emailField);


        // ==========================================
        // DATE OF BIRTH
        // ==========================================

        JLabel dobLabel =
                new JLabel(
                        "Date of Birth (dd-MM-yyyy):"
                );

        dobLabel.setBounds(
                150,
                150,
                220,
                25
        );

        add(dobLabel);


        dobField =
                new JTextField();

        dobField.setBounds(
                380,
                150,
                250,
                25
        );

        add(dobField);


        // ==========================================
        // GENDER
        // ==========================================

        JLabel genderLabel =
                new JLabel("Gender:");

        genderLabel.setBounds(
                150,
                190,
                220,
                25
        );

        add(genderLabel);


        genderCombo =
                new JComboBox<>(
                        SampleData.GENDERS
                );

        genderCombo.setBounds(
                380,
                190,
                150,
                25
        );

        add(genderCombo);


        // ==========================================
        // SEAT
        // ==========================================

        JLabel seatLabel =
                new JLabel("Seat:");

        seatLabel.setBounds(
                150,
                230,
                220,
                25
        );

        add(seatLabel);


        seatCombo =
                new JComboBox<>(
                        SampleData.SEATS
                );

        seatCombo.setBounds(
                380,
                230,
                150,
                25
        );

        add(seatCombo);


        // ==========================================
        // TOTAL FARE
        // ==========================================

        JLabel totalFareCaption =
                new JLabel("Total Fare:");

        totalFareCaption.setBounds(
                150,
                270,
                220,
                25
        );

        add(totalFareCaption);


        totalFareLabel =
                new JLabel("0.00");

        totalFareLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        totalFareLabel.setBounds(
                380,
                270,
                150,
                25
        );

        add(totalFareLabel);


        // ==========================================
        // CONFIRM BOOKING BUTTON
        // ==========================================

        JButton confirmButton =
                new JButton("Confirm Booking");

        confirmButton.setBounds(
                300,
                330,
                160,
                35
        );

        add(confirmButton);


        // ==========================================
        // BACK BUTTON
        // ==========================================

        JButton backButton =
                new JButton("Back");

        backButton.setBounds(
                480,
                330,
                120,
                35
        );

        add(backButton);


        // ==========================================
        // CONFIRM BUTTON ACTION
        // ==========================================

        confirmButton.addActionListener(e ->
                handleConfirm()
        );


        // ==========================================
        // BACK BUTTON ACTION
        // ==========================================

        backButton.addActionListener(e ->
                mainFrame.showCard(
                        MainFrame.RESULTS
                )
        );
    }


    // ==========================================
    // SET SELECTED FLIGHT
    // ==========================================

    public void setFlight(Flight flight) {

        this.selectedFlight = flight;


        totalFareLabel.setText(
                String.format(
                        "%.2f",
                        flight.getBaseFare()
                )
        );


        // Clear old input

        nameField.setText("");

        emailField.setText("");

        dobField.setText("");

        genderCombo.setSelectedIndex(0);

        seatCombo.setSelectedIndex(0);
    }


    // ==========================================
    // HANDLE CONFIRM BOOKING
    // ==========================================

    private void handleConfirm() {

        String name =
                nameField.getText().trim();

        String email =
                emailField.getText().trim();

        String dob =
                dobField.getText().trim();


        // ==========================================
        // CHECK EMPTY FIELDS
        // ==========================================

        if (name.isEmpty()
                || email.isEmpty()
                || dob.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all passenger details.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // ==========================================
        // GET SEAT
        // ==========================================

        String seat =
                (String) seatCombo.getSelectedItem();


        // ==========================================
        // GENERATE PNR
        // ==========================================

        String pnr =
                SampleData.generatePNR();


        // ==========================================
        // GET BOOKING DETAILS
        // ==========================================

        String travelDate =
                selectedFlight.getTravelDate();

        String bookingDate =
                "27-08-2026";


        // ==========================================
        // CREATE BOOKING OBJECT
        // ==========================================

        Booking booking =
                new Booking(
                        pnr,
                        selectedFlight.getFlightNumber(),
                        selectedFlight.getAirline(),
                        selectedFlight.getOrigin(),
                        selectedFlight.getDestination(),
                        name,
                        email,
                        travelDate,
                        seat,
                        selectedFlight.getBaseFare(),
                        "CONFIRMED",
                        bookingDate
                );


        // ==========================================
        // SAVE BOOKING TO DATABASE
        // ==========================================

        boolean bookingSaved =
                BookingDAO.addBooking(
                        booking
                );


        // ==========================================
        // CHECK DATABASE RESULT
        // ==========================================

        if (!bookingSaved) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking could not be saved.",
                    "Booking Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // ==========================================
        // BOOKING SUCCESSFUL
        // ==========================================

        JOptionPane.showMessageDialog(
                this,
                "Booking confirmed successfully!",
                "Booking Successful",
                JOptionPane.INFORMATION_MESSAGE
        );


        // ==========================================
        // GO TO CONFIRMATION
        // ==========================================

        mainFrame.goToConfirmation(
                booking
        );
    }
}