package videoPlayer;

import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.File;


/**
 * ControlBar is used to control that actions of a MediaPlayer. In order to use properly, initControlBar() must be called
 * after initializing the Class. Following which the Duration of the Media must be passed into setDuration(). Each button
 * may be passed an Event Handler, and each Slider may be passed a Listener.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class ControlBar {

    /**
     * Private Image object that contains the play icon
     */
    private Image playIcon = new Image(new File("src/resource/playIcon.png").toURI().toString());

    /**
     * Private Image object that contains the pause icon
     */
    private Image pauseIcon = new Image(new File("src/resource/pauseIcon.png").toURI().toString());

    /**
     * Private Image object that contains the expand icon
     */
    private Image expandIcon = new Image(new File("src/resource/expandIcon.png").toURI().toString());

    /**
     * Private Image object that contains the shrink icon
     */
    private Image shrinkIcon = new Image(new File("src/resource/shrinkIcon.png").toURI().toString());

    /**
     * Private Duration object that contains the duration of the media
     */
    private Duration videoDuration;

    /**
     * Private boolean that indicates whether the media is being played. Used to toggle the play/pause icons
     */
    private boolean playing = false;

    /**
     * Private boolean that indicates whether the window has been expanded. Used to toggle the expand/shrink icons
     */
    private boolean expanded = false;

    /**
     * Vbox which contains all components of the controlBar
     */
    @FXML
    public VBox controlBoxContainer;

    /**
     * ImageView which contains the shrink/expand icon
     */
    @FXML
    private ImageView expandImage;

    /**
     * Button which toggles between fullscreen and a window
     */
    @FXML
    private Button expandButton;

    /**
     * Slider the corresponds to the media's current volume
     */
    @FXML
    private Slider volumeSlider;

    /**
     * Button that rewinds the media
     */
    @FXML
    private Button rewindButton;

    /**
     * Button that play the video
     */
    @FXML
    private Button playButton;

    /**
     * ImageView that contains the play / pause icons
     */
    @FXML
    private ImageView playImage;

    /**
     * Button the fast forwards the video
     */
    @FXML
    private Button fastForwardButton;

    /**
     * Button that pauses the video and returns it to the beginning frame.
     */
    @FXML
    private Button stopButton;

    /**
     * Text displaying the current time of the video
     */
    @FXML
    private Text currentTime;

    /**
     * Slider corresponding to the current location in the video.
     */
    @FXML
    private Slider videoSlider;

    /**
     * Text that displays the time remaining in the video
     */
    @FXML
    private Text timeRemaining;


    /**
     * Used to set an Action Event Handler for the play button.
     * @param event Action Event Handler
     */
    void setPlayButtonAction(EventHandler<ActionEvent> event) { playButton.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the rewind button.
     * @param event Action Event Handler
     */
    void setRewindButtonAction(EventHandler<ActionEvent> event) { rewindButton.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the fast forward button.
     * @param event Action Event Handler
     */
    void setFastForwardButtonAction(EventHandler<ActionEvent> event) { fastForwardButton.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the stop button.
     * @param event Action Event Handler
     */
    void setStopButtonAction(EventHandler<ActionEvent> event) { stopButton.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the expand button.
     * @param event Action Event Handler
     */
    void setExpandButtonAction(EventHandler<ActionEvent> event) { expandButton.setOnAction(event); }

    /**
     * Used to set private boolean: playing. Toggles the play and pause icons.
     * @param playing boolean indicating whether the media is currently playing.
     */
    void setPlaying(boolean playing) { this.playing = playing; playImage.setImage(playing ? pauseIcon : playIcon); }

    /**
     * Returns true if the media is currently playing.
     * @return boolean playing
     */
    boolean isPlaying() { return playing; }

    /**
     * Used to set private boolean: expanded. Toggles the expanded and shrink icons.
     * @param expanded boolean indicating whether the scene is currently expanded.
     */
    void setExpanded(boolean expanded) { this.expanded = expanded; expandImage.setImage(expanded ? shrinkIcon : expandIcon); }

    /**
     * Returns true if the scene is currently expanded.
     * @return boolean expanded
     */
    boolean isExpanded() { return expanded; }

    /**
     * Used to add a listener to the value property of the volume slider.
     * @param listener InvalidationListener to be notified when the value property changes.
     */
    void addVolumeSliderListener(InvalidationListener listener) { volumeSlider.valueProperty().addListener(listener); }

    /**
     * Used to add a listener to the value property of the video slider.
     * @param listener InvalidationListener to be notified when the value property changes.
     */
    void addVideoSliderListener(InvalidationListener listener) { videoSlider.valueProperty().addListener(listener); }

    /**
     * Returns the current location of the video slider between 0 and the Duration of the Media in seconds.
     * @return the value of the video slider.
     */
    double getVideoSliderValue() { return videoSlider.getValue(); }

    /**
     * Set the location of the video slider between 0 and the Duration of the Media in seconds.
     * @param value double value between 0 and the Media's Duration
     */
    void setVolumeSliderValue(double value) { volumeSlider.setValue(value); }

    /**
     * Set the  location of the volume slider between 0 and 1.
     * @param value double value between 0 and 1
     */
    void setVideoSliderValue(double value) { videoSlider.setValue(value); }

    /**
     * Returns the current location of the volume slider between 0 and 1.
     * @return the value of the volume slider.
     */
    double getVolumeSliderValue() { return volumeSlider.getValue(); }

    /**
     * Sets the controlBar as visible or invisible
     * @param visible a boolean value indicating whether the controlBox should be visible or not.
     */
    void setVisible(boolean visible) { controlBoxContainer.setVisible(visible); }

    /**
     * Used to set an Action Event Handler for the play button.
     * @param event Action Event Handler
     */
    void setControlBoxMouseEnterEvent(EventHandler<MouseEvent> event) { controlBoxContainer.setOnMouseEntered(event); }

    /**
     * Used to set an Action Event Handler for the play button.
     * @param event Action Event Handler
     */
    void setControlBoxMouseExitEvent(EventHandler<MouseEvent> event) { controlBoxContainer.setOnMouseExited(event); }

    /**
     * Sets the max value of the video slider to the duration of the Media (in seconds).
     * @param videoDuration Duration of the media
     */
    void setVideoDuration(Duration videoDuration) {
        this.videoDuration = videoDuration;
        timeRemaining.setText("-" + toDurationString(videoDuration));
        videoSlider.setMax(videoDuration.toSeconds());
        volumeSlider.setMax(1.0);
    }

    /**
     * Adds a listener to the video slider to update the text describing the remaining time in the video.
     */
    void initControlBar() {
        videoSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Duration currentDuration = new Duration(newValue.doubleValue() * 1000);
            currentTime.setText("" + toDurationString(currentDuration));
            timeRemaining.setText("-" + toDurationString(videoDuration.subtract(currentDuration)));
        });
    }

    /**
     * Formats the Duration object into a standard time String "00:00:00".
     * @param duration Current duration of the media
     * @return String containing the current location of the video formatted as "00:00:00".
     */
    private String toDurationString(Duration duration) {
        return String.format("%02d:%02d:%02d", (int) duration.toHours(), (int) duration.toMinutes(), (int) duration.toSeconds() % 60);
    }
}
