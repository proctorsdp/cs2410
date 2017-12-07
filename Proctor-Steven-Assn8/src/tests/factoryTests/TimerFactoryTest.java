package tests.factoryTests;

import controller.MineFinderController;
import controller.factories.TimerFactory;
import controller.timer.CountdownTimer;
import controller.timer.GameTimer;
import controller.timer.SpeedDemonTimer;
import controller.timer.TraditionalTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TimerFactoryTest {

    private MineFinderController controller;

    private TimerFactory timerFactory;

    @BeforeEach
    void setUp() {
        timerFactory = new TimerFactory();
        controller = new MineFinderController();
    }

    @Test
    void createTimer() {
        GameTimer timer = timerFactory.createTimer("traDitionAL", controller);

        assert timer instanceof TraditionalTimer;

        timer = timerFactory.createTimer("Speed Demon", controller);

        assert timer instanceof SpeedDemonTimer;

        timer = timerFactory.createTimer("timer", controller);

        assert timer instanceof TraditionalTimer;
    }

}