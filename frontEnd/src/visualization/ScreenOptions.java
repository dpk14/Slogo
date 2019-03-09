package visualization;

import general.DisplayModel;
import general.ErrorMessage;
import javafx.scene.control.*;
import mainpackage.Main;
import javafx.scene.layout.Pane;
import general.Animal;
import general.SystemStorage;

import java.util.ResourceBundle;
import java.util.Set;

public class ScreenOptions {

    private MenuBar options;
    private Pane centerScreen;
    private SystemStorage mySystemStorage;
    private ResourceBundle menuResource;
    private ResourceBundle errorMessageResource;
    //private ErrorMessage errorMessage;
    private String color;

    public ScreenOptions(Pane canvas, SystemStorage storage, double height_of_options) {
        mySystemStorage = storage;
        centerScreen = canvas;
        menuResource = ResourceBundle.getBundle("MenuNames");
        errorMessageResource = ResourceBundle.getBundle("ErrorMessages");
        Menu backGroundColor = BackgroundMenu();
        Menu chooseAnimal = chooseAnimalMenu();
        options = new MenuBar();
        options.getMenus().addAll(backGroundColor, chooseAnimal);
        options.setPrefHeight(height_of_options);
    }

    private Menu BackgroundMenu() {
        Menu temp = new Menu(menuResource.getString("ChooseColor"));
        ToggleGroup group = new ToggleGroup();
        var resource = ResourceBundle.getBundle("DefaultColors");
        for (String key: resource.keySet()){
            RadioMenuItem color = new RadioMenuItem(key);
            color.setOnAction(e->setBackground(resource.getString(key)));
            color.setToggleGroup(group);
            color.setSelected(true);
            temp.getItems().add(color);
        }
        return temp;
    }

    private Menu chooseAnimalMenu(){
        Menu temp = new Menu("Choose Animal Type");
        ToggleGroup group = new ToggleGroup();
        RadioMenuItem turtle  = new RadioMenuItem("Turtle");
        turtle.setOnAction(e->setAnimal("turtle"));
        turtle.setToggleGroup(group);
        turtle.setSelected(true);
        RadioMenuItem frog  = new RadioMenuItem("Frog");
        frog.setOnAction(e->setAnimal("frog"));
        frog.setToggleGroup(group);
        RadioMenuItem horse  = new RadioMenuItem("Horse");
        horse.setOnAction(e->setAnimal("horse"));
        horse.setToggleGroup(group);
        temp.getItems().addAll(turtle, frog, horse);
        return temp;
    }

    private void setAnimal(String animal) {
        Set<String> animal_names = mySystemStorage.getAnimalNames();
        for (String name : animal_names) {
            Animal active_animal = mySystemStorage.getAnimal(name);
            active_animal.setImage(String.format("%s.png", animal));
        }
    }

    private void setBackground(String color) {
        String style = String.format("-fx-background-color: %s;", color);
        centerScreen.setStyle(style);
    }

    /*
    public void setErrorDisplay(){
        if (errorMessage.getErrorsList().size() > 0){
            for (String error: errorMessage.getErrorsList()){
                String message = errorMessageResource.getString(error);
                makeAlertBox(message);
                errorMessage.removeError(error);
            }
        }
    }
    */

    private void makeAlertBox(String message){
        Alert alertBox = new Alert(Alert.AlertType.ERROR);
        alertBox.setTitle(errorMessageResource.getString("AlertBoxTitle"));
        alertBox.setContentText(message);
        alertBox.showAndWait();
    }

    public MenuBar getOptions() {
        return options;
    }

}
