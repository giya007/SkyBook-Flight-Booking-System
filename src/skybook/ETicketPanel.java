package skybook;

import javax.swing.*;
import java.awt.*;

public class ETicketPanel extends JPanel {

    private MainFrame mainFrame;

    private JLabel pnrLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel flightNumberLabel;
    private JLabel airlineLabel;
    private JLabel originLabel;
    private JLabel destinationLabel;
    private JLabel travelDateLabel;
    private JLabel seatLabel;
    private JLabel totalPaidLabel;
    private JLabel statusLabel;

    // Stores where the E-Ticket was opened from
    private String previousPage;

    public ETicketPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(null);


        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel = new JLabel(
                "E-Ticket",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 28)
        );

        titleLabel.setBounds(
                0,
                30,
                850,
                45
        );

        add(titleLabel);


        // ==========================================
        // VALUE LABELS
        // ==========================================

        pnrLabel = createValueLabel();
        nameLabel = createValueLabel();
        emailLabel = createValueLabel();
        flightNumberLabel = createValueLabel();
        airlineLabel = createValueLabel();
        originLabel = createValueLabel();
        destinationLabel = createValueLabel();
        travelDateLabel = createValueLabel();
        seatLabel = createValueLabel();
        totalPaidLabel = createValueLabel();
        statusLabel = createValueLabel();


        // ==========================================
        // BOOKING DETAILS
        // ==========================================

        int y = 95;

        y = addRow(y, "PNR", pnrLabel);
        y = addRow(y, "Passenger Name", nameLabel);
        y = addRow(y, "Passenger Email", emailLabel);
        y = addRow(y, "Flight Number", flightNumberLabel);
        y = addRow(y, "Airline", airlineLabel);
        y = addRow(y, "Origin", originLabel);
        y = addRow(y, "Destination", destinationLabel);
        y = addRow(y, "Travel Date", travelDateLabel);
        y = addRow(y, "Seat", seatLabel);
        y = addRow(y, "Total Paid", totalPaidLabel);
        y = addRow(y, "Status", statusLabel);


        // ==========================================
        // BACK BUTTON
        // ==========================================

        JButton backButton = new JButton("Back");

        backButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        backButton.setBounds(
                365,
                y + 20,
                120,
                40
        );

        add(backButton);


        // ==========================================
        // BACK ACTION
        // ==========================================

        backButton.addActionListener(e -> {

            if (MainFrame.BOOKINGS.equals(previousPage)) {

                mainFrame.showCard(
                        MainFrame.BOOKINGS
                );

            } else {

                mainFrame.showCard(
                        MainFrame.CONFIRMATION
                );
            }
        });
    }


    // ==========================================
    // CREATE VALUE LABEL
    // ==========================================

    private JLabel createValueLabel() {

        JLabel label = new JLabel();

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        return label;
    }


    // ==========================================
    // ADD ROW
    // ==========================================

    private int addRow(
            int y,
            String labelText,
            JLabel valueLabel
    ) {

        JLabel captionLabel = new JLabel(labelText);

        captionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        captionLabel.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        captionLabel.setBounds(
                295,
                y,
                150,
                25
        );

        add(captionLabel);


        JLabel colonLabel = new JLabel(":");

        colonLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        colonLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        colonLabel.setBounds(
                445,
                y,
                20,
                25
        );

        add(colonLabel);


        valueLabel.setBounds(
                480,
                y,
                300,
                25
        );

        add(valueLabel);


        return y + 30;
    }


    // ==========================================
    // SET BOOKING FROM CONFIRMATION
    // ==========================================

    public void setBooking(Booking booking) {

        previousPage = MainFrame.CONFIRMATION;

        setBookingDetails(booking);
    }


    // ==========================================
    // SET BOOKING FROM MY BOOKINGS
    // ==========================================

    public void setBookingFromMyBookings(Booking booking) {

        previousPage = MainFrame.BOOKINGS;

        setBookingDetails(booking);
    }


    // ==========================================
    // SET BOOKING DETAILS
    // ==========================================

    private void setBookingDetails(Booking booking) {

        pnrLabel.setText(
                booking.getPnr()
        );

        nameLabel.setText(
                booking.getPassengerName()
        );

        emailLabel.setText(
                booking.getPassengerEmail()
        );

        flightNumberLabel.setText(
                booking.getFlightNumber()
        );

        airlineLabel.setText(
                booking.getAirline()
        );

        originLabel.setText(
                booking.getOrigin()
        );

        destinationLabel.setText(
                booking.getDestination()
        );

        travelDateLabel.setText(
                booking.getTravelDate()
        );

        seatLabel.setText(
                booking.getSeat()
        );

        totalPaidLabel.setText(
                String.format(
                        "%.2f",
                        booking.getTotalPaid()
                )
        );

        statusLabel.setText(
                booking.getStatus()
        );
    }
}