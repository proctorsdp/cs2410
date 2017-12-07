package view;

import controller.MineFinderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;

public class MineFinderMenu {


    @FXML
    private CheckMenuItem easyItem_DifficultyMenu;

    @FXML
    private CheckMenuItem mediumItem_DifficultyMenu;

    @FXML
    private CheckMenuItem hardItem_DifficultyMenu;

    @FXML
    void setDifficulty_OnDifficultyMenuAction(ActionEvent event) {
        if (event.getSource() instanceof CheckMenuItem) {
            CheckMenuItem difficulty = (CheckMenuItem) event.getSource();

            if (difficulty.equals(easyItem_DifficultyMenu)) {
                mediumItem_DifficultyMenu.setSelected(false);
                hardItem_DifficultyMenu.setSelected(false);
                controller.setDifficulty("easy");
            } else if (difficulty.equals(mediumItem_DifficultyMenu)) {
                easyItem_DifficultyMenu.setSelected(false);
                hardItem_DifficultyMenu.setSelected(false);
                controller.setDifficulty("medium");
            } else if (difficulty.equals(hardItem_DifficultyMenu)) {
                easyItem_DifficultyMenu.setSelected(false);
                mediumItem_DifficultyMenu.setSelected(false);
                controller.setDifficulty("hard");
            }
        }
    }

    @FXML
    private CheckMenuItem traditionalItem_ModeMenu;

    @FXML
    private CheckMenuItem speedDemonItem_ModeMenu;

    @FXML
    private CheckMenuItem countDownItem_ModeMenu;

    @FXML
    void setMode_onModeMenuAction(ActionEvent event) {
        if (event.getSource() instanceof CheckMenuItem) {
            CheckMenuItem mode = (CheckMenuItem) event.getSource();

            if (mode.equals(traditionalItem_ModeMenu)) {
                speedDemonItem_ModeMenu.setSelected(false);
                countDownItem_ModeMenu.setSelected(false);
                controller.setModeText("traditional");
            } else if (mode.equals(speedDemonItem_ModeMenu)) {
                traditionalItem_ModeMenu.setSelected(false);
                countDownItem_ModeMenu.setSelected(false);
                controller.setModeText("speed demon");
            } else if (mode.equals(countDownItem_ModeMenu)) {
                speedDemonItem_ModeMenu.setSelected(false);
                traditionalItem_ModeMenu.setSelected(false);
                controller.setModeText("count down");
            }
        }
    }

    @FXML
    private CheckMenuItem smallItem_SizeMenu;

    @FXML
    private CheckMenuItem mediumItem_SizeMenu;

    @FXML
    private CheckMenuItem largeItem_SizeMenu;

    @FXML
    void setSize_OnSizeMenuAction(ActionEvent event) {
        if (event.getSource() instanceof CheckMenuItem) {
            CheckMenuItem size = (CheckMenuItem) event.getSource();

            if (size.equals(smallItem_SizeMenu)) {
                mediumItem_SizeMenu.setSelected(false);
                largeItem_SizeMenu.setSelected(false);
                controller.setSize("small");
            } else if (size.equals(mediumItem_SizeMenu)) {
                smallItem_SizeMenu.setSelected(false);
                largeItem_SizeMenu.setSelected(false);
                controller.setSize("medium");
            } else if (size.equals(largeItem_SizeMenu)) {
                smallItem_SizeMenu.setSelected(false);
                mediumItem_SizeMenu.setSelected(false);
                controller.setSize("large");
            }
        }
    }

    @FXML
    private CheckMenuItem activeItem_SoundMenu;

    @FXML
    void setSound_OnSoundMenuAction(ActionEvent event) {
        if (event.getSource() instanceof CheckMenuItem) {
            CheckMenuItem sound = (CheckMenuItem) event.getSource();

            if (sound.equals(activeItem_SoundMenu)) {
                controller.setSound(sound.isSelected());
            }
        }
    }

    @FXML
    void onDisplayScoresAction(ActionEvent event) {
        controller.displayScores();
    }

    @FXML
    void onEraseScoresAction(ActionEvent event) {
        controller.eraseScores();
    }

    private MineFinderController controller;

    public void initialize(MineFinderController controller) {
        this.controller = controller;
    }
}
