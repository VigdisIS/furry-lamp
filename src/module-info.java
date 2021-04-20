module Tamagotchi {

    requires  javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens application;
    opens application.GUI;
    opens application.meters;
    opens application.core;
    opens application.persistance;
    opens application.timerTasks;
}