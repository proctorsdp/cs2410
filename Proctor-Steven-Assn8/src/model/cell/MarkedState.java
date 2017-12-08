package model.cell;

/**
 * A cell in a Marked state cannot be selected. Flagging a Marked state will either change the cell's state from Flagged
 * to Possible, or Possible to Hidden.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public abstract class MarkedState implements ICellState {

    /**
     * Contains the cell whose state is set to Marked
     */
    Cell cell;

    /**
     * Default constructor
     * @param cell the cell whose state has become flagged
     */
    MarkedState(Cell cell) {
        this.cell = cell;
    }

    /**
     * Does nothing. No changes occur.
     * @return false, no changes occurred.
     */
    @Override
    public boolean select() {
        // Do Nothing
        return false;
    }

}
