package application;

import javafx.beans.property.DoubleProperty;

public class TiredMeter extends AbstractMeter{
    private boolean exhausted;

    @Override
    void setMeterVal(double val) {
        this.numberProperty().set(val);
        try{
            fireMetersChanged();
        } catch (Exception ignored) {}

        if(getMeterVal() < 0.10){
            System.out.println("Pet is exhausted.");
            firePetExhausted();
        } else {
            fireMeterNormal();
        }

    }

    @Override
    void meterNormal() {
        setExhausted(false);
    }

    public void setExhausted(boolean b){
        this.exhausted = b;
    }

    public boolean isExhausted(){
        return exhausted;
    }
}