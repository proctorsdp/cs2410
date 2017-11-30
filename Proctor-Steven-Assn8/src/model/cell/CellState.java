package model.cell;

/**
 * Interface for a class describing the state of a cell. A valid state must be able to support select and flag methods.
 */
public interface CellState {

    /**
     * Attempts to reveal the cell. Returns true if the observers need to be notified of a change.
     * @return true if a change occurs
     */
    boolean select();

    /**
     * Toggles the flag on a cell.
     *      Hidden -> Flag
     *      Flag -> Possible
     *      Possible -> Hidden
     * @return true if a change occurs and the observers need to be notified.
     */
    boolean flag();

}
