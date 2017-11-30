package tests;

import model.cell.*;
import org.junit.jupiter.api.Test;

class CellTest {

    private Cell emptyCell;
    private Cell bombCell;

    // TODO - add observer to ensure no state changes occurred
    @Test
    void test() {
        emptyCell = new Cell(false);
        bombCell = new Cell(true);

        assert !emptyCell.isBomb();
        assert bombCell.isBomb();

        assert emptyCell.getState() instanceof HiddenState;
        assert bombCell.getState() instanceof HiddenState;

        flagCells();

        assert emptyCell.getState() instanceof FlaggedState;
        assert bombCell.getState() instanceof FlaggedState;

        selectCells();

        // TODO - Check for Notify()

        flagCells();

        assert emptyCell.getState() instanceof PossibleState;
        assert bombCell.getState() instanceof PossibleState;

        selectCells();

        // TODO - Check for Notify()

        flagCells();

        assert emptyCell.getState() instanceof HiddenState;
        assert bombCell.getState() instanceof HiddenState;

        selectCells();

        // TODO - Check for Notify()

        assert emptyCell.getState() instanceof RevealedState;
        assert bombCell.getState() instanceof RevealedState;
    }

    private void flagCells() {
        emptyCell.flag();
        bombCell.flag();
    }

    private void selectCells() {
        emptyCell.select();
        bombCell.select();
    }

}