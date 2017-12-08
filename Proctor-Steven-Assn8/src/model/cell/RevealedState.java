package model.cell;

/**
 * A cell in a Revealed state cannot be selected or flagged.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class RevealedState implements ICellState {

    /**
     * Contains the cell whose state is set to Revealed
     */
    private Cell cell;


    RevealedState(Cell cell) {
        this.cell = cell;
    }

    /**
     * Does Nothing. No Changes
     * @return false, no changes occurred.
     */
    @Override
    public boolean select() {
        // Do Nothing
        return false;
    }

    /**
     * Does Nothing. No Changes.
     * @return false, no changes occurred.
     */
    @Override
    public boolean flag() {
        // Do Nothing
        return false;
    }

    /**
     * Returns the contents of the cell in String form
     * @return the number of bombs surrounding the cell
     */
    @Override
    public String toString() {
        return cell.isBomb() ? "B" : cell.getBombsNearby() + "";
    }
}
