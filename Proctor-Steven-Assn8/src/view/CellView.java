package view;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * The view for the class Cell. Tells the controller to update the model when clicked. Displays the contents of model.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class CellView extends Button implements ICellView {


    /**
     * An CellViewController which is updated when the user interacts with the cell view
     */
    private ICellViewController controller;


    /**
     * Default Constructor. Initializes the style and event handlers of the cellView.
     * @param controller an Objects which implements the ICellViewController interface
     */
    public CellView(ICellViewController controller) {
        this.controller = controller;
        this.setOnMousePressed(this::mouseClicked);
        this.setOnMouseEntered(this::highlightCell);
        this.setOnMouseExited(this::deHighlightCell);
        this.getStylesheets().add("view/styleSheets/cellStyles.css");
        this.setMaxSize(30, 30);
        this.setMinSize(30, 30);
    }

    /**
     * Removes the highlight from the button when the mouse leaves the button
     * @param event not used
     */
    private void deHighlightCell(MouseEvent event) {
        this.setStyle(null);
    }


    /**
     * Highlights the button when the mouse enters the button
     * @param event not used
     */
    private void highlightCell(MouseEvent event) {
        this.setStyle("-fx-background-color: lightskyblue");
    }


    /**
     * Tells the controller to select the cell if it has been left clicked.
     * Tells the controller to flag the cell if it has been right clicked.
     * @param mouseEvent A MouseEvent that occurs when the button is pressed
     */
    private void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            controller.flagCell(this);
        } else {
            controller.selectCell(this);
        }
    }

    /**
     * Sets the text and style of the button
     * @param text text to be displayed in the button
     */
    public void setButtonText(String text) {
        this.setGraphic(null);
        this.setText(text);
        this.getStyleClass().add("revealed-cell");
        setTextColor(Integer.parseInt(text));
    }


    /**
     * Sets the button style based on the value of the integer passed in.
     * @param num an integer that describes the number of bombs surrounding the button.
     */
    private void setTextColor(int num) {
        switch (num) {
            case 0: this.getStyleClass().add("zero-bombs");
                break;
            case 1: this.getStyleClass().add("one-bombs");
                break;
            case 2: this.getStyleClass().add("two-bombs");
                break;
            case 3: this.getStyleClass().add("three-bombs");
                break;
            case 4: this.getStyleClass().add("four-bombs");
                break;
            case 5: this.getStyleClass().add("five-bombs");
                break;
            case 6: this.getStyleClass().add("six-bombs");
                break;
            case 7: this.getStyleClass().add("seven-bombs");
                break;
            case 8: this.getStyleClass().add("eight-bombs");
                break;
            default:
                break;
        }
    }

    /**
     * Makes the button transparent to mouseEvents
     */
    public void disableCell() {
        this.setStyle(null);
        this.setMouseTransparent(true);
    }

    /**
     * Changes the style of the cell using the cellStyles.css file, and a string describing the style desired
     * @param style the name of a style in cellStyles.css
     */
    public void addStyle(String style) {
        this.getStyleClass().add(style);
    }
}
