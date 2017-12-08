package model.cell;

/**
 * A cell in a hidden state can be selected or flagged. Flagging a Hidden state will change the cell's state to Flagged.
 * Selected a Hidden State will set the cell's state as Revealed.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class HiddenState implements ICellState {

    /**
     * Contains the cell whose state is set to Hidden
     */
    private Cell cell;

    /**
     * Default constructor
     * @param cell the cell whose state has become hidden
     */
    HiddenState(Cell cell) {
        this.cell = cell;
    }

    /**
     * Changes the state of the cell from Hidden to Revealed.
     * @return true, changes occurred - observers need to be notified
     */
    @Override
    public boolean select() {
        cell.setState(cell.getRevealedState());
        return true;
    }

    /**
     * Changes the state of the cell from Hidden to Flagged.
     * @return true, changes occurred - observes need to be notified
     */
    @Override
    public boolean flag() {
        cell.setState(cell.getFlaggedState());
        return true;
    }

    /**
     * Returns the contents of the cell in String form
     * @return "H"
     */
    @Override
    public String toString() {
        return "H";
    }

}
