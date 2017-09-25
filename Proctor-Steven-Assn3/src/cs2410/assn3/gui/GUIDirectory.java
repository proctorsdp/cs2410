package cs2410.assn3.gui;

import cs2410.assn3.directory.Directory;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Graphical User Interface that allows the user to select multiple actions from a drop down menu. Allows the user to view
 * a list of current students in the directory, add a student to the directory, or display the average age of the students.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class GUIDirectory extends Application {

    /**
     * Private directory class that stores the current student directory
     */
    private Directory directory = new Directory();

    /**
     * Private Alert box of type Warning used to display various types of information
     */
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    /**
     * Default main method, takes command line arguments and launches GUI
     * @param args paramaters given from the command line, not used here
     */
    public static void main(String[] args) { launch(args); }


    /**
     * Creates a Dialog choice box with options to list directory content, display average student age, and add a student
     * to the directory. Calls appropriate method corresponding to the user's selection. Displays menu after every action
     * until the user clicks cancel or selects quit program.
     * @param primaryStage default Stage used to launch GUI
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        alert.getDialogPane().setPrefWidth(600);
        alert.getDialogPane().getStylesheets().add("resource/custom.css");
        alert.setGraphic(null);

        ArrayList<String> list = new ArrayList<>();
        list.add("List Directory Content");
        list.add("Add Student to Directory");
        list.add("Display Average Age");
        list.add("Quit Program");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(list.get(0), list);
        dialog.setTitle("Student Directory");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Select one: ");

        Optional<String> result = dialog.showAndWait();

        while (result.isPresent() && !result.get().equals(list.get(3))) {
            if (result.get().equals(list.get(0))) {
                displayDirectory();
            }
            else if (result.get().equals(list.get(1))) {
                addStudent();
            }
            else if (result.get().equals(list.get(2))) {
                displayAveAge();
            }
            result = dialog.showAndWait();
        }
    }


    /**
     * Displays all the students in the directory using an information alert box
     */
    private void displayDirectory() {

        alert.setTitle("Student Directory");
        alert.setHeaderText(null);
        alert.setContentText(directory.listContent());

        alert.showAndWait();
    }


    /**
     * Displays a text input dialog box that allows the user to enter a new student's information. Parses the input data
     * and saves the new student to the directory. Displays a confirmation box stating the new student has been successfully
     * added to the directory.
     */
    private void addStudent() {
        TextInputDialog input = new TextInputDialog();
        String firstName, lastName, majorCode, studentID;
        int age;

        input.setTitle("New Student");
        input.setHeaderText("Format: \"First Name\" \"Last Name\" \"Age\" \"Major\" \"Student ID\"\n");
        input.setContentText("Enter the student's information: ");
        input.setGraphic(null);

        Optional<String> result = input.showAndWait();

        if (result.isPresent()) {
            Scanner parse = new Scanner(result.get());

            firstName = parse.next();
            lastName = parse.next();
            age = Integer.parseInt(parse.next());
            majorCode = parse.next();
            studentID = parse.next();

            directory.addStudent(firstName, lastName, age, majorCode, studentID);

            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(
                    "The following student has been added to the directory:\n" +
                    firstName + " " + lastName + ", age: " + age + ", major: " + majorCode + ", ID: " + studentID
            );

            alert.showAndWait();
        }
    }


    /**
     * Displays the average age of the students in the directory to a confirmation alert box
     */
    private void displayAveAge() {

        alert.setTitle("Average Student Age");
        alert.setHeaderText(null);
        alert.setContentText(String.format("The average age of the students is: %4.2f years", directory.getAverageAge()));

        alert.showAndWait();
    }
}
