package application;

import javafx.application.Platform;

import java.util.Collection;
import java.util.TimerTask;

public class DepleteMeters extends TimerTask {
    private final Collection<AbstractMeter> meters;
    private final Controller controller;

    public DepleteMeters(Collection<AbstractMeter> meters, Controller controller){
        this.meters = meters;
        this.controller = controller;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            depleteMeters(meters);
        });

    }

    private void depleteMeters(Collection<AbstractMeter> meters){
        for (AbstractMeter meter: meters) {
            meter.setMeterVal(Math.round((meter.getMeterVal() - 0.10) * 100.0) / 100.0);
        }

        controller.toggleDeath();
        System.out.println("Depleted meters.");
    }
}
