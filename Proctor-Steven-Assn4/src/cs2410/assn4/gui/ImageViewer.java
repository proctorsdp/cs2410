package cs2410.assn4.gui;

import cs2410.assn4.controller.ImageController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Optional;

/**
 * ImageViewer Application that displays the contents of an ImageList and allows the user to scan through the images
 * by clicking next of previous. The user can delete the current image being viewed, or add a new image to the list.
 *
 * When the program is closed by clicking the "x" button, calls the write method of imageList.
 *
 * @author Steven Proctor
 */
public class ImageViewer extends Application {

    /**
     * Width of the Pane
     */
    private static final int PANE_WIDTH = 820;

    /**
     * Height of the Pane
     */
    private static final int PANE_HEIGHT = 700;

    /**
     * X and Y coordinate for the imageViewer
     */
    private static final int IMAGE_X_Y = 10;

    /**
     * Width of the Image Viewer
     */
    private static final int IMAGE_WIDTH = 800;

    /**
     * Height of the ImageViewer
     */
    private static final int IMAGE_HEIGHT = 600;

    /**
     * Y-Coordinate for the Title
     */
    private static final int TITLE_Y = 635;

    /**
     * Y-Coordinate for all buttons
     */
    private static final int BUTTON_Y = PANE_HEIGHT - 40;

    /**
     * Width of the buttons
     */
    private static final int BUTTON_WIDTH = 85;

    /**
     * X-Directional spacing between buttons
     */
    private static final int BUTTON_SPACE = 20;

    /**
     * X-Coordinate for the previous button
     */
    private static final int BUTTON_X = (PANE_WIDTH/2) - BUTTON_WIDTH*2 - (BUTTON_SPACE + BUTTON_SPACE/2);

    /**
     * fileName for the images to be stored in the imageList
     */
    private String fileName = "data/images.data";

    /**
     * ImageController used to control the imageList
     */
    private ImageController imageController;

    /**
     * ImageView that contains the current image in the imageList
     */
    private ImageView imageView;

    /**
     * Pane that contains the imageViewer and Buttons
     */
    private Pane pane;

    /**
     * Button used to display the previous button in the imageList
     */
    private Button previous;

    /**
     * Button used to display the next image in the imageList
     */
    private Button next;

    /**
     * Button used to add an image to the imageList
     */
    private Button add;

    /**
     * Button used to delete the current image
     */
    private Button delete;

    /**
     * Text Input Dialog used to obtain data from the user
     */
    private TextInputDialog textDialog;

    /**
     * Contains the title of the image.
     */
    private Text title;


    /**
     * Initializes the various GUI objects and displays the GUI.
     * @param primaryStage Default parameter of a GUI
     */
    @Override
    public void start(Stage primaryStage) {
        imageController = new ImageController(fileName);
        initPane();

        initImageView();
        initPrevButton();
        initAddButton();
        initDeleteButton();
        initNextButton();
        initTitle();

        initTextDialog();

        primaryStage.setTitle("Image Viewer");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> imageController.quitProgram(fileName));

