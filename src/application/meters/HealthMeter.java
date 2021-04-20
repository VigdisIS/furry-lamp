package application.meters;

import javafx.beans.property.DoubleProperty;

public class HealthMeter extends AbstractMeter{

    @Override
    public void setMeterVal(double val) {
        this.numberProperty().set(val);

        if(getMeterVal() < 0.10){
            firePetDied();
        }
    }

    @Override
    public void meterNormal() {

    }
}
