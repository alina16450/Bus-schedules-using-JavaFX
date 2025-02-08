package repository;

import Domain.Routes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class RoutesRepository extends Repository {
    private Map<Integer, Routes> routes = new HashMap<>();

    public RoutesRepository() {
        super();
        readFromDb();
    }

    private void readFromDb() {
        String query = "SELECT * FROM Routes";
        try{
            Connection con = getConnection();
            try{
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int Id = rs.getInt("Id");
                    String SourceCity = rs.getString("SourceCity");
                    String DestinationCity = rs.getString("DestinationCity");
                    LocalTime DepartureTime = LocalTime.parse(rs.getString("DepartureTime"));
                    LocalTime ArrivalTime = LocalTime.parse(rs.getString("ArrivalTime"));
                    int AvailableSeats = rs.getInt("AvailableSeats");
                    double TicketPrice = rs.getDouble("TicketPrice");

                    Routes route = new Routes(Id, SourceCity, DestinationCity, DepartureTime, ArrivalTime, AvailableSeats, TicketPrice);
                    routes.put(Id, route);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToDb(Routes route) {
        String query = "insert into Routes(Id, SourceCity, DestinationCity, DepartureTime, ArrivalTime, AvailableSeats, TicketPrice) values(?, ?, ?, ?, ?, ?, ?) ";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, route.getId());
            ps.setString(2, route.getSourceCity());
            ps.setString(3, route.getDestinationCity());
            ps.setString(4, route.getArrivalTime().toString());
            ps.setString(5, route.getDepartureTime().toString());
            ps.setInt(6, route.getAvailableSeats());
            ps.setDouble(7, route.getTicketPrice());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFromDB(int id) {
        String query = "delete from Routes where Id = ?";
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void add(int id, Routes route) {
        routes.put(id, route);
        writeToDb(route);
    }

    public void update(int id, Routes route) {
        Routes oldRoute = routes.get(id);
        oldRoute.setSourceCity(route.getSourceCity());
        oldRoute.setDestinationCity(route.getDestinationCity());
        oldRoute.setDepartureTime(route.getDepartureTime());
        oldRoute.setArrivalTime(route.getArrivalTime());
        oldRoute.setAvailableSeats(route.getAvailableSeats());
        oldRoute.setTicketPrice(route.getTicketPrice());
        deleteFromDB(id);
        writeToDb(oldRoute);
    }

    public Iterable<Routes> findAll() {
        return routes.values();
    }


}
