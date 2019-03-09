package visualization;

import interpreters.AnimalInterpreter;
import javafx.scene.layout.Pane;
import general.SystemStorage;

import java.util.ArrayList;

public class AnimalScreen {
    AnimalInterpreter myAnimalInterpreter;
    SystemStorage mySystemStorage;
    private Pane canvas;
    String STARTING_COLOR = "azure";

    public AnimalScreen(SystemStorage systemStorage, AnimalInterpreter interp, int height, int width ){
        mySystemStorage=systemStorage;
        myAnimalInterpreter=interp;
        canvas = new Pane();
        canvas.prefHeight(height);
        canvas.prefWidth(width);
        String style = String.format("-fx-background-color: %s;", STARTING_COLOR);
        canvas.setStyle(style);
        myAnimalInterpreter.setCanvas(canvas);
        mySystemStorage.setScreenParameters(height, width);
        mySystemStorage.getAnimal("0");
        ArrayList<String> zeroth = new ArrayList<>();
        zeroth.add("0");
        mySystemStorage.setActiveAnimals(zeroth);

    }


    public Pane getAnimalPane() {
        return canvas;
    }
}





/*
        for (String name : animal_names) {
                Set<String> animal_names = mySystemStorage.getAnimalNames();
                Animal active_animal = mySystemStorage.getAnimal(name);
                ImageView animal_image = active_animal.getImageView();
                canvas.getChildren().add(animal_image);
                }*/
