package main.java.moneytracker.application;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.view.MainStage;

public class MoneyTrackerMain extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MoneyTrackerApp moneyTracker = new MoneyTrackerApp();

        MainStage mainWindow = new MainStage(moneyTracker);
    }
}
