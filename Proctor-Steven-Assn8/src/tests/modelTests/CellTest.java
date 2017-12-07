package tests.modelTests;

import model.cell.*;
import org.junit.jupiter.api.Test;

class CellTest {

    private Cell emptyCell;
    private Cell bombCell;

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

        assert emptyCell.getState() instanceof FlaggedState;
        assert bombCell.getState() instanceof FlaggedState;

        flagCells();

        assert emptyCell.getState() instanceof PossibleState;
        assert bombCell.getState() instanceof PossibleState;

        selectCells();

        assert emptyCell.getState() instanceof PossibleState;
        assert bombCell.getState() instanceof PossibleState;

        flagCells();

        assert emptyCell.getState() instanceof HiddenState;
        assert bombCell.getState() instanceof HiddenState;

        selectCells();

        assert emptyCell.getState() instanceof RevealedState;
        assert bombCell.getState() instanceof RevealedState;

        emptyCell.setBombsNearby(3);
        bombCell.setBombsNearby(3);

        assert emptyCell.getBombsNearby() == 3;
        assert bombCell.getBombsNearby() == 0;
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