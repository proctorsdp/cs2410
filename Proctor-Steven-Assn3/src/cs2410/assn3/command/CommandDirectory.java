package cs2410.assn3.command;

import cs2410.assn3.directory.Directory;
import java.util.Scanner;

/**
 * A command line based interface that allows the user to select from multiple actions. Actions include, list directory,
 * add student to directory, display the average age of the students, and quit the program. Calls the appropriate methods
 * from the Directory class depending on the user's input.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class CommandDirectory {

    /**
     * Default constructor
     */
    public CommandDirectory() { }


    /**
     * Initializes the directory and scanner. Displays menu in a loop and takes input from the user. Calls method that
     * corresponds to the users input.
     */
    public void run() {
        Directory directory = new Directory();
        Scanner input = new Scanner(System.in);
        String entry = "0";

        while (!entry.equals("4")) {
            System.out.print(menu());
            System.out.print(">: ");
            entry = input.next();

            switch (entry) {
                case "1":
                    System.out.println("\n\nDirectory:\n\n" + directory.listContent());
                    break;
                case "2":
                    addStudent(input, directory);
                    break;
                case "3":
                    System.out.printf("\n\nThe average age of all students is: %4.2f years", directory.getAverageAge());
                    break;
            }
        }
    }


    /**
     * Returns the text that makes up the menu of the program
     * @return menu text
     */
    private String menu() {
        return  "\n\n\n" +
                "=========================================================================\n" +
                "Select one of the following options by entering the corresponding number:\n" +
                "-------------------------------------------------------------------------\n" +
                "1. List directory contents\n" +
                "2. Add student to directory\n" +
                "3. Display average age of students\n" +
                "4. Quit program\n" +
                "=========================================================================\n";
    }


    /**
     * Accepts a scanner and directory. Reads in the information for the new student from the command line, adds the info
     * to the directory, and prints a confirmation that the student's info was saved successfully.
     * @param input a Scanner that reads the user's input to the command line
     * @param directory the directory containing the student list
     */
    private void addStudent(Scanner input, Directory directory) {
        String firstName, lastName, majorCode, studentID;
        int age;

       System.out.println("\n\n" +
               "Enter the student's information in the following format, with a space between each item:\n" +
               "\"first name\" \"last name\" \"age\" \"major\" \"student ID\" (use university code for major)\n" +
               "----------------------------------------------------------------------------------------"
       );

       firstName = input.next();
       lastName = input.next();
       age = Integer.parseInt(input.next());
       majorCode = input.next();
       studentID = input.next();

       directory.addStudent(firstName, lastName, age, majorCode, studentID);

       System.out.println("\n\n" +
               "******************************************************\n" +
               "The following student has been added to the directory:\n" +
               firstName + " " + lastName + ", age: " + age + ", major: " + majorCode + ", ID: " + studentID + "\n" +
               "******************************************************\n"
       );
    }
}
