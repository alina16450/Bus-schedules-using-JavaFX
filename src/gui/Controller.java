package gui;

import Domain.Routes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.Service;

import java.util.List;
import java.util.Set;

public class Controller {

    private Service service;
    private ObservableList<Routes> routes;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<Routes> AllRoutes;
    @FXML
    private ListView<Routes> filtered;
    @FXML
    private ComboBox<String> Sources;
    @FXML
    private ComboBox<String> Destinations;
    @FXML
    private TextField ticketnums;
    @FXML
    private Label price, message;
    @FXML
    private Button bookbutton;


    public void initialize() {
        List<Routes> serviceRoutes = service.sortedRoutes();

        routes = FXCollections.observableList(serviceRoutes);
        AllRoutes.setItems(routes);

        Sources.getItems().setAll(service.getSources());
        Sources.setOnAction(this::findDestinations);
        Destinations.setOnAction(this::findRoutes);
        bookbutton.setOnAction(this::onPriceEntered);

    }

    public void findDestinations(javafx.event.ActionEvent actionEvent) {
        String choice = Sources.getValue();
        Set<String> dests = service.getDestinations(choice);
        Destinations.getItems().setAll(dests);
    }

    public void findRoutes(javafx.event.ActionEvent actionEvent) {
        String choice1 = Sources.getValue();
        String choice2 = Destinations.getValue();
        List<Routes> serviceRoutes = service.getRoutes(choice1, choice2);
        routes = FXCollections.observableList(serviceRoutes);
        filtered.setItems(routes);
    }

    public void onPriceEntered(javafx.event.ActionEvent actionEvent) {
        Routes selected = filtered.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(ticketnums.getText());
        price.setText(String.valueOf(service.bookingCalc(selected, t)));
        AllRoutes.refresh();
        filtered.refresh();
        message.setText("Your tickets were booked successfully.");

    }

}
