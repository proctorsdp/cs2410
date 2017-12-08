package controller.timer;

import java.util.Observer;


/**
 * IGameTimer defines the interface required by MineFinderController for a timer
 *
 * @author Steven Proctor
 * @version 1.0
 */
public interface IGameTimer {

    /**
     * Interface that allows the addition of an observer. An IGameTimer must be Observable.
     * @param observer Any Object who extends Observer
     */
    void addObserver(Observer observer);

    /**
     *
     * @return The current time of the game timer
     */
    int getTime();

    /**
     *
     * @return True if the timer is currently running
     */
    boolean isRunning();

    /**
     * Starts the timer
     */
    void start();

    /**
     * Resets the time to it's initial value
     */
    void reset();

    /**
     * Stops the timer. Cancels all tasks.
     */
    void stop();

    /**
     *
     * @return The amount of time since the time started
     */
    int getMasterTime();
}
