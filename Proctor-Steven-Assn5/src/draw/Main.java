package draw;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Create main BorderPane
        BorderPane mainPane = new BorderPane();

        //Initialize Toolbar and add to top of BorderPane
        ToolPane toolPane = new ToolPane();
        mainPane.setTop(toolPane);

        //Add new DrawingPane to BorderPane and link to Toolbar
        mainPane.setCenter(new DrawingPane(toolPane));

        //Add BorderPane to main scene
        Scene scene = new Scene(mainPane);

        //Add Scene to Stage, disable resizing, set Stage Title
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Drawing Tablet");

        //Display GUI
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
