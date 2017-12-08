package tests.modelTests;

import model.MineField;
import model.cell.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;


class MineFieldTest {

    private MineField mineField;

    @BeforeEach
    void setUp() {
        mineField = new MineField(10, 10,.10);
    }

    @Test
    void selectNearbyCells() {
        System.out.println("TESTING NEARBY CELL SELECTION");

        int numBombs = 9;
        int randNum = 0;
        Random random = new Random();

        while (numBombs != 0) {
            randNum = random.nextInt(99);
            mineField.select(randNum);
            numBombs = mineField.get(randNum).getBombsNearby();
        }

        mineField.selectNearbyCells(mineField.get(randNum));

        System.out.println(mineField);
    }

    @Test
    void testCellManipulation() {
        System.out.println("TESTING CELL MANIPULATION");
        System.out.println(mineField);

        mineField.select(4, 4);
        assert mineField.get(44).getState() instanceof RevealedState;

        System.out.println(mineField);

        mineField.select(25);
        assert mineField.get(25).getState() instanceof RevealedState;

        System.out.println(mineField);

        mineField.flag(30);
        mineField.flag(30);
        assert mineField.get(30).getState() instanceof PossibleState;

        System.out.println(mineField);

        mineField.flag(80);
        assert mineField.get(80).getState() instanceof FlaggedState;

        System.out.println(mineField);

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < mineField.size(); i++) {
                mineField.flag(i);
            }
            System.out.println(mineField);
            assert mineField.get(44).getState() instanceof RevealedState;
            assert mineField.get(25).getState() instanceof RevealedState;
            if (j == 0) {
                assert mineField.get(30).getState() instanceof HiddenState;
                assert mineField.get(80).getState() instanceof PossibleState;
            } else if (j == 1) {
                assert mineField.get(30).getState() instanceof FlaggedState;
                assert mineField.get(80).getState() instanceof HiddenState;
            } else {
                assert mineField.get(30).getState() instanceof PossibleState;
                assert mineField.get(80).getState() instanceof FlaggedState;
            }
        }

        for (int i = 0; i < mineField.size(); i++) {
            mineField.select(i);
        }

        assert mineField.get(30).getState() instanceof PossibleState;
        assert mineField.get(80).getState() instanceof FlaggedState;

        System.out.println(mineField);

        mineField.flag(30);
        mineField.flag(80);
        mineField.flag(80);

        assert mineField.get(30).getState() instanceof HiddenState;
        assert mineField.get(80).getState() instanceof HiddenState;

        System.out.println(mineField);

        mineField.select(30);
        mineField.select(80);

        assert mineField.get(30).getState() instanceof RevealedState;
        assert mineField.get(80).getState() instanceof RevealedState;

        System.out.println(mineField);
    }
}