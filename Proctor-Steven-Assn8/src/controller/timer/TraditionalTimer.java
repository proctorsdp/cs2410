package controller.timer;

import controller.MineFinderController;

public class TraditionalTimer extends GameTimer {

    public TraditionalTimer(MineFinderController controller) {
        super(controller);
        this.time = 0;
    }

    @Override
    void incrementTime() {
        time++;
    }

    @Override
    void resetTime() {
        time = 0;
    }

}
