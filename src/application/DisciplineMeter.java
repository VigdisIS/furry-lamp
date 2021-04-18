package application;

import javafx.beans.property.DoubleProperty;

public class DisciplineMeter extends AbstractMeter{
    private boolean disciplined;

    @Override
    void setMeterVal(double val) {
        this.numberProperty().set(val);
        try{
            fireMetersChanged();
        } catch (Exception ignored) {}

        if(getMeterVal() < 0.10){
            firePetUndsciplined();
        } else {
            fireMeterNormal();
        }
    }

    @Override
    void meterNormal() {
        setDisciplined(true);
    }

    public void setDisciplined(boolean b){
        this.disciplined = b;
    }

    public boolean isDisciplined(){
        return disciplined;
    }
}
