package view;

/**
 * AddPerson GUI that contains several textFields used to obtain information about a new ContractWorker from the User.
 */
class AddContract extends AddPerson {

    /**
     * Public constructor that creates new TextBoxes for each data member needed to create a new ContractWorker
     */
    AddContract() {
        this.getChildren().addAll(
                new TextBox("Name:"), new TextBox("Math:"), new TextBox("Phrase:"),
                new TextBox("IQ:"), new TextBox("Contracts:"), new TextBox("Pay:"));
    }
}