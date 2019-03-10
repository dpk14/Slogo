package interpreters;

import general.DisplayModel;
import general.ErrorMessage;
import interpreters.AnimalInterpreter;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import general.Animal;
import general.SystemStorage;

import java.util.ResourceBundle;
import java.util.Set;

public class DisplayInterpreter {

    private MenuBar options;
    private Pane centerScreen;
    private SystemStorage mySystemStorage;
    private ResourceBundle menuResource;
    private ResourceBundle errorMessageResource;
    private DisplayModel myDisplayModel;
    private Menu backgroundMenu;
    private Menu animalMenu;
    private ErrorMessage errorMessage;

    public DisplayInterpreter(Pane canvas, SystemStorage storage, AnimalInterpreter animalInterpreter, ErrorMessage error, double height_of_options) {
        mySystemStorage = storage;
        centerScreen = canvas;
        menuResource = ResourceBundle.getBundle("resources/menu_names/MenuNames");
        errorMessage = error;
        myDisplayModel = storage.getDisplay();
        errorMessageResource = ResourceBundle.getBundle("ErrorMessages");
        makeBackgroundMenu();
        makeAnimalMenu();
        options = new MenuBar();
        options.getMenus().addAll(backgroundMenu, animalMenu);
        options.setPrefHeight(height_of_options);
    }

    public void updateDisplay(){
        makeBackgroundMenu();
        setErrorDisplay();
    }

    private void makeBackgroundMenu() {
        backgroundMenu = new Menu(menuResource.getString("ChooseColor"));
        ToggleGroup group = new ToggleGroup();
        for (String listColor: myDisplayModel.getColorsList()){
            RadioMenuItem color = new RadioMenuItem(listColor);
            color.setOnAction(e->setBackground(listColor));
            color.setToggleGroup(group);
            color.setSelected(true);
            backgroundMenu.getItems().add(color);
        }
    }

    private void makeAnimalMenu(){
        animalMenu = new Menu(menuResource.getString("ChooseAnimalType"));
        ToggleGroup group = new ToggleGroup();
        for (String imageFile: myDisplayModel.getAnimalShapesList()){
            RadioMenuItem shape = new RadioMenuItem(imageFile);
            shape.setOnAction(e->setAnimal(imageFile));
            shape.setToggleGroup(group);
            shape.setSelected(true);
            animalMenu.getItems().add(shape);
        }
    }

    private void setAnimal(String animal) {
        Set<String> animal_names = mySystemStorage.getAnimalNames();
        for (String name : animal_names) {
            Animal active_animal = mySystemStorage.getAnimal(name);
            active_animal.setImage(animal);
        }
    }

    private void setBackground(String color) {
        String style = String.format("-fx-background-color: %s;", color);
        centerScreen.setStyle(style);
    }


    private void setErrorDisplay(){
        if (errorMessage.getErrorsList().size() > 0){
            for (String error: errorMessage.getErrorsList()){
                String message = errorMessageResource.getString(error);
                makeAlertBox(message);
                errorMessage.removeError(error);
            }
        }
    }

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
