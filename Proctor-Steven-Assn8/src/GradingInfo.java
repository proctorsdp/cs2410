import javafx.scene.control.Alert;

/**
 * Alert Window describing the completion of various homework requirements
 *
 * @author Steven Proctor
 * @version 1.0
 */
class GradingInfo extends Alert {

    GradingInfo() {
        super(AlertType.INFORMATION);
        this.setGraphic(null);
        this.setTitle("Grading Information");
        this.setHeaderText("" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "This project is being used as an assignment for both CS 2410 and CS 5700\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        );
        this.setContentText("" +
                "===================================\n" +
                "The following features were added for CS 2410:\n" +
                "===================================\n" +
                "   Size Feature (10pts)\n" +
                "       - Allows the user to change board size:\n" +
                "       - Fully functional\n" +
                "\n" +
                "   Difficulty Feature (10pts)\n" +
                "       - Allows the user to change game difficulty\n" +
                "       - Fully functional\n" +
                "\n" +
                "   Game Modes (30pts)\n" +
                "       - Allows the user to change the game mode\n" +
                "       - Fully functional\n" +
                "\n" +
                "   * Only 30 \"Your Choice Points\" are expected.\n" +
                "   * The size and difficulty features were added for fun.\n" +
                "\n" +
                "\n" +
                "\n" +
                "===================================\n" +
                "The following features were added for CS 5700:\n" +
                "===================================\n" +
                "   UML & Sequence Diagrams:\n" +
                "       - Located under \"readme/\" in the project directory\n" +
                "\n" +
                "   Test Cases:\n" +
                "       - Test cases were created for all applicable model and controller elements\n" +
                "       - Are located under \"src/tests/\" in the project directory\n" +
                "\n" +
                "   Inclusion of Various Object Oriented Patterns:\n" +
                "       - State Pattern: Handles the state of a cell\n" +
                "       - Observer Pattern: Notifies the Controller of a changes in the timers and model\n" +
                "       - Simple Factory: Handles the creation of the timer, size, and difficulty\n" +
                "       - Template Pattern: Handles the functionality of the three timers\n" +
                "       - Iterator Pattern: Allows the controller to iterate through the minefield\n" +
                "       - MVC Pattern: Handles the structure of the model, view, and controller\n"
        );
    }


}
