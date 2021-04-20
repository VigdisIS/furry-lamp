package application.meters;

import javafx.beans.property.DoubleProperty;

public class HappyMeter extends AbstractMeter{
    private boolean happy;

    @Override
    public void setMeterVal(double val){
        this.numberProperty().set(val);
        try{
            fireMetersChanged();
        } catch (Exception ignored) {}

        if(getMeterVal() < 0.10){
            firePetBored();
        } else {
            fireMeterNormal();
        }
    }

    @Override
    public void meterNormal() {
        setHappy(true);
    }

    public void setHappy(boolean b){
        this.happy = b;
    }

    public boolean isHappy(){
        return happy;
    }

}
