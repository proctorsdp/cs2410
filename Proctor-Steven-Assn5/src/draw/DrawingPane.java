package draw;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * A simple drawing pane that links to a toolbar that allows for drawing and editing of simple shapes. Supports Paths,
 * Rectangles, and Ellipses as possible drawing Shapes. Each shape can be drawn in any direction, while being resized
 * in real time during drawing action. Drawing actions can only occur when one of the shape buttons on the toolbar is
 * pressed. Shapes can be erased using the erase button of the toolbar and selecting a shape to be erased. The Edit
 * button on the toolbar enables a selected shape's properties to be changed. Properties that can be modified are:
 * Fill Color (Not supported for Paths), Stroke Size, and Stroke Color. The shape being drawn will use the properties
 * currently selected in the toolbar.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class DrawingPane extends Pane {

    /**
     * Private ToolPane that controls drawing tools and actions
     */
    private ToolPane toolPane;

    /**
     * Private Shape used to draw new shapes and select current shape
     */
    private Shape shape;

    /**
     * Private double that stores the x-origin of a shape or mouse event
     */
    private double origX;

    /**
     * Private double that stores the y-origin of a shape or mouse event
     */
    private double origY;

    /**
     * Private double that stores the width of a new shape
     */
    private double width;

    /**
     * Private double that stores the height of a new shape
     */
    private double height;

    /**
     * Default Constructor for DrawingPane. Takes a ToolPane parameter that enables drawing actions. Sets drawing area
     * and clip area. Initializes mouseHandlers for drawing new shapes.
     *
     * @param toolPane A ToolPane object that will be linked to the DrawingPane and control drawing tools and actions.
     */
    public DrawingPane(ToolPane toolPane) {
        //Initialize ToolPane
        this.toolPane = toolPane;
        initToolPaneHandlers();

        //Initialize DrawingPane
        this.setPrefSize(600, 600);
        this.setClip(new Rectangle(0, 0, this.getPrefWidth(), this.getPrefHeight()));

        //Initialize Drawing Handlers
        this.setOnMousePressed(this::drawShape);
        this.setOnMouseDragged(this::extendShape);
    }

    /**
     * Initializes the Handler Methods for the Toolbar's buttons and picker objects. Selects the last drawn shape as
     * the currently selected shape when edit is pressed. Picker objects will update the selected shapes stroke and fill
     * properties if the edit button is pressed.
     */
    private void initToolPaneHandlers() {
        //Sets the cursor type for various actions
        toolPane.setEditBtnAction(newEvent -> this.setCursor(Cursor.HAND));
        toolPane.setFreeBtnAction(newEvent -> this.setCursor(Cursor.CROSSHAIR));
        toolPane.setEllBtnAction(newEvent -> this.setCursor(Cursor.DEFAULT));
        toolPane.setRectBtnAction(newEvent -> this.setCursor(Cursor.DEFAULT));
        toolPane.setEraseBtnAction(newEvent -> this.setCursor(Cursor.DEFAULT));

        //Enables the ability to change selected shape fill and stroke dynamically
        toolPane.setFillPickerAction(newEvent -> {if (toolPane.editBtnSelected()) {shape.setFill(toolPane.getFillPickerValue());}});
        toolPane.setStrokePickerAction(newEvent -> {if (toolPane.editBtnSelected()) {shape.setStroke(toolPane.getStrokePickerValue());}});
        toolPane.setStrokeSizeAction(newEvent -> {if (toolPane.editBtnSelected()) {shape.setStrokeWidth(toolPane.getStrokeSizeValue());}});
    }

    /**
     * Gets the current location of the mouse and sets shape equal the type of shape described by the button currently
     * selected on the toolbar. Calls the createsShape() method that initialize shape properties defined by the toolbar.
     * Returns without performing any action if the erase or edit buttons are pressed.
     *
     * @param event A MouseEvent that is used to set the origin for the new shape.
     */
    private void drawShape(MouseEvent event) {
        //Prevents drawing during editing and erasing
        if (toolPane.editBtnSelected() || toolPane.eraseBtnSelected()) { return; }

        //Get mouse location
        origX = event.getX();
        origY = event.getY();

        //Create shape according to pressed button
        if (toolPane.ellBtnSelected()) {
            shape = new Ellipse(origX, origY, 0, 0);
        } else if (toolPane.rectBtnSelected()) {
            shape = new Rectangle(origX, origY, 0, 0);
        } else if (toolPane.freeBtnSelected()) {
            shape = new Path();
        }

        //Initialize shape
        createShape(event);
    }

    /**
     * Sets the stroke size and color, as well as fill color for the new shape. Also initializes handlers used to edit
     * the new shape and the cursors associated with editing
     *
     * @param event MouseEvent used to get the location the new path needs to be drawn to.
     */
    private void createShape(MouseEvent event) {
        //Exits method if no shape is null
        if (shape == null) { return; }

        //Set Shape properties and add to pane
        shape.setStroke(toolPane.getStrokePickerValue());
        shape.setStrokeWidth(toolPane.getStrokeSizeValue());
        this.getChildren().add(shape);

        //Initialize Edit Handlers
        shape.setOnMousePressed(this::modifyShape);
        shape.setOnMouseDragged(this::moveShape);

        //Fills a shape, or creates a line
        if (shape.getClass() == Path.class) {
            ((Path)shape).getElements().add(new MoveTo(event.getX(), event.getY()));
        } else {
            shape.setFill(toolPane.getFillPickerValue());
        }
    }

    /**
     * Calls the appropriate extend() method that will draws the shape in different sizes and directions as the mouse
     * moves around the pane. Stores the distance the mouse has moved in the x and y direction in width and height.
     *
     * @param event MouseEvent used to determine the width and height of the object being drawn
     */
    private void extendShape(MouseEvent event) {
        //If shape is a path, keep drawing path
        if (toolPane.freeBtnSelected()) {
            extendLine(event);
            return;
        }

        //Store height and width of shape
        width = event.getX() - origX;
        height = event.getY() - origY;

        //Extends shape according to type
        if (toolPane.rectBtnSelected()) {
            extendRectangle();
        } else if (toolPane.ellBtnSelected()) {
            extendEllipse();
        }
    }

    /**
     * Draws the Ellipse in real time in any direction, as if contained in a rectangle.
     */
    private void extendEllipse() {
        //Converts width and height to radius
        width /= 2.0;
        height /= 2.0;

        //Redraws ellipse as it is resized
        ((Ellipse) shape).setRadiusX(width > 0 ? width : -width);
        ((Ellipse) shape).setRadiusY(height > 0 ? height : -height);
        ((Ellipse) shape).setCenterX(origX + width);
        ((Ellipse) shape).setCenterY(origY + height);
    }

    /**
     * Draws the Rectangle in real time in any direction.
     */
    private void extendRectangle() {
        //Redraws rectangle as it is resized
        ((Rectangle) shape).setWidth(width > 0 ? width : -width);
        ((Rectangle) shape).setHeight(height > 0 ? height : -height);
        ((Rectangle) shape).setX(width > 0 ? origX : origX + width);
        ((Rectangle) shape).setY(height > 0 ? origY : origY + height);
    }

    /**
     * Draws the Path in real time according to the movements of the mouse.
     *
     * @param event MouseEvent used to track the motion of the mouse.
     */
    private void extendLine(MouseEvent event) {
        ((Path) shape).getElements().add(new LineTo(event.getX(), event.getY()));
    }

    /**
     * Sets the selected shape as the currently selected shape and obtain the current location of the mouse. Calls
     * either the eraseShape() or refreshPickers() if either the edit or erase buttons of the toolbar are pressed.
     *
     * @param event MouseEvent used to get the selected shape and current location of the mouse.
     */
    private void modifyShape(MouseEvent event) {
        //Stores selected shape
        shape = (Shape) event.getSource();
        origX = event.getX();
        origY = event.getY();

        //Either erases shape or refreshes pickers and bring shape to front, depending on button pressed
        if (toolPane.eraseBtnSelected()) {
            eraseShape();
        } else if (toolPane.editBtnSelected()) {
            refreshPickers();
        }
    }

    /**
     * Removes the currently selected shape from the pane.
     */
    private void eraseShape() {
        this.getChildren().remove(shape);
    }

    /**
     * Refreshes the toolbar's pickers to reflect the properties of the currently selected shape. Moves the selected
     * shape to the top of the pane.
     */
    private void refreshPickers() {
        //Exits method if selected shape is null
        if (shape == null) { return; }

        //Sets the toolbar pickers to the selected shapes fill and stroke
        toolPane.setFillPickerValue((Color)shape.getFill());
        toolPane.setStrokePickerValue((Color)shape.getStroke());
        toolPane.setStrokeSizeValue((int)shape.getStrokeWidth());

        //Moves selected shape to the top of the pane
        this.getChildren().remove(shape);
        this.getChildren().add(shape);
    }

    /**
     * Moves the selected shape to the current location of the mouse.
     *
     * @param event MouseEvent used to track the current location of the mouse.
     */
    private void moveShape(MouseEvent event) {
        if (toolPane.editBtnSelected()) {
            shape.setTranslateX(shape.getTranslateX() + event.getX() - origX);
            shape.setTranslateY(shape.getTranslateY() + event.getY() - origY);
        }
    }
}
