package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import videoPlayer.VideoPlayer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VideoPlayer videoPlayer = new VideoPlayer();
        primaryStage.setTitle("Video Player");
        Scene scene = new Scene(videoPlayer, 1200, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
