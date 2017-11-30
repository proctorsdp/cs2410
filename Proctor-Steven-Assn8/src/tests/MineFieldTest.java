package tests;

import model.MineField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MineFieldTest {

    private MineField mineField;

    @BeforeEach
    void setUp() {
        mineField = new MineField(10, .10);
    }

    @Test
    void selectNearbyCells() {
    }

    @Test
    void testToString() {
        System.out.println(mineField);

        mineField.select(0, 0);

        System.out.println(mineField);

        mineField.select(4, 4);

        System.out.println(mineField);

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < mineField.size(); i++) {
                mineField.flag(i);
            }
            System.out.println(mineField);
        }

        for (int i = 0; i < mineField.size(); i++) {
            mineField.select(i);
        }
        System.out.println(mineField);

    }

}