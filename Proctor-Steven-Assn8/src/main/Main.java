package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mineFinderLoader = new FXMLLoader(getClass().getResource("../view/MineFinder.fxml"));
        Parent root = mineFinderLoader.load();
        primaryStage.setTitle("Mine Finder");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setX(50);
        primaryStage.setY(20);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
