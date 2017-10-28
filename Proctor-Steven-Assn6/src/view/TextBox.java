package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * HBox that contains a textField and a label
 */
class TextBox extends HBox {

    /**
     * textField used to get information from the user.
     */
    private TextField textField;

    /**
     * public constructor used to set the label of the textField.
     * Initializes the textField and sets the alignment and spacing of the Hbox
     * @param title String containing the label for the textField
     */
    TextBox(String title) {
        Label label = new Label(title);
        label.setPrefWidth(75);
        label.setAlignment(Pos.CENTER_RIGHT);

        textField = new TextField();
        textField.setPrefWidth(250);

        this.getChildren().addAll(label, textField);
        this.setPadding(new Insets(2.5, 10, 2.5, 10));
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Returns the text contained in the textField
     * @return String containined in the textField
     */
    String getInfo() { return textField.getText(); }
}
