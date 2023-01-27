package moneytracker.view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import moneytracker.controller.people.PeopleOverviewController;
import moneytracker.controller.tickets.CreateTicketController;
import moneytracker.controller.tickets.TicketsOverviewController;
import moneytracker.controller.OverviewController;
import moneytracker.model.MoneyTrackerApp;
import moneytracker.view.general.OverviewPane;
import moneytracker.view.people.PeopleOverviewPane;
import moneytracker.view.tickets.TicketsOverviewPane;

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
        this.tabs.add(new Tab("Tickets", new TicketsOverviewPane(new TicketsOverviewController(moneyTracker), new CreateTicketController(moneyTracker))));
        this.tabs.add(new Tab("People", new PeopleOverviewPane(new PeopleOverviewController(moneyTracker))));

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
