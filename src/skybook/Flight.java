package skybook;

/**
 * Simple data holder for a flight.
 * No database involved yet — objects of this class are created
 * from hardcoded sample data in SampleData.java.
 */
public class Flight {

    private String airline;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String travelDate;
    private double baseFare;
    private String flightClass;

    public Flight(String airline, String flightNumber, String origin, String destination,
                  String departureTime, String arrivalTime, String travelDate,
                  double baseFare, String flightClass) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelDate = travelDate;
        this.baseFare = baseFare;
        this.flightClass = flightClass;
    }

    public String getAirline() {
        return airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public String getFlightClass() {
        return flightClass;
    }
}
