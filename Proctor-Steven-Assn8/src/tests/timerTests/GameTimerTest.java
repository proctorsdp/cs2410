package tests.timerTests;

import controller.MineFinderController;
import controller.timer.CountdownTimer;
import controller.timer.GameTimer;
import controller.timer.SpeedDemonTimer;
import controller.timer.TraditionalTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Observable;
import java.util.Observer;

class GameTimerTest implements Observer {

    private MineFinderController controller;

    private boolean beenUpdated;

    private boolean timeOver;

    private TraditionalTimer traditionalTimer;
    private SpeedDemonTimer speedDemonTimer;
    private CountdownTimer  countdownTimer;

    @BeforeEach
    void setUp() {
        controller = new MineFinderController();

        traditionalTimer = new TraditionalTimer(controller);
        speedDemonTimer = new SpeedDemonTimer(controller);
        countdownTimer = new CountdownTimer(controller, "easy", "small");

        beenUpdated = false;
        timeOver = false;
    }

    @Test
    void traditionalTimer() throws InterruptedException {
        System.out.println("TESTING TRADITIONAL TIMER");

        traditionalTimer.addObserver(this);

        assert !traditionalTimer.isRunning();

        traditionalTimer.start();

        assert traditionalTimer.isRunning();

        traditionalTimer.reset();

        assert traditionalTimer.isRunning();

        assert traditionalTimer.getTime() == 0;

        traditionalTimer.stop();

        assert !traditionalTimer.isRunning();

        traditionalTimer.deleteObserver(this);
    }

    @Test
    void speedyTimer() {
        System.out.println("TESTING SPEEDY TIMER");

        speedDemonTimer.addObserver(this);

        assert !speedDemonTimer.isRunning();

        speedDemonTimer.start();

        assert speedDemonTimer.isRunning();

        speedDemonTimer.reset();

        assert speedDemonTimer.isRunning();

        assert speedDemonTimer.getTime() == 10;

        speedDemonTimer.stop();

        assert !speedDemonTimer.isRunning();

        speedDemonTimer.deleteObserver(this);
    }

    @Test
    void countDownTimer() {
        System.out.println("TESTING COUNTDOWN TIMER");

        countdownTimer.addObserver(this);

        assert !countdownTimer.isRunning();

        countdownTimer.start();

        assert countdownTimer.isRunning();

        countdownTimer.reset();

        assert countdownTimer.isRunning();

        assert countdownTimer.getTime() == 180;

        countdownTimer.stop();

        assert !countdownTimer.isRunning();

        countdownTimer.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameTimer) {
            GameTimer gameTimer = (GameTimer) o;
            int time = gameTimer.getTime();
            if (time == 5 && !beenUpdated) {
                gameTimer.reset();
                beenUpdated = true;
            }
            if (time > 14) {
                timeOver = true;
            }
            System.out.println(time);
        }
    }
}