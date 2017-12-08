package tests.factoryTests;

import controller.MineFinderController;
import controller.factories.TimerFactory;
import controller.timer.*;
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
        IGameTimer timer = timerFactory.createTimer("traDitionAL", "medium", "small");

        assert timer instanceof TraditionalTimer;

        timer = timerFactory.createTimer("Speed Demon", "medium", "medium");

        assert timer instanceof SpeedDemonTimer;

        timer = timerFactory.createTimer("timer", "easy", "large");

        assert timer instanceof TraditionalTimer;
    }

}