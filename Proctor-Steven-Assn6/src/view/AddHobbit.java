package view;

/**
 * AddPerson GUI that contains several textFields used to obtain information about a new Hobbit from the User.
 */
class AddHobbit extends AddPerson {

    /**
     * Public constructor that creates new TextBoxes for each data member needed to create a new Hobbit
     */
    AddHobbit() {
        this.getChildren().addAll(
                new TextBox("Name:"), new TextBox("Math:"),
                new TextBox("Phrase:"), new TextBox("Carrots:"));
    }
}
