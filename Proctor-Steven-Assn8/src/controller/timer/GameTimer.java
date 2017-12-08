package controller.timer;

import controller.MineFinderController;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GameTimer extends Observable {

    protected MineFinderController controller;

    int masterTime;

    int time;

    boolean reset;

    int timeDelay;

    private int period;

    private Timer timer;

    private TimerTask timerTask;

    private boolean isRunning;

    GameTimer(MineFinderController controller) {
        this.controller = controller;
        this.timer = new Timer("GameTimer", true);
        this.masterTime = 0;
        this.timeDelay = 1000;
        this.period = 1000;
        this.isRunning = false;
        this.reset = false;
    }

    public void start() {
        isRunning = true;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (reset) {
                    resetTime();
                    reset = false;
                } else {
                    incrementTime();
                }
                masterTime++;
                setChanged();
                notifyObservers();
                clearChanged();
            }
        };
        timer.scheduleAtFixedRate(timerTask, timeDelay, period);
    }


    abstract void incrementTime();


    abstract void resetTime();

    public void stop() {
        if (isRunning) {
            timerTask.cancel();
            timer.cancel();
        }
        isRunning = false;
        resetTime();
    }

    public void reset() {
        // Do Nothing
    }

    public int getTime() {
        return time;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getMasterTime() { return masterTime; }
}
