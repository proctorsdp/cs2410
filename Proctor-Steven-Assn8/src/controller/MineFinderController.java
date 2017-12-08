package controller;

import controller.factories.DifficultyFactory;
import controller.factories.SizeFactory;
import controller.factories.TimerFactory;
import controller.timer.IGameTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.IMineField;
import model.MineField;
import model.cell.*;
import view.*;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * MineFinderController receives updates from a HeaderView, MenuView, Multiple CellViews, and Multiple Cell classes.
 * Manages the flow of the MineFinderGame. Updates the view based on model changes. Updates the model based on user input
 * to the view. The controller of the MVC pattern.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MineFinderController implements Observer, ICellViewController, IMenuController, IHeaderController {

    /**
     * TilePane containing the CellViews
     */
    @FXML
    public TilePane mineField_TilePane;

    /**
     * The MineFinderWindow
     */
    @FXML
    public VBox mineFinder;

    /**
     * The MenuBar - initialized by FXML
     */
    @FXML
    private Parent menu;

    /**
     * The HeaderBar - initialized by FXML
     */
    @FXML
    private Parent header;

    /**
     * The MenuBar's Controller - initialized by FXML
     */
    @FXML
    private MineFinderMenu menuController;

    /**
     * The HeaderBar's Controller - initialized by FXML
     */
    @FXML
    private MineFinderHeader headerController;

    /**
     * The IMineField model
     */
    private IMineField mineField;

    /**
     * The number of mines in the field, minus the number of marked cells
     */
    private int mineCount;

    /**
     * The number of cells whose content has not been revealed
     */
    private int unselectedCells;

    /**
     * Contains the name of the current game mode
     */
    private String modeText;

    /**
     * Contains the decimal percentage describing the number of cells which currently contain a bomb
     */
    private double difficulty;

    /**
     * Contains true if the game sounds are active
     */
    private boolean sound;

    /**
     * Contains true if the game is over
     */
    private boolean gameOver;

    /**
     * Contains the name of the current difficulty
     */
    private String difficultyText;

    /**
     * Contains the name of the current size
     */
    private String sizeText;

    /**
     * Contains the current size of the minefield
     */
    private Size size;

    /**
     * Contains the current game timer
     */
    private IGameTimer timer;

    /**
     * Sets the game controller for the Header and Menu. Creates the factories. Sets the initial game mode, size, and
     * difficulty. Actives the sound, and formats the tilePane. Calls initializeView.
     */
    public void initialize() {
        headerController.setGameController(this);
        menuController.setGameController(this);

        difficultyFactory = new DifficultyFactory();
        sizeFactory = new SizeFactory();
        timerFactory = new TimerFactory();

        modeText = "TRADITIONAL";
        difficultyText = "EASY";
        difficulty = 0.1;
        sizeText = "SMALL";
        size = new Size(10, 10);
        sound = true;

        mineField_TilePane.setHgap(2);
        mineField_TilePane.setVgap(2);
        mineField_TilePane.setAlignment(Pos.CENTER);

        initializeView();
    }

    /**
     * Creates the minefield based on the size and difficulty. Initializes mineCount, unselectedCells, and gameOver. Adds
     * this as an observer of each cell in the model. Adds CellViews to the tilePane. Sets the header text and initializes
     * the game timer.
     */
    private void initializeView() {
        mineField = new MineField(size.getRows(), size.getCols(), difficulty);
        unselectedCells = mineField.size();
        mineCount = mineField.getNumMines();
        mineField.addObserver(this);

        mineField_TilePane.getChildren().clear();

        for (int i = 0; i < mineField.size(); i++) {
            mineField_TilePane.getChildren().add(new CellView(this));
        }

        mineField_TilePane.setPrefRows(size.getRows());
        mineField_TilePane.setPrefColumns(size.getCols());

        headerController.setDifficultyText(difficultyText);
        headerController.setModeText(modeText);
        headerController.setBombCountText(mineCount + "");
        headerController.setResetButtonStyle("normal-face");

        setTimer();
        timer.addObserver(this);
        headerController.setTimerText(timer.getTime() + "");

        gameOver = false;
    }

    /**
     * Called whenever an object this is Observing changes. Used to Update CellViews and HeaderViews.
     * @param o Object whose state has changed
     * @param arg not used
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Cell) {
            updateCell((Cell) o);
        } else if (o instanceof IGameTimer) {
            updateTimer((IGameTimer) o);
        }
        if (!timer.isRunning() && !gameOver) {
            timer.start();
        }
    }

    /**
     * Checks the current state of the cell model. Updates the view and game accordingly
     * @param cell Cell whose state has changed
     */
    private void updateCell(Cell cell) {
        int index = mineField.indexOfCell(cell);
        CellView cellView = (CellView) mineField_TilePane.getChildren().get(index);

        if (cell.getState() instanceof RevealedState) {
            cellView.disableCell();
            timer.reset();

            if (cell.isBomb()) {
                gameOver();
            } else {
                setNumber(cell, cellView);
            }

        } else if (cell.getState() instanceof MarkedState) {
            setFlag(cell, cellView);
        } else if (cell.getState() instanceof HiddenState) {
            setHidden(cellView);
        }
    }

    /**
     * Updates the timer display in the HeaderView. Calls game over if the timer runs out.
     * @param gameTimer the game timer whose state has changed
     */
    private void updateTimer(IGameTimer gameTimer) {
        int time = gameTimer.getTime();
        headerController.setTimerText(time + "");

        if (time <= 0) {
            Platform.runLater(this::gameOver);
        }
    }

    /**
     * Sets the cellView corresponding to the changed cell to a HiddenView. Increments mineCount and updates the HeaderView
     * @param cellView CellView whose corresponding cell model has changed
     */
    private void setHidden(CellView cellView) {
        cellView.setGraphic(null);
        mineCount++;
        headerController.setBombCountText(mineCount + "");
        cellView.getStyleClass().clear();
        cellView.getStyleClass().add("button");
    }

    /**
     * Sets the cellView to a flaggedView. Decrements mineCount and updates the HeaderView.
     * @param cell the cell whose state has changed
     * @param cellView the cellView corresponding to the changed cell
     */
    private void setFlag(Cell cell, CellView cellView) {
        if (cell.getState() instanceof FlaggedState) {
            mineCount--;
            cellView.getStyleClass().add("flagged-cell");
        } else if (cell.getState() instanceof PossibleState) {
            cellView.getStyleClass().add("possible-cell");
        }

        headerController.setBombCountText(mineCount + "");
    }

    /**
     * Sets the CellView to display the number of bombs surrounding the cell. Decrements unselectedCells. If the cell
     * is surrounded by zero bombs, calls the selectNearbyCells method of the minefield. Calls game complete if the last
     * non-bomb cell has been selected.
     * @param cell the cell whose state has changed
     * @param cellView the cellView corresponding to the changed cell
     */
    private void setNumber(Cell cell, CellView cellView) {
        cellView.setButtonText(cell.getBombsNearby() + "");
        unselectedCells--;

        if (cell.getBombsNearby() == 0) {
            mineField.selectNearbyCells(cell);
        }

        if (unselectedCells == mineField.getNumMines()) {
            gameComplete();
        }
    }

    /**
     * Stops the timer and game. Updates the header, calls flagRemainingBombs and showAlert
     */
    private void gameComplete() {
        gameOver = true;
        timer.stop();
        headerController.setResetButtonStyle("victory-face");
        flagRemainingBombs();
        showAlert("YOU WIN!!!");
    }

    /**
     * Flags any bombs that were not flagged by the user. Any bombs that were correctly flagged by the user will display
     * with a green background. All other flags will have a yellow background.
     */
    private void flagRemainingBombs() {
        Iterator iterator = mineField.getIterator();
        while (iterator.hasNext()) {
            ICell c = (ICell) iterator.next();
            ICellView view = (ICellView) mineField_TilePane.getChildren().get(mineField.indexOfCell(c));
            view.disableCell();
            if (c.isBomb())  {
                if (c.getState() instanceof FlaggedState) {
                    view.addStyle("correct-flag");
                } else if (c.getState() instanceof PossibleState) {
                    c.flag();
                    c.flag();
                    view.addStyle("correct-flag");
                } else {
                    c.flag();
                }
            }
        }
    }

    /**
     * Stops the timer and game. Updates the headerView, calls revealBombs, and showAlert
     */
    private void gameOver() {
        gameOver = true;
        timer.stop();
        headerController.setResetButtonStyle("defeat-face");
        revealBombs();
        showAlert("YOU LOSE!!!");
    }

    /**
     * Reveals the location of all bombs, and makes the background red. Any bombs that were correctly flagged by the user
     * will be marked with a green background. Incorrect flags will retain a yellow background
     */
    private void revealBombs() {
        Iterator iterator = mineField.getIterator();
        while (iterator.hasNext()) {
            ICell c = (ICell) iterator.next();
            ICellView view = (CellView) mineField_TilePane.getChildren().get(mineField.indexOfCell(c));
            view.disableCell();

            if (c.isBomb()) {
                if (c.getState() instanceof MarkedState) {
                    view.addStyle("correct-flag");
                } else {
                    view.addStyle("unmarked-bomb");
                }
            }
        }
    }

    /**
     * Stops the timer and resets the game. Re-sizes the window accordingly.
     */
    public void reset() {
        timer.stop();
        initializeView();
        mineFinder.getScene().getWindow().sizeToScene();
    }

    /**
     * TimerFactory for making GameTimers
     */
    private TimerFactory timerFactory;

    /**
     * Gets the appropriate GameTimer from the Factory
     */
    private void setTimer() {
        timer = timerFactory.createTimer(modeText, difficultyText, sizeText);
    }

    /**
     * DifficultyFactory for making double describing the percentage of the field which contains a bomb
     */
    private DifficultyFactory difficultyFactory;

    /**
     * Sets the name of difficulty and gets the appropriate difficulty from the factory
     * @param difficulty String describing the desired difficulty
     */
    public void setDifficulty(String difficulty) {
        this.difficultyText = difficulty.toUpperCase();
        this.difficulty = difficultyFactory.createDifficulty(difficultyText);
    }

    /**
     * SizeFactory for making size objects describing the size of the minefield
     */
    private SizeFactory sizeFactory;

    /**
     * Sets the name of the size and gets the appropriate size from the Factory
     * @param size string describing the size of the minefield
     */
    public void setSize(String size) {
        this.sizeText = size.toUpperCase();
        this.size = sizeFactory.createSize(sizeText);
    }

    /**
     * Sets the name of the game mode
     * @param modeText String describing the current game mode
     */
    public void setModeText(String modeText) {
        this.modeText = modeText.toUpperCase();
    }

    /**
     * Turns the sound on and off
     * CURRENTLY UNSUPPORTED
     * @param sound True if the sound should be active
     */
    public void setSound(boolean sound) {
        this.sound = sound;
        new Alert(Alert.AlertType.WARNING, "Currently Unsupported").showAndWait();
    }

    /**
     * Displays the scoreboard for the current game type.
     * CURRENTLY UNSUPPORTED
     */
    public void displayScores() { new Alert(Alert.AlertType.WARNING, "Currently Unsupported").showAndWait(); }

    /**
     * Erases the scoreboard for the current game type
     * CURRENTLY UNSUPPORTED
     */
    public void eraseScores() { new Alert(Alert.AlertType.WARNING, "Currently Unsupported").showAndWait(); }

    /**
     * Calls the flag method of the cell corresponding to the given CellView
     * @param cellView a CellView which has been flagged
     */
    public void flagCell(CellView cellView) {
        int index = mineField_TilePane.getChildren().indexOf(cellView);
        mineField.flag(index);
    }

    /**
     * Calls the select method of the cell corresponding to the given CellView
     * @param cellView a CellView which has been selected
     */
    public void selectCell(CellView cellView) {
        int index = mineField_TilePane.getChildren().indexOf(cellView);
        mineField.select(index);
    }

    /**
     *  Displays an alertBox informing the player that the game is over. Displays the endGame message and the amount of
     *  time the user was playing the game.
     * @param content String describing the outcome of the game
     */
    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText(content + "\nTime: " + timer.getMasterTime() + " seconds");
        alert.showAndWait();
    }
}
