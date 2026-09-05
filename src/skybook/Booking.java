package skybook;

/**
 * Simple data holder for a booking.
 * No database involved yet — bookings live only in memory
 * (see SampleData.java) for the duration the app is running.
 */
public class Booking {

    private String pnr;
    private String flightNumber;
    private String airline;
    private String origin;
    private String destination;
    private String passengerName;
    private String passengerEmail;
    private String travelDate;
    private String seat;
    private double totalPaid;
    private String status;
    private String bookingDate;

    public Booking(String pnr, String flightNumber, String airline, String origin, String destination,
                   String passengerName, String passengerEmail, String travelDate, String seat,
                   double totalPaid, String status, String bookingDate) {
        this.pnr = pnr;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.travelDate = travelDate;
        this.seat = seat;
        this.totalPaid = totalPaid;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public String getPnr() {
        return pnr;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public String getSeat() {
        return seat;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookingDate() {
        return bookingDate;
    }
}
