package controller.timer;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Abstract Observable timer that implements the Template Method. Tracks how long the game has been played and provides
 * a means for allowing various ways of counting time. Notifies any observers when the time has changed
 *
 * @author Steven Proctor
 * @version 1.0
 */
public abstract class GameTimer extends Observable implements IGameTimer {

    /**
     * The amount of time the game has been running. Can only count up.
     */
    private int masterTime;

    /**
     * The current time used by concrete GameTimer classes. Can increment up or down.
     */
    int time;

    /**
     * Resets time to it's original value
     */
    boolean reset;

    /**
     * The number of milliseconds to wait before starting the timerTask
     */
    int timeDelay;

    /**
     * The period of milliseconds betwwen each execution of the timerTask
     */
    private int period;

    /**
     * The Timer used to schedule the timerTask
     */
    private Timer timer;

    /**
     * The TimerTask that changes the current time
     */
    private TimerTask timerTask;

    /**
     * Stores whether the timer is running
     */
    private boolean isRunning;

    /**
     * Default constructor
     */
    GameTimer() {
        this.timer = new Timer("GameTimer", true);
        this.masterTime = 0;
        this.timeDelay = 1000;
        this.period = 1000;
        this.isRunning = false;
        this.reset = false;
    }

    /**
     * Creates the timer task and starts the timer. The incrementTime and resetTime methods are defined by the concrete
     * classes
     */
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

    /**
     * Abstract method to alter the the time after each period
     */
    abstract void incrementTime();

    /**
     * Abstract method to reset the time to its original value
     */
    abstract void resetTime();

    /**
     * Stops the timer and timerTasks, resets the time
     */
    public void stop() {
        if (isRunning) {
            timerTask.cancel();
            timer.cancel();
        }
        isRunning = false;
        resetTime();
    }

    /**
     * Gives public access to resetTime if the concrete classes override
     */
    public void reset() {
        // Do Nothing
    }

    /**
     *
     * @return the currentTime
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @return true if the timer is running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     *
     * @return the time since the game began
     */
    public int getMasterTime() { return masterTime; }
}
