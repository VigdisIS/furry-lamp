package application;

import javafx.beans.property.DoubleProperty;

public class HealthMeter extends AbstractMeter{

    @Override
    void setMeterVal(double val) {
        this.numberProperty().set(val);

        if(getMeterVal() < 0.10){
            firePetDied();
        }
    }

    @Override
    void meterNormal() {

    }
}
