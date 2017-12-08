package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;


/**
 * Menu view used to change the state of the game. Size, difficulty, and mode can all be changed.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MineFinderMenu {


    /**
     * MenuItem that sets difficulty as easy
     */
    @FXML
    private CheckMenuItem easyItem_DifficultyMenu;

    /**
     * MenuItem that sets difficulty as medium
     */
    @FXML
    private CheckMenuItem mediumItem_DifficultyMenu;

    /**
     * MenuItem that sets difficulty as hard
     */
    @FXML
    private CheckMenuItem hardItem_DifficultyMenu;

    /**
     * Checks the difficulty that has been most recently selected, uncheck the other difficulties.
     * Updates the difficulty in the controller.
     * @param event ActionEvent when an item in the difficulty menu is selected
     */
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

    /**
     * MenuItem that sets game mode as traditional
     */
    @FXML
    private CheckMenuItem traditionalItem_ModeMenu;

    /**
     * MenuItem that sets game mode as speed demon
     */
    @FXML
    private CheckMenuItem speedDemonItem_ModeMenu;

    /**
     * MenuItem that sets game mode as count down
     */
    @FXML
    private CheckMenuItem countDownItem_ModeMenu;

    /**
     * Checks the mode that has been most recently selected, uncheck the other mode.
     * Updates the mode in the controller.
     * @param event ActionEvent when an item in the mode menu is selected
     */
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

    /**
     * MenuItem that sets size as small
     */
    @FXML
    private CheckMenuItem smallItem_SizeMenu;

    /**
     * MenuItem that sets size as medium
     */
    @FXML
    private CheckMenuItem mediumItem_SizeMenu;

    /**
     * MenuItem that sets size as large
     */
    @FXML
    private CheckMenuItem largeItem_SizeMenu;


    /**
     * Checks the size that has been most recently selected, uncheck the other size.
     * Updates the size in the controller.
     * @param event ActionEvent when an item in the size menu is selected
     */
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

    /**
     * MenuItem used to toggle the game sound on and off
     */
    @FXML
    private CheckMenuItem activeItem_SoundMenu;

    /**
     * Toggles the sound active item on and off. Updates the controller with its status
     * @param event ActionEvent when the active sound menu item is selected
     */
    @FXML
    void setSound_OnSoundMenuAction(ActionEvent event) {
        if (event.getSource() instanceof CheckMenuItem) {
            CheckMenuItem sound = (CheckMenuItem) event.getSource();

            if (sound.equals(activeItem_SoundMenu)) {
                controller.setSound(sound.isSelected());
            }
        }
    }

    /**
     * Displays the scoreboard for the current mode and difficulty.
     * CURRENTLY UNSUPPORTED
     * @param event not used
     */
    @FXML
    void onDisplayScoresAction(ActionEvent event) {
        controller.displayScores();
    }

    /**
     * Erases the scoreboard for the current mode and difficulty.
     * CURRENTLY UNSUPPORTED
     * @param event not used
     */
    @FXML
    void onEraseScoresAction(ActionEvent event) {
        controller.eraseScores();
    }

    /**
     * MineFinderController used to update information when the menu changes
     */
    private IMenuController controller;

    /**
     * Sets the MineFinderController used by the class
     * @param controller MineFinderController
     */
    public void initialize(IMenuController controller) {
        this.controller = controller;
    }
}
