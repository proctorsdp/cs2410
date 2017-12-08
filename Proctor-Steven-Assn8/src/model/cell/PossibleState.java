package model.cell;

/**
 * A cell in a possible state cannot be selected. Flagging a possible state will change the cell's state to hidden.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class PossibleState extends MarkedState {

    /**
     * Default constructor
     * @param cell the cell whose state has become possible
     */
    PossibleState(Cell cell) {
        super(cell);
    }

    /**
     * Changes the State of the cell from Possible to Hidden.
     * @return true, changes occurred - observers need to be notified
     */
    @Override
    public boolean flag() {
        cell.setState(cell.getHiddenState());
        return true;
    }

    /**
     * Returns the contents of the cell in String form
     * @return "P"
     */
    @Override
    public String toString() {
        return "P";
    }
}
