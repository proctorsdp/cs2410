package controller.factories;

/**
 * Creates a double that represents a percentage used to determine the number of bombs to put in a minefield
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class DifficultyFactory {

    /**
     * Returns the percentage (as a decimal) of the minefield which should contain a bomb.
     * @param type a String describing the game difficulty
     * @return a decimal describing the percentage of the field that should be a bomb
     */
    public double createDifficulty(String type) {
        type = type.toUpperCase();
        double difficulty;
        switch (type) {
            case "EASY": difficulty = 0.1;
                break;
            case "MEDIUM": difficulty = 0.25;
                break;
            case "HARD": difficulty = 0.4;
                break;
            default: difficulty = 0.1;
                break;
        }
        return difficulty;
    }
}
