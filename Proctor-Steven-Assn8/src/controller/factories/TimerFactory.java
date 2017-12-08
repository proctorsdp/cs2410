package controller.factories;

import controller.timer.*;

/**
 * Creates a Timer used in the MineFinder Game.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class TimerFactory {

    /**
     * Returns a GameTimer used to track and control the game
     * @param timerType String describing the timer needed
     * @param difficulty String describing the game difficulty
     * @param size String describing the size of the board
     * @return GameTimer
     */
    public IGameTimer createTimer(String timerType, String difficulty, String size) {
        timerType = timerType.toUpperCase();
        IGameTimer timer;
        switch (timerType) {
            case "TRADITIONAL": timer = new TraditionalTimer();
                break;
            case "SPEED DEMON": timer = new SpeedDemonTimer();
                break;
            case "COUNT DOWN": timer = new CountdownTimer(difficulty, size);
                break;
            default:  timer = new TraditionalTimer();
                break;
        }
        return timer;
    }
}
