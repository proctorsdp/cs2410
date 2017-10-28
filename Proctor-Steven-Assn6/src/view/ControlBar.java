package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * HBox that contains a save and cancel button used to add a new person to the directory
 */
class ControlBar extends HBox {
    /**
     * Button used to save a new person from an AddPerson to the directory
     */
    private Button saveBtn;

    /**
     * Button used to clear information entered in an AddPerson
     */
    private Button cancelBtn;

    /**
     * Public constructor that initializes the save and cancel buttons and sets the spacing and alignment of the Hbox.
     */
    ControlBar() {
        saveBtn = new Button("Save");
        saveBtn.setDefaultButton(true);
        cancelBtn = new Button("Cancel");

        this.getChildren().addAll(cancelBtn, saveBtn);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Sets an Action event for the Save button
     * @param event an event that will execute when the save button is pressed
     */
    void setSaveBtnAction(EventHandler<ActionEvent> event) { saveBtn.setOnAction(event); }

    /**
     * Sets an Action event for the cancel button
     * @param event an event that will execute when the cancel button is pressed
     */
    void setCancelBtnAction(EventHandler<ActionEvent> event) { cancelBtn.setOnAction(event); }
}
