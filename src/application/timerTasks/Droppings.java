package application.timerTasks;

import application.GUI.Controller;
import javafx.application.Platform;

import java.util.TimerTask;

public class Droppings extends TimerTask {
    private final Controller controller;

    public Droppings(Controller controller){
        this.controller = controller;
    }

    @Override
    public void run() {
        Platform.runLater(controller::generateDropping);

    }
}
