package cs2410.assn1;

import cs2410.assn1.greeting.MyName;

/**
 *  This program creates a new class and calls a method that will
 *  print the author's name and a generic greeting to the command window.
 *
 *  @author Steven Proctor
 *  @version 1.0
 */
public class Main {

    /**
     * Creates a new Main class N, and calls the printName method
     *
     * @param args Command line arguments for the program
     */
    public static void main(String[] args) {
        MyName N = new MyName();
        N.printName();
    }
}
