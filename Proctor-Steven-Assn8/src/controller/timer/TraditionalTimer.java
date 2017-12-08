package controller.timer;


/**
 * Traditional Timer increments the time each period. Initial time equals zero.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class TraditionalTimer extends GameTimer {

    /**
     * Default Constructor. Sets time to 0.
     */
    public TraditionalTimer() {

        this.time = 0;
    }

    /**
     * Increments the time by one
     */
    @Override
    void incrementTime() {
        time++;
    }

    /**
     * Resets the time to zero
     */
    @Override
    void resetTime() {
        time = 0;
    }

}
