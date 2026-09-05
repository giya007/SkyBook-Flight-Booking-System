package skybook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public static boolean addBooking(Booking booking) {

        String sql =
                "INSERT INTO bookings " +
                "(pnr, flight_number, airline, origin, destination, " +
                "passenger_name, passenger_email, travel_date, seat, " +
                "total_paid, status, booking_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, booking.getPnr());
            ps.setString(2, booking.getFlightNumber());
            ps.setString(3, booking.getAirline());
            ps.setString(4, booking.getOrigin());
            ps.setString(5, booking.getDestination());
            ps.setString(6, booking.getPassengerName());
            ps.setString(7, booking.getPassengerEmail());
            ps.setString(8, booking.getTravelDate());
            ps.setString(9, booking.getSeat());
            ps.setDouble(10, booking.getTotalPaid());
            ps.setString(11, booking.getStatus());
            ps.setString(12, booking.getBookingDate());

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            return rows > 0;

        } catch (Exception e) {

            System.out.println("ERROR WHILE ADDING BOOKING:");
            e.printStackTrace();

            return false;
        }
    }


    public static List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT * FROM bookings";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking(
                        rs.getString("pnr"),
                        rs.getString("flight_number"),
                        rs.getString("airline"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("passenger_name"),
                        rs.getString("passenger_email"),
                        rs.getString("travel_date"),
                        rs.getString("seat"),
                        rs.getDouble("total_paid"),
                        rs.getString("status"),
                        rs.getString("booking_date")
                );

                bookings.add(booking);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("ERROR WHILE GETTING BOOKINGS:");
            e.printStackTrace();
        }

        return bookings;
    }


    // ==========================================
    // GET BOOKINGS FOR ONE USER
    // ==========================================

    public static List<Booking> getBookingsByEmail(String email) {

        List<Booking> bookings = new ArrayList<>();

        String sql =
                "SELECT * FROM bookings " +
                "WHERE passenger_email = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking(
                        rs.getString("pnr"),
                        rs.getString("flight_number"),
                        rs.getString("airline"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("passenger_name"),
                        rs.getString("passenger_email"),
                        rs.getString("travel_date"),
                        rs.getString("seat"),
                        rs.getDouble("total_paid"),
                        rs.getString("status"),
                        rs.getString("booking_date")
                );

                bookings.add(booking);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                    "ERROR WHILE GETTING USER BOOKINGS:"
            );

            e.printStackTrace();
        }

        return bookings;
    }


    public static boolean cancelBooking(String pnr) {

        String sql =
                "UPDATE bookings " +
                "SET status = ? " +
                "WHERE pnr = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "CANCELLED");
            ps.setString(2, pnr);

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            return rows > 0;

        } catch (Exception e) {

            System.out.println(
                    "ERROR WHILE CANCELLING BOOKING:"
            );

            e.printStackTrace();

            return false;
        }
    }
}