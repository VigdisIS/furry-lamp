package application;

import javafx.beans.property.DoubleProperty;

public class HungerMeter extends AbstractMeter{

    @Override
    public void setMeterVal(double val){
        this.numberProperty().set(val);
        try{
            fireMetersChanged();
        } catch (Exception ignored) {}

        if(getMeterVal() < 0.10){
            firePetSick();
        }
    }

    @Override
    void meterNormal() {

    }

}
