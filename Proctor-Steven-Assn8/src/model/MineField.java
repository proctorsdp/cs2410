package model;

import model.cell.Cell;
import model.cell.ICell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observer;

/**
 * Minefield contains a list of cells which makes up the field. Minefield accepts size and difficulty parameters, creates
 * the list, and shuffles the contents. Updates the bombCount for each cell in the field allows access to a cells select
 * and flag methods. Allows includes an option to select all the cells surrounding a given cell.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MineField extends ArrayList<ICell> implements IMineField {

    /**
     * Contains the number of cells in the mineField
     */
    private int size;

    /**
     * Stores the number of rows the minefield is made up of
     */
    private int numRows;

    /**
     * Stores the number of columns the minefield in made up of.
     */
    private int numCols;

    /**
     * Constructor accepts the number of rows, columns, and the percent of the cells which are to contain bombs. Creates
     * the minefield, shuffles the cells, and counts the number of of cells surrounding each cell.
     * @param numRows number of rows that makes up the minefield (10 <= rows <= 25)
     * @param numCols number of columns that makes up the minefield (10 <= columns <= 25)
     * @param percentMines percentage of cell that contain a bomb in decimal form (0.1 <= percent <= 0.4)
     */
    public MineField(int numRows, int numCols, double percentMines) {
        if (percentMines > 0.4 || percentMines < 0.1) {
            throw new IllegalArgumentException(
                    "percentMines must be between 0.1 and 0.4; percentMines = " + percentMines
            );
        } else if (numRows < 10 || numRows > 25) {
            throw new IllegalArgumentException(
                    "numRows must be between 10 and 25; numRows = " + numRows
            );
        } else if (numCols < 10 || numCols > 50) {
            throw new IllegalArgumentException(
                    "numCols must be between 10 and 50; numCols = " + numCols
            );
        }

        this.numRows = numRows;
        this.numCols = numCols;
        this.size = numCols * numRows;
        this.numMines = (int) (size * percentMines);

        for (int i = 0; i < size; i++) {
            boolean isBomb = false;
            if (i < numMines) {
                isBomb = true;
            }
            this.add(new Cell(isBomb));
        }

        Collections.shuffle(this);

        updateBombCount();
    }

    /**
     * Calls the select function for all eight cells surrounding the given cell.
     * @param c a cell whose neighbors need to be selected
     */
    public void selectNearbyCells(ICell c) {
        int index = this.indexOfCell(c);
        int row = index / numCols;
        int col = index % numCols;

        for (int i = row - 1; i <= row + 1; i++) {
            if (i < 0 || i > numRows - 1) { continue; }

            for (int j = col - 1; j <= col + 1; j++) {
                if (j < 0 || j > numCols - 1) { continue; }

                this.get(indexAt(i, j)).select();
            }
        }
    }

    /**
     * Calls the select method of a cell, given its location in the minefield.
     * @param row the row in the minefield where the cell is located
     * @param col the column in the minefield where the cell is located
     */
    public void select(int row, int col) {
        int index = indexAt(row, col);
        if (index >= 0 && index < size) {
            this.get(index).select();
        }
    }

    /**
     * Calls the select method of a cell, given its location in the minefield.
     * @param index the location of the cell in the arraylist
     */
    public void select(int index) {
        if (index >= 0 && index < size) {
            this.get(index).select();
        }
    }

    @Override
    public Iterator getIterator() {
        return this.iterator();
    }

    /**
     * Calls teh flag method of a cell, given its location in the minefield.
     * @param row the row in which the cell is located in the minefield
     * @param col the column in which the cell is located in the minefield
     */
    public void flag(int row, int col) {
        int index = indexAt(row, col);
        if (index >= 0 && index < size) {
            this.get(index).flag();
        }
    }

    /**
     * Calls teh flag method of a cell, given its location in the minefield.
     * @param index the location of the cell in the arraylist
     */
    public void flag(int index) {
        if (index >= 0 && index < size) {
            this.get(index).flag();
        }
    }

    /**
     * Contains the number of mines in the minefield
     */
    private int numMines;

    /**
     * Return the number of mines in the minefield.
     * @return the number of mines in the minefield
     */
    public int getNumMines() {
        return numMines;
    }


    public int indexOfCell(ICell cell) {
        return this.indexOf(cell);
    }


    /**
     * Returns a string of the minefield, formatted to its appropriate dimensions.
     * @return a string containing the minefield
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (ICell c : this) {
            stringBuilder.append(c);

            if ((this.indexOfCell(c) + 1) % numCols == 0) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }


    /**
     * Add an minefield observer
     * @param observer an object which extends Observer
     */
    public void addObserver(Observer observer) {
        for (ICell c : this) {
            c.addObserver(observer);
        }
    }


    /**
     * Remove a minefield observer
     * @param observer and object which extends Observer
     */
    public void removeObserver(Observer observer) {
        for (ICell c : this) {
            c.deleteObserver(observer);
        }
    }


    /**
     * Returns the index of a cell in the arraylist, given its row and column in the grid
     * @param row the row which the cell is located in the minefield
     * @param col the column which the cell is located in the minefield
     * @return the index of the cell in the arraylist
     */
    private int indexAt(int row, int col) {
        return (row * numCols) + col;
    }


    /**
     * Calls countNearbyBombs for each cell in the minefield and sets that value
     */
    private void updateBombCount() {
        for (ICell c : this) {
            c.setBombsNearby(countNearbyBombs(indexOfCell(c)));
        }
    }


    /**
     * Counts the number of bombs surrounding a cell, given its index
     * @param index the index of the cell in the arraylist
     * @return the number of bombs surrounding the cell
     */
    private int countNearbyBombs(int index) {
        int row = index / numCols;
        int col = index % numCols;
        int bombs = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            if (i < 0 || i > numRows - 1) { continue; }

            for (int j = col - 1; j <= col + 1; j++) {
                if (j < 0 || j > numCols - 1) { continue; }

                if (this.get(indexAt(i, j)).isBomb()) {
                    bombs++;
                }
            }
        }

        return bombs;
    }

}
