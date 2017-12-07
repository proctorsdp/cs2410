package controller.factories;

public class DifficultyFactory {

    public double createDifficulty(String type) {
        type = type.toUpperCase();
        double difficulty;
        switch (type) {
            case "EASY": difficulty = 0.1;
                break;
            case "MEDIUM": difficulty = 0.25;
                break;
            case "HARD": difficulty = 0.4;
                break;
            default: difficulty = 0.1;
                break;
        }
        return difficulty;
    }
}
