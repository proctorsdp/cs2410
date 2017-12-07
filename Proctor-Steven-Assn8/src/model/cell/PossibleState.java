package model.cell;

public class PossibleState extends MarkedState {

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

    @Override
    public String toString() {
        return "P";
    }
}
