package controller.timer;

import java.util.Observer;

public interface IGameTimer {
    void addObserver(Observer observer);

    int getTime();

    boolean isRunning();

    void start();

    void reset();

    void stop();

    int getMasterTime();
}
