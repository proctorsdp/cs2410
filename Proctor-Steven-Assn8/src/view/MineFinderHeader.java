package view;

import controller.MineFinderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.File;

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
    void onResetButtonAction(ActionEvent event) {
        controller.reset();
    }

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

    public void setResetButtonImage(String imageURL) {
        resetButton.setText(null);
        resetButton.setGraphic(null);
        ImageView graphic = new ImageView(new File(imageURL).toURI().toString());
        graphic.setFitHeight(resetButton.getPrefHeight());
        resetButton.setGraphic(graphic);
    }
}
