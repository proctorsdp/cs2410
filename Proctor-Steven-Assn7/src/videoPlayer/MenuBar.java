package videoPlayer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;

/**
 * A menu bar for a video player. Has options to open and close video files. Option to quit the program. Options to play,
 * pause, stop, rewind, fast forward, and resize. Help menu contains documentation and about option. Each menu item can
 * have an event handler given to it. The edit menu can be disabled, as well as the open and close options.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MenuBar {

    /**
     * The menuBar container
     */
    @FXML
    public javafx.scene.control.MenuBar menuBarContainer;

    /**
     * MenuItem containing the fast forward option
     */
    @FXML
    public MenuItem fastForwardOption;

    /**
     * MenuItem containing the rewind option
     */
    @FXML
    public MenuItem rewindOption;

    /**
     * MenuItem containing the stop option
     */
    @FXML
    public MenuItem stopOption;

    /**
     * MenuItem containing the resize option
     */
    @FXML
    public MenuItem resizeOption;

    /**
     * MenuItem containing the quit option
     */
    @FXML
    private MenuItem quitOption;

    /**
     * MenuItem containing the open option
     */
    @FXML
    private MenuItem openOption;

    /**
     * MenuItem containing the close option
     */
    @FXML
    private MenuItem closeOption;

    /**
     * MenuItem containing the play option
     */
    @FXML
    private MenuItem playOption;

    /**
     * MenuItem containing the documentaion option
     */
    @FXML
    private MenuItem documentationOption;

    /**
     * MenuItem containing the about option
     */
    @FXML
    private MenuItem aboutOption;


    /**
     * Used to set an Action Event Handler for the open menuItem.
     * @param event Action Event Handler
     */
    void setOpenOptionAction(EventHandler<ActionEvent> event) { openOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the close menuItem.
     * @param event Action Event Handler
     */
    void setCloseOptionAction(EventHandler<ActionEvent> event) { closeOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the quit menuItem.
     * @param event Action Event Handler
     */
    void setQuitOptionAction(EventHandler<ActionEvent> event) { quitOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the play menuItem.
     * @param event Action Event Handler
     */
    void setPlayOptionAction(EventHandler<ActionEvent> event) { playOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the fast forward menuItem.
     * @param event Action Event Handler
     */
    void setFastForwardOptionAction(EventHandler<ActionEvent> event) { fastForwardOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the rewind menuItem.
     * @param event Action Event Handler
     */
    void setRewindOptionAction(EventHandler<ActionEvent> event) { rewindOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the stop menuItem.
     * @param event Action Event Handler
     */
    void setStopOptionAction(EventHandler<ActionEvent> event) { stopOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the resize menuItem.
     * @param event Action Event Handler
     */
    void setResizeOptionAction(EventHandler<ActionEvent> event) { resizeOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the documentation menuItem.
     * @param event Action Event Handler
     */
    void setDocumentationOption(EventHandler<ActionEvent> event) { documentationOption.setOnAction(event); }

    /**
     * Used to set an Action Event Handler for the about menuItem.
     * @param event Action Event Handler
     */
    void setAboutOption(EventHandler<ActionEvent> event) { aboutOption.setOnAction(event); }

    /**
     * Sets the menu to be either visible or invisible.
     * @param visible boolean value that determines whether the menu will be visible.
     */
    void setVisible(boolean visible) { menuBarContainer.setVisible(visible); }

    /**
     * Used to set an Key Event Handler for the menu.
     * @param event Key Event Handler
     */
    void setContainerKeyEvent(EventHandler<KeyEvent> event) { menuBarContainer.setOnKeyPressed(event); }

    /**
     * Sets the open menu Item as disabled
     * @param disabled boolean that disables or activates the open menu Item
     */
    void setOpenDisabled(boolean disabled) { openOption.setDisable(disabled); }

    /**
     * Sets the close menu Item as disabled
     * @param disabled boolean that disables or activates the close menu Item
     */
    void setCloseDisabled(boolean disabled) { closeOption.setDisable(disabled); }

    /**
     * Sets the edit menu as disabled
     * @param disabled boolean that disables or activates the edit menu
     */
    void setEditMenuDisabled(boolean disabled) {
        playOption.setDisable(disabled);
        fastForwardOption.setDisable(disabled);
        rewindOption.setDisable(disabled);
        stopOption.setDisable(disabled);
        resizeOption.setDisable(disabled);
    }
}
