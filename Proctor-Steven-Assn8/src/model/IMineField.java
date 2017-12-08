package model;

import model.cell.ICell;
import java.util.Iterator;
import java.util.Observer;

/**
 * IMineField defines the interface required by MineFinderController for a MineField
 *
 * @author Steven Proctor
 * @version 1.0
 */
public interface IMineField {

    /**
     *
     * @return the current size of the minefield
     */
    int size();

    /**
     *
     * @return the number of mines in the minefield
     */
    int getNumMines();

    /**
     * Adds an observer to each cell in the minefield
     * @param observer Object which extends Observer
     */
    void addObserver(Observer observer);

    /**
     * Returns the index of an ICell in the minefield
     * @param cell The ICell in question
     * @return the index of ICell in the minefield
     */
    int indexOfCell(ICell cell);

    /**
     * Calls the select method on each ICell surrounding the cell.
     * @param cell an ICell in the minefield
     */
    void selectNearbyCells(ICell cell);

    /**
     * Calls the flag method for an ICell at the given index
     * @param index the index of an ICell in the minefield
     */
    void flag(int index);

    /**
     * Calls the select method on an ICell at the given index
     * @param index the index of an ICell in the minefield
     */
    void select(int index);

    /**
     * Returns an iterator for scanning through the minefield
     * @return a minefield iterator
     */
    Iterator getIterator();
}
