package model.cell;

import java.util.Observer;

/**
 * ICell defines the interface required by MineField for a Cell. Cell must be observable.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public interface ICell {

    /**
     * A method for selecting the cell and revealing its contents
     */
    void select();

    /**
     * A method for toggling through the types of flags on a cell
     */
    void flag();

    /**
     * Adds a object as an observer. A Cell must be observable
     * @param observer Object which extends Observer
     */
    void addObserver(Observer observer);

    /**
     * Removes an object as an observer.
     * @param observer Object which extends Observer
     */
    void deleteObserver(Observer observer);

    /**
     * Sets the value defining the number of bombs surrounding the cell
     * @param i the number of bombs surrounding the cell
     */
    void setBombsNearby(int i);

    /**
     *
     * @return True if the cell contains a bomb
     */
    boolean isBomb();

    /**
     * Returns the current state of the object. Must be one of the four options
     *  Flagged
     *  Hidden
     *  Possible
     *  Revealed
     *
     * @return the current state of the object
     */
    Object getState();

    /**
     *
     * @return the number of bombs surrounding the cell
     */
    int getBombsNearby();
}
