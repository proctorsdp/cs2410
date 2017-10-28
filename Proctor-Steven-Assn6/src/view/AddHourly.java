package view;

/**
 * AddPerson GUI that contains several textFields used to obtain information about a new HourlyWorker from the User.
 */
class AddHourly extends AddPerson {

    /**
     * Public constructor that creates new TextBoxes for each data member needed to create a new HourlyWorker
     */
    AddHourly() {
        this.getChildren().addAll(
                new TextBox("Name:"), new TextBox("Math:"), new TextBox("Phrase:"),
                new TextBox("IQ:"), new TextBox("Hours:"), new TextBox("Wage:"));
    }
}