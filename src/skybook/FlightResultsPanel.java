package skybook;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FlightResultsPanel extends JPanel {

    private MainFrame mainFrame;

    private JTable flightTable;
    private DefaultTableModel tableModel;

    private List<Flight> flights = new ArrayList<>();

    private JScrollPane scrollPane;

    private JButton selectButton;
    private JButton backButton;

    private final String[] columns = {
            "Airline",
            "Flight No.",
            "Origin",
            "Destination",
            "Departure",
            "Arrival",
            "Base Fare",
            "Class"
    };


    public FlightResultsPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        // ==========================================
        // PANEL
        // ==========================================

        setLayout(null);
        setBackground(Color.WHITE);


        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel = new JLabel(
                "Available Flights",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Serif", Font.BOLD, 24)
        );

        titleLabel.setBounds(
                200,
                20,
                450,
                40
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

        flightTable = new JTable(tableModel);

        flightTable.setFont(
                new Font("SansSerif", Font.PLAIN, 12)
        );

        flightTable.setRowHeight(25);

        flightTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // --- cleaner grid look ---
        flightTable.setShowGrid(false);
        flightTable.setIntercellSpacing(new Dimension(0, 0));
        flightTable.setGridColor(new Color(230, 230, 230));
        flightTable.setSelectionBackground(new Color(210, 228, 255));
        flightTable.setSelectionForeground(Color.BLACK);


        // ==========================================
        // TABLE HEADER
        // ==========================================

        flightTable.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );

        flightTable.getTableHeader()
                .setReorderingAllowed(false);

        flightTable.getTableHeader()
                .setBackground(new Color(245, 245, 245));

        flightTable.getTableHeader()
                .setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 210, 210)));


        // ==========================================
        // CENTER-ALIGN HEADER + CELL CONTENT
        // ==========================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0; i < columns.length; i++) {

            flightTable.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        ((DefaultTableCellRenderer) flightTable
                .getTableHeader()
                .getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);


        // ==========================================
        // COLUMN RESIZING
        // ==========================================

        flightTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );


        // ==========================================
        // COLUMN WIDTHS
        // ==========================================

        flightTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(100);

        flightTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(100);

        flightTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(100);

        flightTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(110);

        flightTable.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(95);

        flightTable.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(90);

        flightTable.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(105);

        flightTable.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(85);


        // ==========================================
        // SCROLL PANE
        // ==========================================

        scrollPane = new JScrollPane(
                flightTable
        );

        // --- remove ugly default sunken border ---
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        // --- match viewport background so it doesn't look "boxed" ---
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setOpaque(true);

        // --- thin custom scrollbars instead of default OS style ---
        styleScrollBar(scrollPane.getVerticalScrollBar());
        styleScrollBar(scrollPane.getHorizontalScrollBar());

        add(scrollPane);


        // ==========================================
        // SELECT FLIGHT BUTTON
        // ==========================================

        selectButton =
                new JButton("Select Flight");

        selectButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        add(selectButton);


        // ==========================================
        // BACK BUTTON
        // ==========================================

        backButton =
                new JButton("Back");

        backButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        add(backButton);


        // ==========================================
        // SELECT BUTTON ACTION
        // ==========================================

        selectButton.addActionListener(e ->
                selectFlight()
        );


        // ==========================================
        // BACK BUTTON ACTION
        // ==========================================

        backButton.addActionListener(e ->
                mainFrame.showCard(MainFrame.HOME)
        );


        // ==========================================
        // INITIAL POSITION
        // ==========================================

        updateTableLayout();
    }


    // ==========================================
    // SCROLLBAR STYLING HELPER
    // ==========================================

    private void styleScrollBar(JScrollBar scrollBar) {

        scrollBar.setPreferredSize(new Dimension(8, 8));

        scrollBar.setUI(new BasicScrollBarUI() {

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(190, 190, 190);
                this.trackColor = Color.WHITE;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
    }


    // ==========================================
    // UPDATE TABLE AND BUTTON POSITIONS
    // ==========================================

    private void updateTableLayout() {

        int rowCount = flights.size();

        int rowHeight = flightTable.getRowHeight();

        int headerHeight =
                flightTable.getTableHeader()
                        .getPreferredSize()
                        .height;


        // ==========================================
        // CALCULATE TABLE HEIGHT
        // ==========================================

        int tableHeight =
                headerHeight
                + (rowCount * rowHeight)
                + 2;


        // Keep a small minimum when there are no results

        if (rowCount == 0) {
            tableHeight = headerHeight + 2;
        }


        // ==========================================
        // LIMIT TABLE HEIGHT
        // ==========================================

        if (tableHeight > 250) {
            tableHeight = 250;
        }


        // ==========================================
        // TABLE POSITION
        // ==========================================

        scrollPane.setBounds(
                25,
                85,
                785,
                tableHeight
        );


        // ==========================================
        // BUTTON POSITION
        // ==========================================

        int buttonY =
                85 + tableHeight + 25;


        selectButton.setBounds(
                285,
                buttonY,
                180,
                40
        );


        backButton.setBounds(
                485,
                buttonY,
                120,
                40
        );

        revalidate();
        repaint();
    }


    // ==========================================
    // ADD FLIGHT TO TABLE
    // ==========================================

    private void addFlightToTable(
            Flight flight
    ) {

        tableModel.addRow(new Object[] {

                flight.getAirline(),

                flight.getFlightNumber(),

                flight.getOrigin(),

                flight.getDestination(),

                flight.getDepartureTime(),

                flight.getArrivalTime(),

                String.format(
                        "%.2f",
                        flight.getBaseFare()
                ),

                flight.getFlightClass()
        });
    }


    // ==========================================
    // LOAD ALL FLIGHTS FROM DATABASE
    // ==========================================

    public void loadFlights() {

        tableModel.setRowCount(0);

        flights =
                FlightDAO.getAllFlights();

        for (Flight flight : flights) {

            addFlightToTable(flight);
        }

        updateTableLayout();
    }


    // ==========================================
    // LOAD SEARCH RESULTS
    // ==========================================

    public void loadSearchResults(
            List<Flight> searchResults
    ) {

        tableModel.setRowCount(0);

        flights = searchResults;

        for (Flight flight : flights) {

            addFlightToTable(flight);
        }

        updateTableLayout();
    }


    // ==========================================
    // SELECT FLIGHT
    // ==========================================

    private void selectFlight() {

        int selectedRow =
                flightTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a flight.",
                    "No Flight Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Flight selectedFlight =
                flights.get(selectedRow);


        mainFrame.goToPassengerDetails(
                selectedFlight
        );
    }
}