import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Set;

public class AnimalScreen {

    Pane canvas;
    String STARTING_COLOR = "azure";
    public AnimalScreen(SystemStorage mySystemStorage, int height, int width ){

        canvas = new Pane();
        canvas.prefHeight(height);
        canvas.prefWidth(width);
        String style = String.format("-fx-background-color: %s;", STARTING_COLOR);
        canvas.setStyle(style);
        Animal firstAnimal = new Animal("first",height, width, canvas);
        mySystemStorage.storeAnimal("first", firstAnimal);
        canvas.getChildren().add(firstAnimal.getImageView());

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
