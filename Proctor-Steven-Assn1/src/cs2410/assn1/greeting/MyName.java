package cs2410.assn1.greeting;

/**
 *  This class stores the name of the author as well as a method to
 *  print the author's name and a generic greeting to the command window.
 *
 *  @author Steven Proctor
 *  @version 1.0
 */
public class MyName {

    /**
     * Private string used to store the author's first name
     */
    private String fName = "Steven";

    /**
     * Private string used to store the author's last name
     */
    private String lName = "Proctor";

    /**
     * Default constructor
     */
    public MyName() {}

    /**
     * prints the author's first and last name as part of a generic greeting to the command window
     */
    public void printName() {
        String greeting = "Hello, my name is " + fName + " " + lName + ".\n"
                + "It's nice to meet you!\n";
        System.out.println(greeting);
    }
}