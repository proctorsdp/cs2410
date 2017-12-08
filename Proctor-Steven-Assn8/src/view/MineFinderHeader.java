package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Header View that displays the current state of the game. Displays the number of bombs remaining, the current time,
 * the game mode, and the current difficulty. Includes a reset button to reset the game. Reset button can be styled to
 * match the state of the game.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MineFinderHeader {


    /**
     * Text object that displays the current difficulty
     */
    @FXML
    private Text difficultyText;


    /**
     * Text object that displays the current game mode
     */
    @FXML
    private Text modeText;


    /**
     * Text object that displays the current bomb count
     */
    @FXML
    private Text bombCountText;

    /**
     * The Button object that calls controller's reset method
     */
    @FXML
    private Button resetButton;

    /**
     * The Text object that displays the current time
     */
    @FXML
    private Text timerText;


    /**
     * Calls the reset method of the controller when the button is pressed
     * @param event not used
     */
    @FXML
    void onResetButtonAction(ActionEvent event) { controller.reset(); }


    private IHeaderController controller;


    public void initialize(IHeaderController controller) {
        this.controller = controller;
    }

    /**
     * Set the String to be displayed in the difficultyText
     * @param difficultyText String containing the current difficulty
     */
    public void setDifficultyText(String difficultyText) {
        this.difficultyText.setText(difficultyText);
    }

    /**
     * Set the String to be displayed in the ModeText
     * @param modeText String containing the mode being played
     */
    public void setModeText(String modeText) {
        this.modeText.setText(modeText);
    }

    /**
     * Set the String to be displayed in the BombCountText
     * @param bombCount String containing the current bomb count
     */
    public void setBombCountText(String bombCount) {
        this.bombCountText.setText(bombCount);
    }

    /**
     * Set the String to be displayed in the TimerText
     * @param timerText String containing the current time
     */
    public void setTimerText(String timerText) {
        this.timerText.setText(timerText);
    }

    /**
     * Sets the style of reset button given a css formatted string
     * @param style css formatted string
     */
    public void setResetButtonStyle(String style) {
        resetButton.getStyleClass().clear();
        resetButton.getStyleClass().add("button");
        resetButton.getStyleClass().add(style);
    }
}
