package model.cell;

public abstract class FlaggedCell implements ICellState {

    Cell cell;

    FlaggedCell(Cell cell) {
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
