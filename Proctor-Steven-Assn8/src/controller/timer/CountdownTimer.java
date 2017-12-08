package controller.timer;

/**
 * Count Down Timer decrements the time each period. Initial time depends on the board size and difficulty.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class CountdownTimer extends GameTimer {

    /**
     * The amount of time given to solve a board of particular size and difficulty
     */
    private int initialTime;

    /**
     * Default Constructor. Determines appropriate initialTime
     * @param difficulty String describing the difficulty of the game
     * @param size String describing the size of the board
     */
    public CountdownTimer(String difficulty, String size) {

        size = size.toUpperCase();
        switch (size) {
            case "SMALL": this.initialTime = 3;
                break;
            case "MEDIUM": this.initialTime = 8;
                break;
            case "LARGE": this.initialTime = 15;
                break;
            default: this.initialTime = 5;
                break;
        }

        this.initialTime *= 60;

        difficulty = difficulty.toUpperCase();
        switch (difficulty) {
            case "EASY": this.initialTime *= 1;
                break;
            case "MEDIUM": this.initialTime *= 2;
                break;
            case "HARD": this.initialTime *= 3;
                break;
            default: this.initialTime *= 1;
                break;
        }

        this.time = this.initialTime;
    }

    /**
     * decrements time by one
     */
    @Override
    void incrementTime() {
        time--;
    }

    /**
     * reset time to initialTime
     */
    @Override
    void resetTime() {
        time = initialTime;
    }
}
