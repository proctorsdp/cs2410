package controller.timer;

import controller.MineFinderController;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GameTimer extends Observable {

    protected MineFinderController controller;

    int time;

    private Timer timer;

    private TimerTask timerTask;

    private boolean isRunning;

    GameTimer(MineFinderController controller) {
        this.controller = controller;
        this.timer = new Timer("GameTimer", true);
        this.isRunning = false;
    }

    public void start() {
        isRunning = true;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                incrementTime();
                setChanged();
                notifyObservers();
                clearChanged();
            }
        };
        timer.scheduleAtFixedRate(timerTask,1000, 1000);
    }

    abstract void incrementTime();

    public void reset() {
        if (isRunning) {
            stop();
            resetTime();
        }
        isRunning = false;
    }

    abstract void resetTime();

    public void stop() {
        isRunning = false;
        timerTask.cancel();
        timer.cancel();
    }

    public void update() {
        // Do Nothing
    }

    public int getTime() {
        return time;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