        primaryStage.show();
    }

    /**
     * Initializes the previous button based on the coordinates above and adds it to the pane.
     */
    private void initPrevButton() {
        previous = new Button("Previous");
        previous.setPrefWidth(BUTTON_WIDTH);
        previous.setLayoutX(BUTTON_X);
        previous.setLayoutY(BUTTON_Y);
        previous.setOnAction(event -> updateImage(UpdateType.PREV));
        pane.getChildren().add(previous);
    }

    /**
     * Initializes the next button based on the coordinates above and adds it to the pane.
     */
    private void initNextButton() {
        next = new Button("Next");
        next.setPrefWidth(BUTTON_WIDTH);
        next.setLayoutX(BUTTON_X + BUTTON_WIDTH * 3 + BUTTON_SPACE * 3);
        next.setLayoutY(BUTTON_Y);
        next.setOnAction(event -> updateImage(UpdateType.NEXT));
        pane.getChildren().add(next);
    }

    /**
     * Initializes the add button based on the coordinates above and adds it to the pane.
     */
    private void initAddButton() {
        add = new Button("Add");
        add.setPrefWidth(BUTTON_WIDTH);
        add.setLayoutX(BUTTON_X + BUTTON_WIDTH + BUTTON_SPACE);
        add.setLayoutY(BUTTON_Y);
        add.setOnAction(event -> updateImage(UpdateType.ADD));
        pane.getChildren().add(add);
    }

    /**
     * Initializes the delete button based on the coordinates above and adds it to the pane.
     */
    private void initDeleteButton() {
        delete = new Button("Delete");
        delete.setPrefWidth(BUTTON_WIDTH);
        delete.setLayoutX(BUTTON_X + BUTTON_WIDTH * 2 + BUTTON_SPACE * 2);
        delete.setLayoutY(BUTTON_Y);
        delete.setOnAction(event -> updateImage(UpdateType.DEL));
        pane.getChildren().add(delete);
    }

    /**
     * Initializes the pane based on the coordinates above.
     */
    private void initPane() {
        pane = new Pane();
        pane.setPrefWidth(PANE_WIDTH);
        pane.setPrefHeight(PANE_HEIGHT);
    }

    /**
     * Initializes the imageView based on the coordinates above and adds it to the pane.
     */
    private void initImageView() {
        imageView = new ImageView(imageController.getCurrentImage());
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(IMAGE_HEIGHT);
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setX(IMAGE_X_Y);
        imageView.setY(IMAGE_X_Y);
        pane.getChildren().add(imageView);
    }

    /**
     * Initializes the text dialog box.
     */
    private void initTextDialog() {
        textDialog = new TextInputDialog();
        textDialog.setGraphic(null);
        textDialog.setHeaderText(null);
    }

    /**
     * Initializes the title based on the coordinates above and adds it to the pane.
     */
    private void initTitle() {
        title = new Text();
        title.setText(imageController.getCurrentTitle());
        title.setY(TITLE_Y);
        title.setX(PANE_WIDTH/2 - title.getLayoutBounds().getWidth()/2);
        pane.getChildren().add(title);
    }

    /**
     * Updates the current image based on the button that was pressed. Then updates the Title and Buttons.
     * @param type the button type that was pressed by the user.
     */
    private void updateImage(UpdateType type) {
        switch (type) {
            case NEXT: imageView.setImage(imageController.getNextImage());
                break;
            case PREV: imageView.setImage(imageController.getPrevImage());
                break;
            case ADD: imageView.setImage(getNewImage());
                break;
            case DEL: imageView.setImage(imageController.removeCurrentImage());
                break;
        }
        updateTitle();
        updateButtons();
    }

    /**
     * Updates the textDialog object to retrieve an image URL and Title from the user.
     * Creates a new image based on the user input.
     * @return the new image if added, otherwise returns the current image
     */
    private Image getNewImage() {
        textDialog.setTitle("Image URL");
        textDialog.setContentText("Enter the URL for an image:");

        Optional<String> url = textDialog.showAndWait();
        textDialog.getEditor().clear();

        textDialog.setTitle("Image Title");
        textDialog.setContentText("Enter a title for the image:");

        Optional<String> title = textDialog.showAndWait();
        textDialog.getEditor().clear();

        if (url.isPresent() && title.isPresent()) {
            return imageController.addNewImage(url.get(), title.get());
        } else {
            return imageController.getCurrentImage();
        }
    }

    /**
     * Updates the title object with the title of the current image
     */
    private void updateTitle() {
        title.setText(imageController.getCurrentTitle());
        title.setX(PANE_WIDTH/2 - title.getLayoutBounds().getWidth()/2);
    }

    /**
     * Calls the disableButton methods to update the status of next, previous, and delete buttons
     */
    private void updateButtons() {
        disableMotion(imageController.isOneImage());
        disableDelete(imageController.noImage());
    }

    /**
     * Disables the next and previous buttons if a true is passed in. Reactivates with a false.
     * @param condition true if the next and previous button are to be disabled
     */
    private void disableMotion(boolean condition) {
        next.setDisable(condition);
        previous.setDisable(condition);
    }

    /**
     * Disables the delete button when true is passed in. Reactivates with a false.
     * @param condition true if delete button is to be disabled
     */
    private void disableDelete(boolean condition) {
        delete.setDisable(condition);
    }

}
