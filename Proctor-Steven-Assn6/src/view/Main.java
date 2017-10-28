package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for the Employee Directory that Showcases Polymorphism, Abstract Classes, and Interfaces.
 */
public class Main extends Application {

    /**
     * Sets title and size of the stage. Adds a new DirectoryApp to the stage, and displays the stage.
     * @param primaryStage The main stage of the app.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Directory");
        primaryStage.setScene(new Scene(new DirectoryApp(), 500, 400));
        primaryStage.show();
    }

    /**
     * Entry point for program
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
