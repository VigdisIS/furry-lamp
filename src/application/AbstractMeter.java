package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractMeter {
    private Collection<MeterChangeListener> listeners = new ArrayList<MeterChangeListener>();
    private DoubleProperty meterVal;

    public double getMeterVal(){
        return this.meterVal.get();
    }

    abstract void setMeterVal(double val);

    public final DoubleProperty numberProperty() {
        if(meterVal == null) {
            meterVal = new SimpleDoubleProperty(0);
        }
        return meterVal;
    }

    public void addMeterChangeListener(MeterChangeListener listener) {
        listeners.add(listener);
    }

    public void removeMeterChangeListener(MeterChangeListener listener) {
        listeners.remove(listener);
    }

    void fireMetersChanged(){
        for (MeterChangeListener listener: listeners) {
            listener.metersChanged(this);
        }
    }

    void fireMeterNormal(){
        for (MeterChangeListener listener: listeners) {
            listener.meterNormal(this);
        }
    }

    void firePetSick(){
        for (MeterChangeListener listener: listeners) {
            listener.petSick(this);
        }
    }

    void firePetBored(){
        for (MeterChangeListener listener: listeners) {
            listener.petBored(this);
        }
    }

    void firePetUndsciplined(){
        for (MeterChangeListener listener: listeners) {
            listener.petUndisciplined(this);
        }
    }

    void firePetExhausted(){
        for (MeterChangeListener listener: listeners) {
            listener.petExhausted(this);
        }
    }

    void firePetDied(){
        for (MeterChangeListener listener: listeners) {
            listener.petDied(this);
        }
    }

    abstract void meterNormal();
}
