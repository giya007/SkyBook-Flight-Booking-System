package skybook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    // ==============================
    // GET ALL FLIGHTS
    // ==============================
    public static List<Flight> getAllFlights() {

        List<Flight> flights = new ArrayList<>();

        String sql = "SELECT * FROM flights";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Flight flight = new Flight(
                        rs.getString("airline"),
                        rs.getString("flight_number"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getString("travel_date"),
                        rs.getDouble("base_fare"),
                        rs.getString("flight_class")
                );

                flights.add(flight);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return flights;
    }


    // ==============================
    // SEARCH FLIGHTS
    // ==============================
    public static List<Flight> searchFlights(
            String origin,
            String destination,
            String travelDate) {

        List<Flight> flights =
                new ArrayList<>();

        String sql =
                "SELECT * FROM flights " +
                "WHERE origin = ? " +
                "AND destination = ? " +
                "AND travel_date = ?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, origin);
            ps.setString(2, destination);
            ps.setString(3, travelDate);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Flight flight = new Flight(
                        rs.getString("airline"),
                        rs.getString("flight_number"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getString("travel_date"),
                        rs.getDouble("base_fare"),
                        rs.getString("flight_class")
                );

                flights.add(flight);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return flights;
    }
}