package view;

import javafx.scene.control.Alert;

public abstract class GameFinished extends Alert {

    public GameFinished() {
        super(AlertType.INFORMATION);
    }

}
