package model;

import model.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observer;

public class MineField extends ArrayList<Cell> {

    private int size;
    private int numRows;
    private int numCols;
    private int numMines;

    private int indexAt(int row, int col) {
        return (row * numCols) + col;
    }

    private void updateBombCount() {
        for (Cell c : this) {
            c.setBombsNearby(countNearbyBombs(indexOf(c)));
        }
    }

    private int countNearbyBombs(int index) {
        int row = index / numCols;
        int col = index % numCols;
        int bombs = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            if (i < 0 || i > numRows - 1) { continue; }

            for (int j = col - 1; j <= col + 1; j++) {
                if (j < 0 || j > numCols - 1) { continue; }

                if (this.get(indexAt(i, j)).isBomb()) {
                    bombs++;
                }
            }
        }

        return bombs;
    }

    public MineField(int numRows, int numCols, double percentMines) {
        if (percentMines > 0.4 || percentMines < 0.1) {
            throw new IllegalArgumentException(
                    "percentMines must be between 0.1 and 0.4; percentMines = " + percentMines
            );
        } else if (numRows < 10 || numRows > 25) {
            throw new IllegalArgumentException(
                    "numRows must be between 10 and 25; numRows = " + numRows
            );
        } else if (numCols < 10 || numCols > 50) {
            throw new IllegalArgumentException(
                    "numCols must be between 10 and 50; numCols = " + numCols
            );
        }

        this.numRows = numRows;
        this.numCols = numCols;
        this.size = numCols * numRows;
        this.numMines = (int) (size * percentMines);

        for (int i = 0; i < size; i++) {
            boolean isBomb = false;
            if (i < numMines) {
                isBomb = true;
            }
            this.add(new Cell(isBomb));
        }

        Collections.shuffle(this);

        updateBombCount();
    }

    public void selectNearbyCells(Cell c) {
        int index = this.indexOf(c);
        int row = index / numCols;
        int col = index % numCols;

        for (int i = row - 1; i <= row + 1; i++) {
            if (i < 0 || i > numRows - 1) { continue; }

            for (int j = col - 1; j <= col + 1; j++) {
                if (j < 0 || j > numCols - 1) { continue; }

                // TODO
                this.get(indexAt(i, j)).select();
            }
        }
    }

    public void select(int row, int col) {
        int index = indexAt(row, col);
        if (index >= 0 && index < size) {
            this.get(index).select();
        }
    }

    public void select(int index) {
        if (index >= 0 && index < size) {
            this.get(index).select();
        }
    }

    public void flag(int row, int col) {
        int index = indexAt(row, col);
        if (index >= 0 && index < size) {
            this.get(index).flag();
        }
    }

    public void flag(int index) {
        if (index >= 0 && index < size) {
            this.get(index).flag();
        }
    }

    public int getNumMines() {
        return numMines;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Cell c : this) {
            stringBuilder.append(c);

            if ((this.indexOf(c) + 1) % numCols == 0) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }

    public void addObserver(Observer observer) {
        for (Cell c : this) {
            c.addObserver(observer);
        }
    }

    public void removeObserver(Observer observer) {
        for (Cell c : this) {
            c.deleteObserver(observer);
        }
    }
}
