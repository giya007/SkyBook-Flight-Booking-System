package skybook;

/**
 * Holds simple constant data used by the UI.
 *
 * Flights and bookings are now handled through
 * JDBC/MySQL and DAO classes.
 */
public class SampleData {

    // Cities shown in the From/To combo boxes
    public static final String[] CITIES = {
            "Delhi",
            "Mumbai",
            "Bangalore",
            "Chennai",
            "Kolkata",
            "Hyderabad"
    };

    // Genders shown in the passenger details form
    public static final String[] GENDERS = {
            "Male",
            "Female",
            "Other"
    };

    // Seats shown in the passenger details form
    public static final String[] SEATS = {
            "A1",
            "A2",
            "B1",
            "B2",
            "C1",
            "C2",
            "D1",
            "D2"
    };

    // Generates a simple PNR for a new booking
    public static String generatePNR() {

        int number =
                1000 + (int) (Math.random() * 9000);

        return "PNR" + number;
    }
}