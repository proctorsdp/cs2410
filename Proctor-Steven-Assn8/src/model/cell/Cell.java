package model.cell;

import java.util.Observable;

public class Cell extends Observable {

    private ICellState revealedState;
    private ICellState flaggedState;
    private ICellState possibleState;
    private ICellState hiddenState;

    private ICellState state;

    private boolean isBomb;

    private int bombsNearby;


    ICellState getRevealedState() {
        return revealedState;
    }

    ICellState getFlaggedState() {
        return flaggedState;
    }

    ICellState getPossibleState() {
        return possibleState;
    }

    ICellState getHiddenState() {
        return hiddenState;
    }

    void setState(ICellState state) {
        this.state = state;
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
