package main.java.moneytracker.view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import main.java.moneytracker.controller.OverviewController;
import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.view.general.OverviewPane;

import java.util.ArrayList;

public class MainPane extends BorderPane {

    private final MoneyTrackerApp moneyTracker;
    private final TabPane tabPane;
    private final ArrayList<Tab> tabs;

    public MainPane(MoneyTrackerApp moneyTracker) {
        this.moneyTracker = moneyTracker;

        this.tabPane = new TabPane();
        this.tabs = new ArrayList<>();

        this.tabs.add(new Tab("Overview", new OverviewPane(new OverviewController(moneyTracker))));
        this.tabs.add(new Tab("Tickets", null));
        this.tabs.add(new Tab("People", null));

        this.initializeTabs();
        this.setCenter(tabPane);
    }


    /**
     * Adds all the tabs to the tabPane
     */
    private void initializeTabs() {
        this.tabs.forEach(tab -> {
            tab.setClosable(false);
            tabPane.getTabs().add(tab);
        });
    }
}
