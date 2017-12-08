package view;

/**
 * IMenuController defines the interface required for a controller, in order for the Menu to update the controller of user input
 *
 * @author Steven Proctor
 * @version 1.0
 */
public interface IMenuController {

    /**
     * Erases the scoreboard for the current game type
     */
    void eraseScores();

    /**
     * Displays the scoreboard for the current game type
     */
    void displayScores();

    /**
     * Sets the sound active or inactive
     * @param active True if sound should be active
     */
    void setSound(boolean active);

    /**
     * Sets the size of the minefield
     * @param size string describing the size of the minefield
     */
    void setSize(String size);

    /**
     * Sets the mode of the game
     * @param mode String describing the desired game mode
     */
    void setModeText(String mode);

    /**
     * Sets the game difficulty
     * @param difficulty String describing the desired difficulty
     */
    void setDifficulty(String difficulty);
}
