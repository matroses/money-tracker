package main.java.moneytracker.view.general;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.view.View;
import main.java.moneytracker.controller.OverviewController;

import java.util.HashMap;
import java.util.Map;

public class OverviewPane extends GridPane implements View {

    private final OverviewController controller;

    Label titleLabel, textLabel;

    CheckBox checkBox;

    public OverviewPane(OverviewController controller) {
        this.controller = controller;
        this.controller.setView(this);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);

        this.titleLabel = new Label("Overview");

        Map<Person, Map<Person, Float>> debtMap = this.controller.getDebtMap();
        this.textLabel = new Label(this.getOverviewText(debtMap));

        checkBox = new CheckBox("Simplify debts");
        checkBox.setOnAction(event -> {
            CheckBox cb = (CheckBox) event.getSource();

            if (cb.isSelected()) this.textLabel.setText(this.getOverviewText(this.controller.getSimplifiedDebtMap(this.controller.getDebtMap())));
            else this.textLabel.setText(this.getOverviewText(this.controller.getDebtMap()));
        });

        this.add(titleLabel, 0, 1, 1, 1);
        this.add(textLabel, 0, 2, 1, 1);
        this.add(checkBox, 0, 3, 1, 1);

        this.update();
    }

    public String getOverviewText(Map<Person, Map<Person, Float>> debtMap) {

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

        return text;
    }

    public void update() {
        if (this.checkBox.isSelected()) this.textLabel.setText(this.getOverviewText(this.controller.getSimplifiedDebtMap(this.controller.getDebtMap())));
        else this.textLabel.setText(this.getOverviewText(this.controller.getDebtMap()));
    }
}
