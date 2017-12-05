package model.cell;

public class HiddenState implements ICellState {

    private Cell cell;

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

    @Override
    public String toString() {
        return "H";
    }

}
