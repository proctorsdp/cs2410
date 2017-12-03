package controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.MineField;
import model.cell.*;
import view.CellView;
import view.MineFinderHeader;
import view.MineFinderMenu;

import java.time.Duration;
import java.time.Instant;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

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
    private boolean keepPlaying;
    private int mineCount;
    private int unselectedCells;
    private String mode;
    private double difficulty;
    private int rowSize;
    private int colSize;
    private boolean sound;
    private String difficultyText;
    private Timer timer;
    private Instant start, end;

    public void initialize() {
        headerController.initialize(this);
        menuController.initialize(this);

        mineCount = 0;
        mode = "TRADITIONAL";
        difficultyText = "EASY";
        difficulty = 0.1;
        rowSize = 10;
        colSize = 10;
        sound = true;
        keepPlaying = true;

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

        for (Cell c : mineField) {
            mineField_TilePane.getChildren().add(new CellView(this));
        }

        mineField_TilePane.setPrefRows(rowSize);
        mineField_TilePane.setPrefColumns(colSize);
        mineField_TilePane.setAlignment(Pos.CENTER);

        headerController.setDifficultyText(difficultyText);
        headerController.setModeText(mode);
        headerController.setBombCountText(mineCount + "");

        keepPlaying = true;

        System.out.println(mineField);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (timer == null) {
            startTimer();
        }
        if (o instanceof Cell) {
            Cell cell = (Cell) o;
            int index = mineField.indexOf(cell);
            CellView cellView = (CellView) mineField_TilePane.getChildren().get(index);

            if (cell.getState() instanceof RevealedState) {
                cellView.disableCell();
                if (cell.isBomb()) {
                    gameOver(cell, cellView);
                } else {
                    setNumber(cell, cellView);
                }
            } else if (cell.getState() instanceof FlaggedCell) {
                setFlag(cell, cellView);
            } else if (cell.getState() instanceof HiddenState) {
                setHidden(cell, cellView);
            }
        }
        System.out.println(mineField);
    }

    private void startTimer() {
        start = Instant.now();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                while (keepPlaying) {
                    end = Instant.now();
                    Duration duration = Duration.between(start, end);
                    if (duration.toMillis() % 1000 == 0) {
                        headerController.setTimerText(duration.getSeconds() + "");
                    }
                }
                stopTimer();
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer = null;
    }

    private void setHidden(Cell cell, CellView cellView) {
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
        keepPlaying = false;
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

    private void gameOver(Cell cell, CellView cellView) {
        keepPlaying = false;
        System.out.println("You Lose!!!");
        headerController.setResetButtonImage("src/view/graphics/deadFace.png");
        revealBombs();
        cellView.setStyle("-fx-background-color: red");
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
        keepPlaying = false;
        mineField_TilePane.getChildren().clear();
        headerController.setResetButtonImage("src/view/graphics/worriedFace.png");
        headerController.setTimerText("0");
        initializeView();
        mineFinder.getScene().getWindow().sizeToScene();
    }

    public void setMode(String mode) {
        this.mode = mode.toUpperCase();
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
        size = size.toUpperCase();
        switch (size) {
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
