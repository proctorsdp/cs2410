package model.cell;

import java.util.Observer;

public interface ICell {

    void select();

    void flag();

    void addObserver(Observer observer);

    void deleteObserver(Observer observer);

    void setBombsNearby(int i);

    boolean isBomb();

    Object getState();

    int getBombsNearby();
}
