package view;

import javafx.scene.control.Label;

/**
 * A label that display the text passed into the constructor. Used to display the people in the directory that have the
 * attribute selected in the comboBox
 */
class DisplayList extends Label {

    /**
     * Public constructor that sets the text of the label.
     * @param list The String to be displayed
     */
    DisplayList(String list) {
        this.setText(list);
    }
}
