package model.cell;

public class RevealedState implements CellState {

    private Cell cell;

    public RevealedState(Cell cell) {
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

    @Override
    public String toString() {
        return cell.isBomb() ? "B" : cell.getBombsNearby() + "";
    }
}
