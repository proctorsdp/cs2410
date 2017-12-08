package tests.timerTests;

import controller.timer.CountdownTimer;
import controller.timer.SpeedDemonTimer;
import controller.timer.TraditionalTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GameTimerTest {

    private TraditionalTimer traditionalTimer;
    private SpeedDemonTimer speedDemonTimer;
    private CountdownTimer  countdownTimer;

    @BeforeEach
    void setUp() {
        traditionalTimer = new TraditionalTimer();
        speedDemonTimer = new SpeedDemonTimer();
        countdownTimer = new CountdownTimer("easy", "small");
    }

    @Test
    void traditionalTimer() throws InterruptedException {
        System.out.println("TESTING TRADITIONAL TIMER");

        assert !traditionalTimer.isRunning();

        traditionalTimer.start();

        assert traditionalTimer.isRunning();

        traditionalTimer.reset();

        assert traditionalTimer.isRunning();

        assert traditionalTimer.getTime() == 0;

        traditionalTimer.stop();

        assert !traditionalTimer.isRunning();
    }

    @Test
    void speedyTimer() {
        System.out.println("TESTING SPEEDY TIMER");

        assert !speedDemonTimer.isRunning();

        speedDemonTimer.start();

        assert speedDemonTimer.isRunning();

        speedDemonTimer.reset();

        assert speedDemonTimer.isRunning();

        assert speedDemonTimer.getTime() == 10;

        speedDemonTimer.stop();

        assert !speedDemonTimer.isRunning();
    }

    @Test
    void countDownTimer() {
        System.out.println("TESTING COUNTDOWN TIMER");

        assert !countdownTimer.isRunning();

        countdownTimer.start();

        assert countdownTimer.isRunning();

        countdownTimer.reset();

        assert countdownTimer.isRunning();

        assert countdownTimer.getTime() == 180;

        countdownTimer.stop();

        assert !countdownTimer.isRunning();

    }

}