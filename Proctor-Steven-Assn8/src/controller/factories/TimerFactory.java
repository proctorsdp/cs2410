package controller.factories;

import controller.MineFinderController;
import controller.timer.CountdownTimer;
import controller.timer.GameTimer;
import controller.timer.SpeedDemonTimer;
import controller.timer.TraditionalTimer;

public class TimerFactory {

    public GameTimer createTimer(String timerType, MineFinderController controller) {
        timerType = timerType.toUpperCase();
        GameTimer timer;
        switch (timerType) {
            case "TRADITIONAL": timer = new TraditionalTimer(controller);
                break;
            case "SPEED DEMON": timer = new SpeedDemonTimer(controller);
                break;
            case "COUNT DOWN": timer = new CountdownTimer(controller, controller.getDifficultyText(), controller.getSizeText());
                break;
            default:  timer = new TraditionalTimer(controller);
                break;
        }
        return timer;
    }
}
