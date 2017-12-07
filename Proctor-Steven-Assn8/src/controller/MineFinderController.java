package controller;

import controller.factories.DifficultyFactory;
import controller.factories.SizeFactory;
import controller.factories.TimerFactory;
import controller.timer.GameTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.MineField;
import model.cell.*;
import view.CellView;
import view.GradingInfo;
import view.MineFinderHeader;
import view.MineFinderMenu;
import java.util.Observable;
import java.util.Observer;

public class MineFinderController implements Observer {

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

    private MineField mineField;
    private int mineCount;
    private int unselectedCells;
    private String modeText;
    private double difficulty;
    private boolean sound;
    private boolean gameOver;
    private String difficultyText;
    private String sizeText;
    private Size size;
    private GameTimer timer;

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

        GradingInfo gradingInfo = new GradingInfo();
        gradingInfo.showAndWait();
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
        } else if (o instanceof GameTimer) {
            updateTimer((GameTimer) o);
        }
        if (!timer.isRunning() && !gameOver) {
            timer.start();
        }
    }

    private void updateCell(Cell cell) {
        int index = mineField.indexOf(cell);
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

    private void updateTimer(GameTimer gameTimer) {
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
    }

    private void flagRemainingBombs() {
        for (Cell c : mineField) {
            CellView view = (CellView) mineField_TilePane.getChildren().get(mineField.indexOf(c));
            view.disableCell();
            if (c.isBomb())  {
                if (c.getState() instanceof FlaggedState) {
                    view.getStyleClass().add("correct-flag");
                } else if (c.getState() instanceof PossibleState) {
                    c.flag();
                    c.flag();
                    view.getStyleClass().add("correct-flag");
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
    }

    private void revealBombs() {
        for (Cell c : mineField) {
            CellView view = (CellView) mineField_TilePane.getChildren().get(mineField.indexOf(c));
            view.disableCell();

            if (c.isBomb()) {
                if (c.getState() instanceof MarkedState) {
                    view.getStyleClass().add("correct-flag");
                } else {
                    view.getStyleClass().add("unmarked-bomb");
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
        timer = timerFactory.createTimer(modeText, this);
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

    public String getDifficultyText() {
        return difficultyText;
    }

    public String getSizeText() {
        return sizeText;
    }
}
