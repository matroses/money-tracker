package main.java.moneytracker.view.general;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.view.View;
import main.java.moneytracker.controller.OverviewController;

public class OverviewPane extends GridPane implements View {

    private final OverviewController controller;

    Label testLabel = new Label("The calculated bill will be shown here.");

    public OverviewPane(OverviewController controller) {
        this.controller = controller;
        this.controller.setView(this);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);

        this.add(testLabel, 0, 0);
    }

    public void update() {
        // Update the overview pane, called by the controller
    }
}
