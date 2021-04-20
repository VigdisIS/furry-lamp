package application.core;

public class CareCenter{
    private final Pet pet;

    public CareCenter(Pet pet){
        this.pet = pet;
    }

    public String feed(){
        return (pet.feed()) ? "Pet was successfully fed" : "Pet is not hungry right now";
    }

    public String play(){
        return (pet.play()) ? "Pet had fun playing with you<3" : "Pet does not want to play right now";
    }

    public String discipline(){
        return (pet.discipline()) ? "Pet was disciplined" : "Pet does not need to be disciplined right now";
    }

    public String sleep(){
        return (pet.sleep()) ? "Pet slept well<3" : "Pet is not tired right now";
    }

    public String clean(){
        return (pet.clean()) ? "Pet is now clean" : "Pet doesn't need to be cleaned";
    }

    public String medicine(){
        return (pet.medicine()) ? "Pet was given medicine" : "Pet doesn't need medicine";
    }
}
