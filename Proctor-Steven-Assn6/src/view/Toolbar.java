package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * HBox containing a comboBox and ToggleButtons used to navigate the directory and add new employees to the directory.
 */
class Toolbar extends HBox {

    /**
     * ComboBox containing a list of ways the directory can be filtered
     */
    private ComboBox<String> comboBox;

    /**
     * Togglebutton used to bring up the form needed to add a new HourlyWorker
     */
    private ToggleButton hourlyBtn;

    /**
     * Togglebutton used to bring up the form needed to add a new ContractWorker
     */
    private ToggleButton contractBtn;

    /**
     * Togglebutton used to bring up the form needed to add a new Hobbit
     */
    private ToggleButton hobbitBtn;

    /**
     * Public constructor that initializes the comboBox and buttons.
     * Sets the width of the buttons and adds them to a toggleGroup.
     * Adds the Nodes to the HBox and sets the spacing and alignment of the HBox
     */
    Toolbar() {
        comboBox = new ComboBox<>(FXCollections.
                observableArrayList("Math", "Speak", "IQ", "Carrots", "Hours", "Contracts", "Income"));

        hourlyBtn = new ToggleButton("Hourly");
        contractBtn = new ToggleButton("Contract");
        hobbitBtn = new ToggleButton("Hobbit");

        hobbitBtn.setPrefWidth(75);
        hourlyBtn.setPrefWidth(75);
        contractBtn.setPrefWidth(75);

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(hobbitBtn, hourlyBtn, contractBtn);

        this.getChildren().addAll(comboBox, hobbitBtn, hourlyBtn, contractBtn);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Sets the action to be executed when the hourly button is pressed.
     * @param event the event that should execute when the hourly button is pressed.
     */
    void setHourlyBtnAction(EventHandler<ActionEvent> event) { hourlyBtn.setOnAction(event); }

    /**
     * Sets the action to be executed when the contract button is pressed.
     * @param event the event that should execute when the contract button is pressed.
     */
    void setContractBtnAction(EventHandler<ActionEvent> event) { contractBtn.setOnAction(event); }

    /**
     * Sets the action to be executed when the hobbit button is pressed.
     * @param event the event that should execute when the hobbit button is pressed.
     */
    void setHobbitBtnAction(EventHandler<ActionEvent> event) { hobbitBtn.setOnAction(event); }

    /**
     * Sets the action to be executed when an new entry in the comboBox is selected.
     * @param event the event that should execute when a new entry in the comboBox is selected
     */
    void setComboBoxAction(EventHandler<ActionEvent> event) { comboBox.setOnAction(event); }

    /**
     *
     * @return The String of the currently selected entry in the comboBox
     */
    String getComboBoxSelection() { return comboBox.getValue(); }

    /**
     * Checks to see if the hobbit button is currently selected.
     * @return true if the hobbit button is currently selected. Otherwise false.
     */
    boolean isHobbitBtnPressed() { return hobbitBtn.isSelected(); }

    /**
     * Checks to see if the hourly button is currently selected.
     * @return true if the hourly button is currently selected. Otherwise false.
     */
    boolean isHourlyBtnPressed() { return hourlyBtn.isSelected(); }

    /**
     * Checks to see if the contract button is currently selected.
     * @return true if the contract button is currently selected. Otherwise false.
     */
    boolean isContractBtnPressed() { return contractBtn.isSelected(); }
}
