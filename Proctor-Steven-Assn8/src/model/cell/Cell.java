package model.cell;

import java.util.Observable;

/**
 *  Cell class is for a minesweeper like game. The cell can either contain a bomb or the number of bombs surrounding the
 *  cell.
 *
 *  The cell can be in four different states:
 *      Hidden
 *      Flagged
 *      Possible
 *      Revealed
 *
 *  The select and flag methods vary depending on the state of the cell.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class Cell extends Observable implements ICell {


    /**
     * Stores the revealedState ICellState
     */
    private ICellState revealedState;

    /**
     * Returns the revealedState
     * @return revealedState
     */
    ICellState getRevealedState() {
        return revealedState;
    }

    /**
     * Stores the flaggedState ICellState
     */
    private ICellState flaggedState;

    /**
     * Returns the flaggedState
     * @return flaggeState
     */
    ICellState getFlaggedState() {
        return flaggedState;
    }

    /**
     * Stores the possibleState ICellState
     */
    private ICellState possibleState;

    /**
     * Returns the possibleState
     * @return possibleState
     */
    ICellState getPossibleState() {
        return possibleState;
    }

    /**
     * Stores the hiddenState ICellState
     */
    private ICellState hiddenState;

    /**
     * Returns the revealedState
     * @return revealedState
     */
    ICellState getHiddenState() {
        return hiddenState;
    }


    /**
     * Cell Constructor. Accepts a boolean as a parameter indicating whether or not the cell contains a bomb. Sets the
     * state of the cell as Hidden. The number of bombs surrounding the cell is set to zero.
     * @param isBomb boolean indicating whether or not the cell contains a bomb
     */
    public Cell(boolean isBomb) {
        this.hiddenState = new HiddenState(this);
        this.flaggedState = new FlaggedState(this);
        this.possibleState = new PossibleState(this);
        this.revealedState = new RevealedState(this);

        state = hiddenState;

        this.isBomb = isBomb;
        this.bombsNearby = 0;
    }


    /**
     * Stores the current state of the cell
     */
    private ICellState state;

    /**
     * Sets the current state of the cell
     * @param state the new state the cell has entered
     */
    void setState(ICellState state) {
        this.state = state;
    }

    /**
     * Returns the current state of the Cell:
     *  - Hidden: Cell is hidden
     *  - Revealed: Cell is revealed
     *  - Flagged: Cannot by selected, user believes the cell contains a bomb
     *  - Possible: Cannot be selected, user believes the cell MIGHT contain a bomb
     * @return the current state of the cell
     */
    public ICellState getState() {
        return state;
    }


    /**
     * Contains the number of bombs surrounding the cell
     */
    private int bombsNearby;

    /**
     * Set the number of bombs surrounding the cell. Bombs may be directly adjacent or diagonal to the cell. Should only
     * be used once after the board is initialized.
     * @param bombsNearby the number of bombs surrounding the cell
     */
    public void setBombsNearby(int bombsNearby) {
        if (isBomb) { return; }
        this.bombsNearby = bombsNearby;
    }


    /**
     * Return the number of bombs surrounding the cell. Bombs may be directly adjacent or diagonal to the cell.
     * @return the number of bombs surrounding the cell
     */
    public int getBombsNearby() {
        return bombsNearby;
    }


    /**
     * Select the cell and attempts to reveal its contents. Flagged & Possible cells cannot be revealed. Will notify any
     * observers if a state change occurs.
     */
    public void select() {
        if (state.select()) {
            setChanged();
            notifyObservers();
            clearChanged();
        }
    }

    /**
     * Toggle the flag on a cell.
     *      An hidden cell will be marked with a flag.
     *      A flagged cell will be marked with a question mark.
     *      A possible cell will return to a hidden state.
     * Observers will be notified of any state changes.
     */
    public void flag() {
        if (state.flag()) {
            setChanged();
            notifyObservers();
            clearChanged();
        }
    }

    /**
     * Stores whether or not the cell contains a bomb
     */
    private boolean isBomb;

    /**
     * Returns whether or not the cell contains a bomb.
     * @return true if the cell contains a bomb, otherwise false.
     */
    public boolean isBomb() {
        return isBomb;
    }


    /**
     * Returns a string containing "B" if the cell contains a bomb. Otherwise returns the number of bombs surrounding
     * the cell.
     * @return "B" if cell is a bomb, otherwise the number of bombs surrounding the cell
     */
    public String toString() {
        return state.toString();
    }

}
