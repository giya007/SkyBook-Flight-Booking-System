package skybook;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class MyBookingsPanel extends JPanel {

    private MainFrame mainFrame;

    private JTable bookingTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JButton viewTicketButton;
    private JButton cancelButton;
    private JButton backButton;

    private String currentUserEmail;

    // Stores the actual Booking objects shown in the table
    private List<Booking> currentBookings;

    private final String[] columns = {
            "PNR",
            "Flight No.",
            "Passenger Name",
            "Travel Date",
            "Seat",
            "Total Paid",
            "Status",
            "Booking Date"
    };

    public MyBookingsPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(null);
        setBackground(Color.WHITE);

        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel = new JLabel(
                "My Bookings",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 24)
        );

        titleLabel.setBounds(
                0,
                15,
                850,
                35
        );

        add(titleLabel);


        // ==========================================
        // TABLE MODEL
        // ==========================================

        tableModel = new DefaultTableModel(
                columns,
                0
        ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };


        // ==========================================
        // TABLE
        // ==========================================

        bookingTable = new JTable(tableModel);

        bookingTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        bookingTable.setRowHeight(25);

        bookingTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        bookingTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        bookingTable.setFillsViewportHeight(false);

        // Cleaner grid look
        bookingTable.setShowGrid(false);

        bookingTable.setIntercellSpacing(
                new Dimension(0, 0)
        );

        bookingTable.setGridColor(
                new Color(230, 230, 230)
        );

        bookingTable.setSelectionBackground(
                new Color(210, 228, 255)
        );

        bookingTable.setSelectionForeground(
                Color.BLACK
        );


        // ==========================================
        // HEADER
        // ==========================================

        bookingTable.getTableHeader().setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        bookingTable.getTableHeader()
                .setReorderingAllowed(false);

        bookingTable.getTableHeader()
                .setBackground(
                        new Color(245, 245, 245)
                );

        bookingTable.getTableHeader()
                .setBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                1,
                                0,
                                new Color(210, 210, 210)
                        )
                );


        // ==========================================
        // CENTER ALIGN HEADER + CELLS
        // ==========================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0; i < columns.length; i++) {

            bookingTable.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        ((DefaultTableCellRenderer) bookingTable
                .getTableHeader()
                .getDefaultRenderer())
                .setHorizontalAlignment(
                        SwingConstants.CENTER
                );


        // ==========================================
        // COLUMN WIDTHS
        // ==========================================

        bookingTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        bookingTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(90);

        bookingTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(125);

        bookingTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(105);

        bookingTable.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(65);

        bookingTable.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(95);

        bookingTable.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(100);

        bookingTable.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(105);


        // ==========================================
        // SCROLL PANE
        // ==========================================

        scrollPane = new JScrollPane(
                bookingTable
        );

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)
                )
        );

        scrollPane.getViewport()
                .setBackground(Color.WHITE);

        scrollPane.setOpaque(true);

        styleScrollBar(
                scrollPane.getVerticalScrollBar()
        );

        styleScrollBar(
                scrollPane.getHorizontalScrollBar()
        );

        add(scrollPane);


        // ==========================================
        // VIEW E-TICKET BUTTON
        // ==========================================

        viewTicketButton = new JButton(
                "View E-Ticket"
        );

        viewTicketButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        add(viewTicketButton);


        // ==========================================
        // CANCEL BUTTON
        // ==========================================

        cancelButton = new JButton(
                "Cancel Booking"
        );

        cancelButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        add(cancelButton);


        // ==========================================
        // BACK BUTTON
        // ==========================================

        backButton = new JButton(
                "Back to Home"
        );

        backButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        add(backButton);


        // ==========================================
        // BUTTON ACTIONS
        // ==========================================

        viewTicketButton.addActionListener(e ->
                handleViewTicket()
        );

        cancelButton.addActionListener(e ->
                handleCancel()
        );

        backButton.addActionListener(e ->
                mainFrame.showCard(MainFrame.HOME)
        );


        updateTableLayout();
    }


    // ==========================================
    // SCROLLBAR STYLING
    // ==========================================

    private void styleScrollBar(JScrollBar scrollBar) {

        scrollBar.setPreferredSize(
                new Dimension(8, 8)
        );

        scrollBar.setUI(new BasicScrollBarUI() {

            @Override
            protected void configureScrollBarColors() {

                this.thumbColor =
                        new Color(190, 190, 190);

                this.trackColor =
                        Color.WHITE;
            }

            @Override
            protected JButton createDecreaseButton(
                    int orientation
            ) {

                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(
                    int orientation
            ) {

                return createZeroButton();
            }

            private JButton createZeroButton() {

                JButton button = new JButton();

                button.setPreferredSize(
                        new Dimension(0, 0)
                );

                button.setMinimumSize(
                        new Dimension(0, 0)
                );

                button.setMaximumSize(
                        new Dimension(0, 0)
                );

                return button;
            }
        });
    }


    // ==========================================
    // LOAD BOOKINGS FOR CURRENT USER
    // ==========================================

    public void loadBookings(String userEmail) {

        currentUserEmail = userEmail;

        tableModel.setRowCount(0);

        // Get only this user's bookings
        currentBookings =
                BookingDAO.getBookingsByEmail(
                        currentUserEmail
                );


        // ==========================================
        // ADD BOOKINGS TO TABLE
        // ==========================================

        for (Booking b : currentBookings) {

            tableModel.addRow(
                    new Object[] {

                            b.getPnr(),

                            b.getFlightNumber(),

                            b.getPassengerName(),

                            b.getTravelDate(),

                            b.getSeat(),

                            String.format(
                                    "%.2f",
                                    b.getTotalPaid()
                            ),

                            b.getStatus(),

                            b.getBookingDate()
                    }
            );
        }

        updateTableLayout();
    }


    // ==========================================
    // VIEW E-TICKET
    // ==========================================

    private void handleViewTicket() {

        int selectedRow =
                bookingTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a booking to view the E-Ticket.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Booking selectedBooking =
                currentBookings.get(selectedRow);

        // Open E-Ticket from My Bookings
        mainFrame.goToETicketFromMyBookings(
                selectedBooking
        );
    }


    // ==========================================
    // UPDATE TABLE LAYOUT
    // ==========================================

    private void updateTableLayout() {

        int rowCount =
                bookingTable.getRowCount();

        int rowHeight =
                bookingTable.getRowHeight();

        int headerHeight =
                bookingTable
                        .getTableHeader()
                        .getPreferredSize()
                        .height;

        int horizontalScrollBarHeight =
                scrollPane
                        .getHorizontalScrollBar()
                        .getPreferredSize()
                        .height;


        int visibleRows =
                Math.min(rowCount, 5);

        int tableHeight;

        if (rowCount == 0) {

            tableHeight =
                    headerHeight + 2;

        } else {

            tableHeight =
                    headerHeight
                    + (visibleRows * rowHeight)
                    + horizontalScrollBarHeight
                    + 2;
        }


        // ==========================================
        // TABLE
        // ==========================================

        scrollPane.setBounds(
                30,
                55,
                775,
                tableHeight
        );


        // ==========================================
        // BUTTONS
        // ==========================================

        int buttonY =
                55
                + tableHeight
                + 20;

        viewTicketButton.setBounds(
                245,
                buttonY,
                180,
                40
        );

        cancelButton.setBounds(
                435,
                buttonY,
                180,
                40
        );

        backButton.setBounds(
                625,
                buttonY,
                180,
                40
        );


        revalidate();
        repaint();
    }


    // ==========================================
    // CANCEL BOOKING
    // ==========================================

    private void handleCancel() {

        int selectedRow =
                bookingTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a booking to cancel.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // ==========================================
        // GET PNR
        // ==========================================

        String pnr =
                (String) tableModel.getValueAt(
                        selectedRow,
                        0
                );


        // ==========================================
        // GET STATUS
        // ==========================================

        String status =
                (String) tableModel.getValueAt(
                        selectedRow,
                        6
                );


        // ==========================================
        // ALREADY CANCELLED
        // ==========================================

        if (status.equals("CANCELLED")) {

            JOptionPane.showMessageDialog(
                    this,
                    "This booking is already cancelled.",
                    "Already Cancelled",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        // ==========================================
        // CONFIRM CANCELLATION
        // ==========================================

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Cancel booking " + pnr + "?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION
                );


        if (confirm == JOptionPane.YES_OPTION) {

            // ==========================================
            // UPDATE DATABASE
            // ==========================================

            boolean cancelled =
                    BookingDAO.cancelBooking(pnr);


            if (cancelled) {

                loadBookings(currentUserEmail);

                JOptionPane.showMessageDialog(
                        this,
                        "Booking " + pnr +
                                " has been cancelled.",
                        "Cancelled",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking could not be cancelled.",
                        "Cancellation Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}