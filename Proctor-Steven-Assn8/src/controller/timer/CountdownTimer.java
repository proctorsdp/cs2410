package controller.timer;

import controller.MineFinderController;

public class CountdownTimer extends GameTimer {

    private int initialTime;

    public CountdownTimer(MineFinderController controller, String difficulty, String size) {
        super(controller);

        size = size.toUpperCase();
        switch (size) {
            case "SMALL": this.initialTime = 3;
                break;
            case "MEDIUM": this.initialTime = 8;
                break;
            case "LARGE": this.initialTime = 15;
                break;
            default: this.initialTime = 5;
                break;
        }

        this.initialTime *= 60;

        difficulty = difficulty.toUpperCase();
        switch (difficulty) {
            case "EASY": this.initialTime *= 1;
                break;
            case "MEDIUM": this.initialTime *= 2;
                break;
            case "HARD": this.initialTime *= 3;
                break;
            default: this.initialTime *= 1;
                break;
        }

        this.time = this.initialTime;
    }

    @Override
    void incrementTime() {
        time--;
    }

    @Override
    void resetTime() {
        time = initialTime;
    }
}
