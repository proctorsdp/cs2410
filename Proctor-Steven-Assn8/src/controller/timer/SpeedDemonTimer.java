package controller.timer;

import controller.MineFinderController;


public class SpeedDemonTimer extends GameTimer {

    public SpeedDemonTimer(MineFinderController controller) {
        super(controller);
        this.time = 10;
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
    public void update() {
        resetTime();
//        setChanged();
//        notifyObservers();
//        clearChanged();
    }

}
