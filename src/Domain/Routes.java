package Domain;

import java.time.Duration;
import java.time.LocalTime;

public class Routes {
    private int Id;
    private String SourceCity;
    private String DestinationCity;
    private LocalTime DepartureTime;
    private LocalTime ArrivalTime;
    private int AvailableSeats;
    private double TicketPrice;

    public Routes(int id, String sourceCity, String destinationCity, LocalTime departureTime,
                  LocalTime arrivalTime, int availableSeats, double ticketPrice) {
        Id = id;
        SourceCity = sourceCity;
        DestinationCity = destinationCity;
        DepartureTime = departureTime;
        ArrivalTime = arrivalTime;
        AvailableSeats = availableSeats;
        TicketPrice = ticketPrice;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSourceCity() {
        return SourceCity;
    }

    public void setSourceCity(String sourceCity) {
        SourceCity = sourceCity;
    }

    public String getDestinationCity() {
        return DestinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        DestinationCity = destinationCity;
    }

    public LocalTime getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        DepartureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public int getAvailableSeats() {
        return AvailableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        AvailableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return TicketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        TicketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        Duration duration = Duration.between(DepartureTime, ArrivalTime);
        return "Routes{" +
                "id=" + Id +
                ", SourceCity='" + SourceCity + '\'' +
                ", DestinationCity='" + DestinationCity + '\'' +
                ", DepartureTime=" + DepartureTime +
                ", ArrivalTime=" + ArrivalTime +
                ", Duration= " + duration +
                ", AvailableSeats=" + AvailableSeats +
                ", TicketPrice=" + TicketPrice +
                '}';
    }
}
