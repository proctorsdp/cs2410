package view;

import controller.MineFinderController;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.File;

public class CellView extends Button {

    private MineFinderController controller;

    public CellView(MineFinderController controller) {
        this.controller = controller;
        this.setFont(new Font(15));
        this.setOnMousePressed(this::mouseClicked);
        this.setOnMouseEntered(this::highlightCell);
        this.setOnMouseExited(this::deHighlightCell);
        this.getStylesheets().add("cellStyles.css");
        this.setMaxSize(30, 30);
        this.setMinSize(30, 30);
    }

    private void deHighlightCell(MouseEvent event) {
        this.setStyle(null);
    }

    private void highlightCell(MouseEvent event) {
        this.setStyle("-fx-background-color: lightskyblue");
    }

    private void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            controller.flagCell(this);
        } else {
            controller.selectCell(this);
            disableCell();
        }
    }

    public void setButtonImage(String image) {
        this.setText(null);
        this.setGraphic(new ImageView(new File(image).toURI().toString()));
    }

    public void setButtonText(String text) {
        this.setGraphic(null);
        this.setText(text);
        this.getStyleClass().add("revealed-button");
        setTextColor(Integer.parseInt(text));
    }

    private void setTextColor(int num) {
        switch (num) {
            case 0: this.getStyleClass().add("zero-button");
                break;
            case 1: this.getStyleClass().add("one-button");
                break;
            case 2: this.getStyleClass().add("two-button");
                break;
            case 3: this.getStyleClass().add("three-button");
                break;
            case 4: this.getStyleClass().add("four-button");
                break;
            case 5: this.getStyleClass().add("five-button");
                break;
            case 6: this.getStyleClass().add("six-button");
                break;
            case 7: this.getStyleClass().add("seven-button");
                break;
            case 8: this.getStyleClass().add("eight-button");
                break;
            default:
                break;
        }
    }

    public void disableCell() {
        this.setOnMouseEntered(null);
        this.setStyle(null);
        this.setOnMouseExited(null);
        this.setMouseTransparent(true);
    }
}
