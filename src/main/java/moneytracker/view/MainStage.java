package main.java.moneytracker.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.moneytracker.model.MoneyTrackerApp;

public class MainStage {

    private Stage stage = new Stage();

    public MainStage(MoneyTrackerApp moneyTracker) {
        stage.setTitle("Money Tracker Application");
        stage.initStyle(StageStyle.UTILITY);
        stage.setX(630);
        stage.setY(20);
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        BorderPane borderPane = new MainPane(moneyTracker);
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
