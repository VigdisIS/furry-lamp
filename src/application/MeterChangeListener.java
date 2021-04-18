package application;

public interface MeterChangeListener {

    void metersChanged(AbstractMeter meter);

    void meterNormal(AbstractMeter meter);

    void petSick(AbstractMeter meter);

    void petBored(AbstractMeter meter);

    void petUndisciplined(AbstractMeter meter);

    void petExhausted(AbstractMeter meter);

    void petDied(AbstractMeter meter);

}
