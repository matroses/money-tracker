package main.java.moneytracker.view.general;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.view.View;
import main.java.moneytracker.controller.OverviewController;

import java.lang.invoke.LambdaConversionException;
import java.util.HashMap;
import java.util.Map;

public class OverviewPane extends GridPane implements View {

    private final OverviewController controller;

    Label titleLabel, textLabel;

    public OverviewPane(OverviewController controller) {
        this.controller = controller;
        this.controller.setView(this);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);

        this.titleLabel = new Label("Overview");
        this.textLabel = new Label("");

        this.add(titleLabel, 0, 0, 1, 1);
        this.setupTextLabel();
        this.add(textLabel, 0, 2, 1, 1);
        this.update();
    }

    public void setupTextLabel() {
        Map<Person, Map<Person, Float>> debtMap = this.controller.getDebtMap();
        this.controller.logDebtMap(debtMap);

        String text = "";

        for (Map.Entry<Person, Map<Person, Float>> debtee: debtMap.entrySet()) {
            Map<Person, Float> debtorMap = new HashMap<>();
            int debtorAmount = 0;

            for (Map.Entry<Person, Float> debtor: debtee.getValue().entrySet()) {
                if (debtor.getValue() > 0f) {
                    debtorMap.put(debtor.getKey(), debtor.getValue());
                    debtorAmount++;
                }
            }
            if (!debtorMap.isEmpty()) {
                int debtorCount = 0;
                text += "\n" + debtee.getKey().getFullName() + " owes ";

                for (Map.Entry<Person, Float> debtor: debtorMap.entrySet()) {
                    if (debtorCount > 0 && debtorCount < debtorAmount) text += " and ";
                    text += debtor.getKey().getFullName() + " €\u200E" + debtor.getValue();
                    debtorCount ++;
                }
            }
        }

        titleLabel.setText(text);
    }

    public void update() {
        // Update the overview pane, called by the controller
        //personTable.setItems(FXCollections.observableArrayList(controller.getDebtPerPerson()));
        //personTable.refresh();
    }
}
