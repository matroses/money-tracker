package moneytracker.view.general;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import moneytracker.controller.OverviewController;
import moneytracker.controller.SettingsController;
import moneytracker.model.Person;
import moneytracker.view.View;

import java.util.Map;

public class SettingsPane extends GridPane implements View {

    private final SettingsController controller;

    private final Button saveButton = new Button("Save database");

    public SettingsPane(SettingsController controller) {
        this.controller = controller;
        this.controller.setView(this);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);

        this.saveButton.setOnAction(event -> this.controller.saveDatabase());

        this.add(new Label("Settings"), 0, 0, 1, 1);
        this.add(this.saveButton, 0, 1);
        this.update();
    }

    public void update() {

    }
}
