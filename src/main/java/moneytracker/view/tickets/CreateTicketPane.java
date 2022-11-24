package main.java.moneytracker.view.tickets;

import main.java.moneytracker.controller.Controller;
import main.java.moneytracker.view.View;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class CreateTicketPane extends GridPane implements View {

    public CreateTicketPane(Controller controller) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
    }

    @Override
    public void update() {

    }
}
