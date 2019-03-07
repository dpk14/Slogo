package mainpackage;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

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
