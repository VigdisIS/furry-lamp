package application;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private final HungerMeter hungerVal = new HungerMeter();
    private final HappyMeter happyVal = new HappyMeter();
    private final TiredMeter tiredVal = new TiredMeter();
    private final DisciplineMeter disciplineVal = new DisciplineMeter();
    private final HealthMeter healthVal = new HealthMeter();

    private final Collection<AbstractMeter> meters = new ArrayList<>();

    private final Pet pet = new Pet(hungerVal, happyVal, tiredVal, disciplineVal, healthVal);

    private final CareCenter careCenter = new CareCenter(pet);

    private final Timer depleteTimer = new Timer();
    private final Timer droppingsTimer = new Timer();

    private final LoadSave progress = new LoadSave();

    public AnchorPane base;
    public Pane petArea;
    public Button feedBtn;
    public Button bathBtn;
    public Button disciplineBtn;
    public Button playBtn;
    public Button medicineBtn;
    public Button sleepBtn;
    public ProgressBar happyMeter;
    public ProgressBar hungerMeter;
    public ProgressBar tiredMeter;
    public ProgressBar disciplineMeter;
    public ProgressBar healthMeter;
    public TextArea actionText;

    public void initialize(){
        Collections.addAll(meters, hungerVal, happyVal, tiredVal, disciplineVal, healthVal);

        for (AbstractMeter meter: meters) {
            meter.addMeterChangeListener(pet);
        }

        String[] data_split = progress.readFromFile("src/application/tamagotchiSave.txt").split("\n");

        hungerVal.setMeterVal(Double.parseDouble(data_split[0]));
        happyVal.setMeterVal(Double.parseDouble(data_split[1]));
        tiredVal.setMeterVal(Double.parseDouble(data_split[2]));
        disciplineVal.setMeterVal(Double.parseDouble(data_split[3]));
        healthVal.setMeterVal(pet.calcHealth());

        hungerMeter.progressProperty().bind(hungerVal.numberProperty());
        happyMeter.progressProperty().bind(happyVal.numberProperty());
        tiredMeter.progressProperty().bind(tiredVal.numberProperty());
        disciplineMeter.progressProperty().bind(disciplineVal.numberProperty());
        healthMeter.progressProperty().bind(healthVal.numberProperty());

        actionText.setText("Loaded data from last save successfully.");

        meters.remove(healthVal);
        meters.remove(tiredVal);
        depleteTimer.schedule(new DepleteMeters(meters, this), 5000, 5000);

        droppingsTimer.schedule(new Droppings(this), 17000, 17000);
    }

    public void generateDropping(){
        if(pet.getDroppingsCounter() < 5){
            pet.setDroppingsCounter(pet.getDroppingsCounter() + 1);
            Random random = new Random();
            int ranX = random.nextInt(230);
            System.out.println("Random X: "+ranX);
            int ranY = random.nextInt(230);

            Pane dropping = new Pane();
            dropping.setPrefSize(50, 50);
            dropping.setStyle("-fx-background-color: DAE6F3;");
            dropping.setLayoutX(ranX);
            dropping.setLayoutY(ranY);
            petArea.getChildren().add(dropping);

            toggleSick();
        }

    }

    public void toggleSick(){
        List<Node> buttons = base.getChildren()
                .stream()
                .filter(obj -> obj instanceof Button && !obj.getId().equals("medicineBtn"))
                .collect(Collectors.toList());

        for(Node button : buttons){
            button.setDisable(pet.isSick());
        }
    }

    public void toggleDeath(){
        if(pet.isDead()){
            List<Node> buttons = base.getChildren()
                    .stream()
                    .filter(obj -> obj instanceof Button)
                    .collect(Collectors.toList());

            for(Node button : buttons){
                button.setDisable(pet.isDead());
            }

            depleteTimer.cancel();
            depleteTimer.purge();
            droppingsTimer.cancel();
            droppingsTimer.purge();
            actionText.setText("Pet died");
        }
    }

    public void feedBtn(ActionEvent e){
        actionText.setText(careCenter.feed());
    }

    public void playBtn(ActionEvent e){
        actionText.setText(careCenter.play());
    }

    public void disciplineBtn(ActionEvent e){
        actionText.setText(careCenter.discipline());
    }

    public void sleepBtn(ActionEvent e){
        actionText.setText(careCenter.sleep());
    }

    public void cleanBtn(ActionEvent e){
        petArea.getChildren().clear();
        actionText.setText(careCenter.clean());
    }

    public void medicineBtn(ActionEvent e){
        actionText.setText(careCenter.medicine());
        toggleSick();
    }



}
