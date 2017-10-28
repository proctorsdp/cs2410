package view;

import control.PersonList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import java.util.Objects;

/**
 * BorderPane that contains a toolbar. Displays a filtered list of employees based on the entry selected from the comboBox.
 * If one of the buttons are pressed an AddPerson form will display to get information about a new person from the user.
 * A controlBar is added when the AddPerson form is displayed. Pressing the save button will add the new person to the
 * directory. Pressing cancel will clear the data currently contained in the textFields.
 */
class DirectoryApp extends BorderPane {

    /**
     * Toolbar used to navigate the directory and add additional employees
     */
    private Toolbar toolbar;

    /**
     * ControlBar used to save a new employee or clear the information from the textFields
     */
    private ControlBar controlBar;

    /**
     * PersonList containing the employee directory. Initially Empty.
     */
    private PersonList personList;

    /**
     * Abstract AddPerson used to change the form currently displayed when adding a new employee
     */
    private AddPerson addPerson;

    /**
     * Public constructor that initializes the person list, toolbar, and controlBar. Sets the actionHandlers for the
     * comboBox and buttons in the toolbar. Sets the actionHandlers for the save and cancel buttons in the controlBar.
     * Adds the Toolbar to the top of the borderpane.
     */
    DirectoryApp() {
        personList = new PersonList();

        toolbar = new Toolbar();
        toolbar.setComboBoxAction(this::displayList);
        toolbar.setHobbitBtnAction(this::displayAddHobbit);
        toolbar.setHourlyBtnAction(this::displayAddHourly);
        toolbar.setContractBtnAction(this::displayAddContract);
        this.setTop(toolbar);

        controlBar = new ControlBar();
        controlBar.setSaveBtnAction(this::createPerson);
        controlBar.setCancelBtnAction(this::clearContents);
    }

    /**
     * EventHandler for when an entry in the comboBox on the Toolbar is selected. Grabs a list of employees from personList
     * based on the filter selected. Displays the filtered employee list and information. Removes the ControlBar from the
     * borderPane.
     * @param event unused
     */
    private void displayList(ActionEvent event) {
        String listType = toolbar.getComboBoxSelection();
        if (Objects.equals(listType, "Math")) {
            this.setCenter(new DisplayList(personList.getMathList()));
        } else if (Objects.equals(listType, "Income")) {
            this.setCenter(new DisplayList(personList.getIncomeList()));
        } else if (Objects.equals(listType, "Hours")) {
            this.setCenter(new DisplayList(personList.getHoursList()));
        } else if (Objects.equals(listType, "IQ")) {
            this.setCenter(new DisplayList(personList.getIQList()));
        } else if (Objects.equals(listType, "Speak")) {
            this.setCenter(new DisplayList(personList.getSayList()));
        } else if (Objects.equals(listType, "Carrots")) {
            this.setCenter(new DisplayList(personList.getCarrotsList()));
        } else if (Objects.equals(listType, "Contracts")) {
            this.setCenter(new DisplayList(personList.getContractsList()));
        }
        this.setBottom(null);
    }

    /**
     * Brings up the form to get the info needed to add a new Hobbit. Adds the AddPerson Form and ControlBar to BorderPane
     * @param event Unused
     */
    private void displayAddHobbit(ActionEvent event) {
        addPerson = new AddHobbit();
        this.setCenter(addPerson);
        this.setBottom(controlBar);
    }

    /**
     * Brings up the form to get the info needed to add a new HourlyWorker. Adds the AddPerson Form and ControlBar to BorderPane
     * @param event Unused
     */
    private void displayAddHourly(ActionEvent event) {
        addPerson = new AddHourly();
        this.setCenter(addPerson);
        this.setBottom(controlBar);
    }

    /**
     * Brings up the form to get the info needed to add a new ContractWorker. Adds the AddPerson Form and ControlBar to BorderPane
     * @param event Unused
     */
    private void displayAddContract(ActionEvent event) {
        addPerson = new AddContract();
        this.setCenter(addPerson);
        this.setBottom(controlBar);
    }

    /**
     * Grabs the information the user typed in the AddPerson form and uses it to create a new employee and add them
     * to the directory. Displays a confirmation box that the employee has been added. If not all textFields have been
     * filled, an error message displays and informs the user that all textFields must be completed.
     * @param event unused
     */
    private void createPerson(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setGraphic(null);
        alert.setHeaderText(null);

        if (addPerson.areBoxesFilled()) {
            if (toolbar.isHobbitBtnPressed()) {
                personList.createNewHobbit(
                        addPerson.getString(0), addPerson.getString(1),
                        addPerson.getString(2), addPerson.getString(3));
            } else if (toolbar.isHourlyBtnPressed()) {
                personList.createNewHourlyWorker(
                        addPerson.getString(0), addPerson.getString(1), addPerson.getString(2),
                        addPerson.getString(3), addPerson.getString(4), addPerson.getString(5));
            } else if (toolbar.isContractBtnPressed()) {
                personList.createNewContractWorker(
                        addPerson.getString(0), addPerson.getString(1), addPerson.getString(2),
                        addPerson.getString(3), addPerson.getString(4), addPerson.getString(5));
            }

            alert.setTitle("New Employee Added");
            alert.setContentText("" + addPerson.getString(0) + " has been added");
            clearContents(event);
        } else {
            alert.setTitle("Not Enough Information");
            alert.setContentText("All text fields must be filled before saving");
        }

        alert.showAndWait();
    }

    /**
     * Clears the data entered into an AddPerson form by replacing the current form with a new form.
     * @param event Unused
     */
    private void clearContents(ActionEvent event) {
        if (toolbar.isHobbitBtnPressed()) {
            displayAddHobbit(event);
        } else if (toolbar.isHourlyBtnPressed()) {
            displayAddHourly(event);
        } else if (toolbar.isContractBtnPressed()) {
            displayAddContract(event);
        }
    }
}
