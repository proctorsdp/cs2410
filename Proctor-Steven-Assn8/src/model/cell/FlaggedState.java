package model.cell;

/**
 * A cell in a flagged state cannot be selected. Flagging a flagged state will change the cell's state to Possible
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class FlaggedState extends MarkedState {

    /**
     * Default constructor
     * @param cell the cell whose state has become flagged
     */
    FlaggedState(Cell cell) {
        super(cell);
    }

    /**
     * Changes the State of Cell from Flagged to Possible.
     * @return true, changes occurred - observers need to be notified.
     */
    @Override
    public boolean flag() {
        cell.setState(cell.getPossibleState());
        return true;
    }

    /**
     * Returns the contents of the cell in String form
     * @return "F"
     */
    @Override
    public String toString() {
        return "F";
    }
}
