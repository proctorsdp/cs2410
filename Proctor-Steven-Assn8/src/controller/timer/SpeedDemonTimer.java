package controller.timer;

import controller.MineFinderController;


public class SpeedDemonTimer extends GameTimer {

    public SpeedDemonTimer(MineFinderController controller) {
        super(controller);
        this.time = 10;
        this.timeDelay = 0;
    }

    @Override
    void incrementTime() {
        time--;
    }

    @Override
    void resetTime() {
        time = 10;
    }

    @Override
    public void reset() {
        reset = true;
    }

}
