package videoPlayer;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * VideoPlayer is used to play .mp4 video files. Allows simple controls over the video such as play, pause, stop,
 * fast forward, rewind (currently unsupported) and scrubbing through the video via a slider. Also gives the user control
 * over the media volume (does not change the system volume) and allows the user to toggle the video between full screen
 * and a regular window.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class VideoPlayer extends StackPane {

    /**
     * MediaPlayer used to manipulate the media on a code level
     */
    private MediaPlayer mediaPlayer;

    /**
     * MediaView used to display the media to the user
     */
    private MediaView mediaView;

    /**
     * ControlBar that allows the user to control the video
     */
    private ControlBar controlBar;

    /**
     * MenuBar used to execute commands in the program
     */
    private MenuBar menuBar;

    /**
     * Timers used to update the video slider and control visibility
     */
    private Timer controlTimer, sliderTimer;

    /**
     * Boolean used to track if the video is playing
     */
    private boolean running;

    /**
     * Default constructor for the video player. Calls initialization methods for the controlBar and menuBar.
     */
    public VideoPlayer() {
        initMenuBar();
        initControlBar();

        this.setStyle("-fx-background-color: black");
        this.setOnMouseMoved(this::displayControls);
        this.setOnKeyPressed(this::checkKey);
    }

    /**
     * Loads the controlBar from a .fxml file. Adds the control bar to the pane and sets all event handlers.
     */
    private void initControlBar() {
        Node controller = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/videoPlayer/ControlBar.fxml"));

        try {
            controller = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getChildren().add(controller);
        setAlignment(controller, Pos.BOTTOM_CENTER);
        setMargin(controller, new Insets(50));

        controlBar = loader.getController();
        controlBar.initControlBar();
        controlBar.setVisible(false);
        controlBar.setPlayButtonAction(this::playVideo);
        controlBar.setFastForwardButtonAction(this::fastForwardVideo);
        controlBar.setRewindButtonAction(this::rewindVideo);
        controlBar.setStopButtonAction(this::stopVideo);
        controlBar.setExpandButtonAction(this::expandVideo);
        controlBar.addVideoSliderListener(observable -> mediaPlayer.seek(new Duration(controlBar.getVideoSliderValue() * 1000)));
        controlBar.addVolumeSliderListener(observable -> mediaPlayer.setVolume(controlBar.getVolumeSliderValue()));
        controlBar.setControlBoxMouseEnterEvent(this::mouseEnteredControlBox);
        controlBar.setControlBoxMouseExitEvent(event -> { running = false; hideControls(event); });
    }

    /**
     * Loads the menuBar from a .fxml file. Adds the menu bar to the pane and sets all the event handlers.
     */
    private void initMenuBar() {
        Node menu = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/videoPlayer/MenuBar.fxml"));

        try {
            menu = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getChildren().add(menu);
        setAlignment(menu, Pos.TOP_CENTER);

        menuBar = loader.getController();
        menuBar.setVisible(true);
        menuBar.setCloseDisabled(true);
        menuBar.setOpenOptionAction(this::openFile);
        menuBar.setCloseOptionAction(this::closeFile);
        menuBar.setQuitOptionAction(this::quitProgram);
        menuBar.setPlayOptionAction(this::playVideo);
        menuBar.setFastForwardOptionAction(this::fastForwardVideo);
        menuBar.setRewindOptionAction(this::rewindVideo);
        menuBar.setStopOptionAction(this::stopVideo);
        menuBar.setResizeOptionAction(this::expandVideo);
        menuBar.setEditMenuDisabled(true);
        menuBar.setContainerKeyEvent(this::checkKey);
//        menuBar.setDocumentationOption();
//        menuBar.setAboutOption();
    }

    /**
     * Initializes the Media, MediaPlayer, and MediaView. Adds the mediaView to the pane. Sets the media ready event handler.
     * @param videoFile File object containing the video to be played
     */
    private void initMediaPlayer(File videoFile) {
        Media media = new Media(videoFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaView = new MediaView();
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.fitWidthProperty().bind(this.getScene().widthProperty());
        mediaView.fitHeightProperty().bind(this.getScene().heightProperty());
        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);

        this.getChildren().add(0, mediaView);

        mediaPlayer.setOnReady(() -> {
            controlBar.setVideoDuration(mediaPlayer.getMedia().getDuration());
            controlBar.setVolumeSliderValue(mediaPlayer.getVolume());
            controlBar.setVisible(true);

            menuBar.setCloseDisabled(false);
            menuBar.setOpenDisabled(true);
            menuBar.setEditMenuDisabled(false);

            if (!controlBar.isExpanded()) { expandVideo(null); }

            updateSliders();
            playVideo(null);
            hideControls(null);
        });
    }

    /**
     * Checks to see if the key that was pressed was the spaceBar.
     * @param event keyboard event
     */
    private void checkKey(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            playVideo(null);
        }
    }

    /**
     * Plays the video if the video is paused. Otherwise plays the video. Toggles the icons in controlBar.
     * @param event action event
     */
    private void playVideo(ActionEvent event) {
        mediaPlayer.setRate(1);
        if (controlBar.isPlaying()) {
            mediaPlayer.pause();
            controlBar.setPlaying(false);
        } else {
            mediaPlayer.play();
            controlBar.setPlaying(true);
        }
    }

    /**
     * Increases the rate at which the video plays. Increments from x1 to x4 to x7 then loops back to x1.
     * @param event action event
     */
    private void fastForwardVideo(ActionEvent event) {
        double rate = mediaPlayer.getRate() < 7 ? mediaPlayer.getRate() + 3 : 1;
        mediaPlayer.setRate(rate);
    }

    /**
     * Current unsupported
     * @param event action event
     */
    private void rewindVideo(ActionEvent event) {
        throw new UnsupportedOperationException();
    }

    /**
     * Pauses the video and returns the video to the beginning.
     * @param event action event
     */
    private void stopVideo(ActionEvent event) {
        mediaPlayer.pause();
        mediaPlayer.seek(new Duration(0));
        controlBar.setPlaying(false);
    }

    /**
     * Toggles the video between full screen and a smaller window.
     * @param event action event
     */
    private void expandVideo(ActionEvent event) {
        if (controlBar.isExpanded()) {
            ((Stage) this.getScene().getWindow()).setFullScreen(false);
            controlBar.setExpanded(false);
        } else {
            ((Stage) this.getScene().getWindow()).setFullScreen(true);
            controlBar.setExpanded(true);
        }
    }

    /**
     * Opens a file chooser to selected a new file to play. Supports .mp4 and .m4v video files. Calls initMedia if the File exists.
     * @param event action event.
     */
    private void openFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".mp4", "*.mp4"));
        File videoFile = fileChooser.showOpenDialog(new Stage());
        if (videoFile != null) { initMediaPlayer(videoFile); }
    }

    /**
     * Closes the current file and resets all values and visibilities to their default values.
     * @param event action event
     */
    private void closeFile(ActionEvent event) {
//        sliderTimer.cancel();
        controlTimer.cancel();
        mediaPlayer.stop();
        controlBar.setPlaying(false);
        controlBar.setVisible(false);
        controlBar.setVideoSliderValue(0);
        menuBar.setVisible(true);
        menuBar.setOpenDisabled(false);
        menuBar.setCloseDisabled(true);
        menuBar.setEditMenuDisabled(true);
        this.getChildren().remove(mediaView);
        mediaPlayer.dispose();
    }

    /**
     * Terminates the program
     * @param event action event
     */
    private void quitProgram(ActionEvent event) {
        if (this.getChildren().contains(mediaView)) { closeFile(event); }
        ((Stage) this.getScene().getWindow()).close();
    }

    /**
     * Displays the controlBar and menuBar. Then calls hideControls.
     * @param event mouse event
     */
    private void displayControls(MouseEvent event) {
        if (!this.getChildren().contains(mediaView)) { return; }
        controlBar.setVisible(true);
        menuBar.setVisible(true);
        if (!running) { hideControls(event); }
        this.requestFocus();
    }

    /**
     * Starts a timer that will hide the controlBar and menuBar after 5 seconds.
     * @param event mouse event
     */
    private synchronized void hideControls(MouseEvent event) {
        running = true;
        controlTimer = new Timer("ControlTimer", true);
        controlTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                controlBar.setVisible(false);
                menuBar.setVisible(false);
                running = false;
            }
        }, 5000);
    }

    /**
     * Starts a timer that updates the value of the video slider every second
     */
    private void updateSliders() {
//        sliderTimer = new Timer("SliderTimer", true);
//        sliderTimer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                controlBar.setVideoSliderValue(mediaPlayer.getCurrentTime().toSeconds());
//            }
//        }, 0, 1000);
    }

    /**
     * Keeps the controlBar and menuBar visible if the mouse enters the controlBar
     * @param event mouseEvent
     */
    private void mouseEnteredControlBox(MouseEvent event) {
        controlTimer.cancel();
        running = true;
        controlBar.setVisible(true);
        menuBar.setVisible(true);
    }
}
