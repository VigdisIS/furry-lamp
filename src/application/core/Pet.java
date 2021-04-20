package application.core;

import application.meters.*;

public class Pet implements MeterChangeListener {

    private boolean sick;
    private boolean dead;
    private int droppingsCounter;

    private final HappyMeter happyMeter;
    private final HungerMeter hungerMeter;
    private final TiredMeter tiredMeter;
    private final DisciplineMeter disciplineMeter;
    private final HealthMeter healthMeter;

    public Pet(HungerMeter hungerMeter, HappyMeter happyMeter, TiredMeter tiredMeter, DisciplineMeter disciplineMeter, HealthMeter healthMeter){
        this.hungerMeter = hungerMeter;
        this.happyMeter = happyMeter;
        this.tiredMeter = tiredMeter;
        this.disciplineMeter = disciplineMeter;
        this.healthMeter = healthMeter;
    }

    @Override
    public void metersChanged(AbstractMeter meter) {
        healthMeter.setMeterVal(calcHealth());
    }

    @Override
    public void meterNormal(AbstractMeter meter) {
        meter.meterNormal();
    }

    @Override
    public void petSick(AbstractMeter meter) {
        this.sick = true;
    }

    @Override
    public void petBored(AbstractMeter meter) {
        happyMeter.setHappy(false);
    }

    @Override
    public void petUndisciplined(AbstractMeter meter) {
        disciplineMeter.setDisciplined(false);
    }

    @Override
    public void petExhausted(AbstractMeter meter) {
        tiredMeter.setExhausted(true);
    }

    @Override
    public void petDied(AbstractMeter meter) {
        this.dead = true;
        System.out.println("Pet died.");
    }

    public int getDroppingsCounter(){
        return droppingsCounter;
    }

    public void setDroppingsCounter(int count){
        this.droppingsCounter = count;
        if(getDroppingsCounter() == 5){
            setSick(true);
        }
    }

    public void setSick(boolean b){
        this.sick = b;
    }

    public boolean isSick(){
        return sick;
    }

    public boolean isDead(){
        return dead;
    }

    public double calcHealth(){
        return ((happyMeter.getMeterVal() + disciplineMeter.getMeterVal() + hungerMeter.getMeterVal() + tiredMeter.getMeterVal())/4);
    }

    public boolean play(){
        if(disciplineMeter.isDisciplined() && !tiredMeter.isExhausted() && happyMeter.getMeterVal() <= 0.90){
            happyMeter.setMeterVal(Math.round((happyMeter.getMeterVal() + 0.10) * 100.0) / 100.0);
            tiredMeter.setMeterVal(Math.round((tiredMeter.getMeterVal() - 0.10) * 100.0) / 100.0);
            return true;
        } else{
            return false;
        }
    }

    public boolean feed(){
        if(disciplineMeter.isDisciplined() && !tiredMeter.isExhausted() && happyMeter.isHappy() && hungerMeter.getMeterVal() <= 0.90){
            hungerMeter.setMeterVal(Math.round((hungerMeter.getMeterVal() + 0.10) * 100.0) / 100.0);
            return true;
        } else{
            return false;
        }
    }

    public boolean discipline(){
        if(!tiredMeter.isExhausted() && disciplineMeter.getMeterVal() <= 0.90){
           disciplineMeter.setMeterVal(disciplineMeter.getMeterVal() + 0.1);
           return true;
        } else{
            return false;
        }
    }

    public boolean sleep(){
        if (tiredMeter.getMeterVal() <= 0.4){
            tiredMeter.setMeterVal(1.0);
            return true;
        } else{
            return false;
        }
    }

    public boolean clean(){
        if(getDroppingsCounter() > 0){
            setDroppingsCounter(0);
            return true;
        } else{
            return false;
        }
    }

    public boolean medicine(){
        if(isSick()){
            setSick(false);
            return true;
        } else{
            return false;
        }
    }
}
