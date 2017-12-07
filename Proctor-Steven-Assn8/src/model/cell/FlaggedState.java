package model.cell;

public class FlaggedState extends MarkedState {

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

    @Override
    public String toString() {
        return "F";
    }
}
