package mainpackage;

import javafx.scene.layout.Pane;
import mainpackage.Animal;
import mainpackage.SystemStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public class AnimalScreen {

    Pane canvas;
    String STARTING_COLOR = "azure";
    public AnimalScreen(SystemStorage mySystemStorage, int height, int width ){

        canvas = new Pane();
        canvas.prefHeight(height);
        canvas.prefWidth(width);
        String style = String.format("-fx-background-color: %s;", STARTING_COLOR);
        canvas.setStyle(style);
        mySystemStorage.setScreenParameters(canvas, height, width);
        Animal zeroth=mySystemStorage.getAnimal("0");
        List<Entry<String, Animal>> activeAnimals = new ArrayList<>();
        activeAnimals.add(new SimpleEntry<>("0", zeroth));
        mySystemStorage.setActiveAnimals(activeAnimals);

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
