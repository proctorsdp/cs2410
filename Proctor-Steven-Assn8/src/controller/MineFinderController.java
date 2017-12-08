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

public class MineFinderController implements Observer, ICellViewController, IMenuController, IHeaderController {

    @FXML
    public TilePane mineField_TilePane;
    @FXML
    public VBox mineFinder;
    @FXML
    private Parent menu;
    @FXML
    private Parent header;
    @FXML
    private MineFinderMenu menuController;
    @FXML
    private MineFinderHeader headerController;

    private IMineField mineField;
    private int mineCount;
    private int unselectedCells;
    private String modeText;
    private double difficulty;
    private boolean sound;
    private boolean gameOver;
    private String difficultyText;
    private String sizeText;
    private Size size;
    private IGameTimer timer;

    public void initialize() {
        headerController.initialize(this);
        menuController.initialize(this);

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

    private void updateTimer(IGameTimer gameTimer) {
        int time = gameTimer.getTime();
        headerController.setTimerText(time + "");

        if (time <= 0) {
            Platform.runLater(this::gameOver);
        }
    }

    private void setHidden(CellView cellView) {
        cellView.setGraphic(null);
        mineCount++;
        headerController.setBombCountText(mineCount + "");
        cellView.getStyleClass().clear();
        cellView.getStyleClass().add("button");
    }

    private void setFlag(Cell cell, CellView cellView) {
        if (cell.getState() instanceof FlaggedState) {
            mineCount--;
            cellView.getStyleClass().add("flagged-cell");
        } else if (cell.getState() instanceof PossibleState) {
            cellView.getStyleClass().add("possible-cell");
        }

        headerController.setBombCountText(mineCount + "");
    }

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

    private void gameComplete() {
        gameOver = true;
        timer.stop();
        headerController.setResetButtonStyle("victory-face");
        flagRemainingBombs();
        showAlert("YOU WIN!!!");
    }

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

    private void gameOver() {
        gameOver = true;
        timer.stop();
        headerController.setResetButtonStyle("defeat-face");
        revealBombs();
        showAlert("YOU LOSE!!!");
    }

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

    public void reset() {
        timer.stop();
        initializeView();
        mineFinder.getScene().getWindow().sizeToScene();
    }

    private TimerFactory timerFactory;

    private void setTimer() {
        timer = timerFactory.createTimer(modeText, difficultyText, sizeText);
    }

    private DifficultyFactory difficultyFactory;

    public void setDifficulty(String difficulty) {
        this.difficultyText = difficulty.toUpperCase();
        this.difficulty = difficultyFactory.createDifficulty(difficultyText);
    }

    private SizeFactory sizeFactory;

    public void setSize(String size) {
        this.sizeText = size.toUpperCase();
        this.size = sizeFactory.createSize(sizeText);
    }

    public void setModeText(String modeText) {
        this.modeText = modeText.toUpperCase();
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public void displayScores() {
    }

    public void eraseScores() {
    }

    public void flagCell(CellView cellView) {
        int index = mineField_TilePane.getChildren().indexOf(cellView);
        mineField.flag(index);
    }

    public void selectCell(CellView cellView) {
        int index = mineField_TilePane.getChildren().indexOf(cellView);
        mineField.select(index);
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText(content + "\nTime: " + timer.getMasterTime() + " seconds");
        alert.showAndWait();
    }
}
