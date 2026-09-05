package skybook;

import javax.swing.*;
import java.awt.*;

public class BookingConfirmationPanel extends JPanel {

    private MainFrame mainFrame;

    private Booking currentBooking;

    public BookingConfirmationPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(null);

        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel = new JLabel(
                "Booking Confirmed!",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 30)
        );

        titleLabel.setForeground(
                new Color(0, 128, 0)
        );

        titleLabel.setBounds(
                120,
                30,
                610,
                45
        );

        add(titleLabel);


        // ==========================================
        // SUCCESS MESSAGE
        // ==========================================

        JLabel messageLabel = new JLabel(
                "Your flight has been successfully booked.",
                SwingConstants.CENTER
        );

        messageLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 18)
        );

        messageLabel.setBounds(
                100,
                90,
                650,
                35
        );

        add(messageLabel);


        // ==========================================
        // VIEW E-TICKET BUTTON
        // ==========================================

        JButton ticketButton =
                new JButton("View E-Ticket");

        ticketButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        ticketButton.setBounds(
                240,
                150,
                370,
                55
        );

        add(ticketButton);


        // ==========================================
        // BACK TO HOME BUTTON
        // ==========================================

        JButton backHomeButton =
                new JButton("Back to Home");

        backHomeButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        backHomeButton.setBounds(
                240,
                230,
                370,
                55
        );

        add(backHomeButton);


        // ==========================================
        // VIEW E-TICKET ACTION
        // ==========================================

        ticketButton.addActionListener(e -> {

            if (currentBooking != null) {

                mainFrame.goToETicket(currentBooking);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking information is not available.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // ==========================================
        // BACK TO HOME ACTION
        // ==========================================

        backHomeButton.addActionListener(e ->
                mainFrame.showCard(MainFrame.HOME)
        );
    }


    // ==========================================
    // SET BOOKING
    // ==========================================

    public void setBooking(Booking booking) {

        this.currentBooking = booking;
    }
}