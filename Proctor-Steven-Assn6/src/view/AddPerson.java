package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;


/**
 * Abstract VBox for GUI's used to get information about a new person from the user. Sets alignment and spacing. Contains
 * a method for getting the text from a textBox and checking to see if all textBoxes have been filled.
 */
abstract class AddPerson extends VBox {

    /**
     * Public constructor that sets the alignment and spacing of the AddPerson GUI
     */
    AddPerson() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setSpacing(5);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Grabs the text from a text box based on the integer passed in.
     * Text boxes are numbered from top to bottom beginning with the number 0
     * @param box An int referring the the box number from which to grab the text from.
     * @return String contained within the text box corresponding to the integer passed in.
     */
    String getString(int box) {
        TextBox textBox = (TextBox) this.getChildren().get(box);
        return textBox.getInfo();
    }

    /**
     * Checks to see if all textBoxes in the GUI have been filled out.
     * @return true if all textBoxes contain text. False otherwise.
     */
    boolean areBoxesFilled() {
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (getString(i).equals("")) { return false; }
        }
        return true;
    }
}
