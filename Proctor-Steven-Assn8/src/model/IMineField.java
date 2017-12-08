package model;

import model.cell.ICell;
import java.util.Iterator;
import java.util.Observer;

public interface IMineField {
    int size();

    int getNumMines();

    void addObserver(Observer observer);

    int indexOfCell(ICell cell);

    void selectNearbyCells(ICell cell);

    void flag(int index);

    void select(int index);

    Iterator getIterator();
}
