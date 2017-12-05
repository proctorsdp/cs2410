package controller;

import controller.timer.CountdownTimer;
import controller.timer.GameTimer;
import controller.timer.SpeedDemonTimer;
import controller.timer.TraditionalTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.MineField;
import model.cell.*;
import view.CellView;
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
    private String mode;
    private double difficulty;
    private int rowSize;
    private int colSize;
    private boolean sound;
    private boolean gameOver;
    private String difficultyText;
    private String sizeText;
    private GameTimer timer;

    public void initialize() {
        headerController.initialize(this);
        menuController.initialize(this);

        mineCount = 0;
        mode = "TRADITIONAL";
        difficultyText = "EASY";
        difficulty = 0.1;
        sizeText = "SMALL";
        rowSize = 10;
        colSize = 10;
        sound = true;

        mineField_TilePane.setHgap(2);
        mineField_TilePane.setVgap(2);
        mineField_TilePane.setStyle("-fx-background-color: darkgrey");

        initializeView();
    }

    private void initializeView() {
        mineField = new MineField(rowSize, colSize, difficulty);
        unselectedCells = mineField.size();
        mineCount = mineField.getNumMines();
        mineField.addObserver(this);

        mineField_TilePane.getChildren().clear();

        for (Cell c : mineField) {
            mineField_TilePane.getChildren().add(new CellView(this));
        }

        mineField_TilePane.setPrefRows(rowSize);
        mineField_TilePane.setPrefColumns(colSize);
        mineField_TilePane.setAlignment(Pos.CENTER);

        headerController.setDifficultyText(difficultyText);
        headerController.setModeText(mode);
        headerController.setBombCountText(mineCount + "");
        headerController.setResetButtonImage("src/view/graphics/worriedFace.png");

        setTimer();
        timer.addObserver(this);
        headerController.setTimerText(timer.getTime() + "");

        gameOver = false;
        System.out.println(mineField);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!timer.isRunning() && !gameOver) {
            timer.start();
        }
        if (o instanceof Cell) {
            updateCell((Cell) o);
            System.out.println(mineField);
        } else if (o instanceof GameTimer) {
            updateTimer((GameTimer) o);
        }
    }

    private void updateCell(Cell cell) {
        int index = mineField.indexOf(cell);
        CellView cellView = (CellView) mineField_TilePane.getChildren().get(index);

        if (cell.getState() instanceof RevealedState) {
            cellView.disableCell();
            timer.update();
            if (cell.isBomb()) {
                gameOver();
                cellView.setStyle("-fx-background-color: red");
            } else {
                setNumber(cell, cellView);
            }
        } else if (cell.getState() instanceof FlaggedCell) {
            setFlag(cell, cellView);
        } else if (cell.getState() instanceof HiddenState) {
            setHidden(cellView);
        }
    }

    private void updateTimer(GameTimer gameTimer) {
        int time = gameTimer.getTime();
        headerController.setTimerText(time + "");
        if (time <= 0) {
            Platform.runLater(() -> gameOver());
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
            cellView.setButtonImage("src/view/graphics/flag.png");
        } else if (cell.getState() instanceof PossibleState) {
            cellView.setButtonImage("src/view/graphics/possible.png");
        }
        headerController.setBombCountText(mineCount + "");
        cellView.getStyleClass().add("flagged-button");
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
        headerController.setResetButtonImage("src/view/graphics/coolFace.png");
        System.out.println("You Win!!!");
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
        System.out.println("You Lose!!!");
        headerController.setResetButtonImage("src/view/graphics/deadFace.png");
        revealBombs();
    }

    private void revealBombs() {
        for (Cell c : mineField) {
            CellView view = (CellView) mineField_TilePane.getChildren().get(mineField.indexOf(c));
            view.disableCell();
            if (c.isBomb()) {
                if (c.getState() instanceof FlaggedCell) {
                    view.getStyleClass().add("correct-flag");
                } else {
                    view.setButtonImage("src/view/graphics/bomb.png");
                }
            }
        }
    }

    public void reset() {
        timer.reset();
        initializeView();
        mineFinder.getScene().getWindow().sizeToScene();
    }

    private void setTimer() {
        switch (mode) {
            case "TRADITIONAL": timer = new TraditionalTimer(this);
                break;
            case "SPEED DEMON": timer = new SpeedDemonTimer(this);
                break;
            case "COUNT DOWN": timer = new CountdownTimer(this, difficultyText, sizeText);
                break;
            default:  timer = new TraditionalTimer(this);
                break;
        }
    }

    public void setDifficulty(String difficulty) {
        this.difficultyText = difficulty.toUpperCase();
        switch (difficultyText) {
            case "EASY": this.difficulty = 0.1;
                break;
            case "MEDIUM": this.difficulty = 0.25;
                break;
            case "HARD": this.difficulty = 0.4;
                break;
            default: this.difficulty = 0.1;
                break;
        }
    }

    public void setSize(String size) {
        this.sizeText = size.toUpperCase();
        switch (sizeText) {
            case "SMALL":
                rowSize = 10;
                colSize = 10;
                break;
            case "MEDIUM":
                rowSize = 25;
                colSize = 25;
                break;
            case "LARGE":
                rowSize = 25;
                colSize = 50;
                break;
            default:
                rowSize = 10;
                colSize = 10;
                break;
        }
    }

    public void setMode(String mode) {
        this.mode = mode.toUpperCase();
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
}
