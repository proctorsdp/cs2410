package controller;


/**
 * Size is used to store the number of rows and columns needed for a mineField. Allows the SizeFactory to function.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class Size {

    /**
     * Stores the number of rows
     */
    private int rows;

    /**
     * Stores the number of columns
     */
    private int cols;

    /**
     * Default constructor, takes in an integer value describing the number of rows and columns for the size instance.
     * @param rows number of rows
     * @param cols number of columns
     */
    public Size(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    /**
     *
     * @return the number of rows
     */
    int getRows() {
        return rows;
    }

    /**
     *
     * @return the number of columns
     */
    int getCols() {
        return cols;
    }

    /**
     * Checks if two size objects are equal by checking the values for row and col are equal
     * @param o any object
     * @return true if the object is an instance of Size and the row and col data members for each instance are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Size) {
            Size s = (Size) o;
            return s.getCols() == cols && s.getRows() == rows;
        }
        return false;
    }
}
