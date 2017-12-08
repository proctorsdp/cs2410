package controller.timer;


/**
 * Speed Demon Timer decrements the time each period. Initial time equals ten. Allows the controller access to resetTime
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class SpeedDemonTimer extends GameTimer {

    /**
     * Default Constructor. Sets time to 10, and timeDelay to 0
     */
    public SpeedDemonTimer() {
        this.time = 10;
        this.timeDelay = 0;
    }

    /**
     * Decrements the time by one
     */
    @Override
    void incrementTime() {
        time--;
    }

    /**
     * resets the time to 10
     */
    @Override
    void resetTime() {
        time = 10;
    }

    /**
     * Sets the need to reset to true
     */
    @Override
    public void reset() {
        reset = true;
    }

}
