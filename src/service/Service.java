package service;

import Domain.Routes;
import repository.Repository;
import repository.RoutesRepository;

import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private RoutesRepository repo;

    public Service(RoutesRepository repo) {
        this.repo = repo;
    }

    public List<Routes> sortedRoutes() {
        List<Routes> routes = new ArrayList<>();
        for (Routes route : repo.findAll()) {
            routes.add(route);
        }
        return routes.stream().sorted(Comparator.comparing(Routes::getDestinationCity).thenComparing(Routes::getDepartureTime)).collect(Collectors.toList());
    }

    public Set<String> getSources() {
        Set<String> sources = new HashSet<>();
        for (Routes route : repo.findAll()) {
            sources.add(route.getSourceCity());
        }
        return sources;
    }

    public Set<String> getDestinations(String sourceCity) {
        Set<String> destinations = new HashSet<>();
        for (Routes route : repo.findAll()) {
            if (route.getSourceCity().equals(sourceCity)) {
                destinations.add(route.getDestinationCity());
            }
        }
        return destinations;
    }

    public List<Routes> getRoutes(String sourceCity, String destinationCity) {
        List<Routes> routes = new ArrayList<>();
        for (Routes route : repo.findAll()) {
            routes.add(route);
        }
        return routes.stream().filter(route -> {
                    return route.getDestinationCity().equals(destinationCity) && route.getSourceCity().equals(sourceCity);
                })
                .sorted(Comparator.comparing(Routes::getDestinationCity).thenComparing(Routes::getDepartureTime)).collect(Collectors.toList());
    }

    public double bookingCalc(Routes bookedRoute, int numTickets) {
        double price = 0;

        for (Routes route : repo.findAll()) {
            if (route == bookedRoute) {
                int availableSeats = bookedRoute.getAvailableSeats();
                if (numTickets > availableSeats) {
                    throw new RuntimeException("Not enough available seats");
                } else {

                    bookedRoute.setAvailableSeats(availableSeats - numTickets);
                    price = bookedRoute.getTicketPrice();
                    repo.update(route.getId(), bookedRoute);
                    return price * numTickets;
                    }
                }
            }
        return price;
    }

}
