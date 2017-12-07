package view;

import controller.MineFinderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MineFinderHeader {

    @FXML
    private Text difficultyText;

    @FXML
    private Text modeText;

    @FXML
    private Text bombCountText;

    @FXML
    private Button resetButton;

    @FXML
    private Text timerText;

    @FXML
    void onResetButtonAction(ActionEvent event) { controller.reset(); }

    private MineFinderController controller;

    public void initialize(MineFinderController controller) {
        this.controller = controller;
    }

    public void setDifficultyText(String difficultyText) {
        this.difficultyText.setText(difficultyText);
    }

    public void setModeText(String modeText) {
        this.modeText.setText(modeText);
    }

    public void setBombCountText(String bombCount) {
        this.bombCountText.setText(bombCount);
    }

    public void setTimerText(String timerText) {
        this.timerText.setText(timerText);
    }

    public void setResetButtonStyle(String style) {
        resetButton.getStyleClass().clear();
        resetButton.getStyleClass().add("button");
        resetButton.getStyleClass().add(style);
    }
}
