package cs2410.assn3;

import cs2410.assn3.command.CommandDirectory;
import cs2410.assn3.gui.GUIDirectory;
import javafx.application.Application;

/**
 * Begins running a student directory program that reads in data from a given file, and stores the data in an ArrayList.
 * Allows the user to list the students currently in the directory, see the average age of the students, and add a
 * student to the directory. User can interact with the program using either the command line or a gui interface.
 * Initializes the proper interface based on a command line argument given when staring the program, either "gui" or "cmd".
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class Main {

    /**
     * Checks the arguments for correct information, calls an error message for invalid entries. Initializes and calls
     * either a command line interface (if argument equals "cmd") or a gui interface (if argument equals "gui") in which
     * the user will interact with the program.
     * @param args a String that should either contain "cmd" or "gui"
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println(argumentError());
        }

        else if (args[0].equals("gui")) {
            Application.launch(GUIDirectory.class, args);
        }

        else if (args[0].equals("cmd")) {
            CommandDirectory commandDirectory = new CommandDirectory();
            commandDirectory.run();
        }

        else {
            System.err.println(argumentError());
        }
    }

    /**
     * Returns an error message describing a missing or empty argument, and instructs the user on how to run the program
     * @return a string containing an error message
     */
    private static String argumentError() {
        return "MISSING/INCORRECT ARGUMENT\n" +
                "-----------------------------------------------------------\n" +
                "RUN PROGRAM AGAIN AND ENTER ONE OF THE FOLLOWING ARGUMENTS:\n" +
                "1. \"gui\" - Runs the gui interface for the program\n" +
                "2. \"cmd\" - Runs the command line interface for the program\n";
    }
}
