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
    private DisplayModel myDisplayModel;
    private ErrorMessage errorMessage;
    private String color;

    public ScreenOptions(Pane canvas, SystemStorage storage, ErrorMessage error, double height_of_options) {
        mySystemStorage = storage;
        centerScreen = canvas;
        menuResource = ResourceBundle.getBundle("MenuNames");
        myDisplayModel = storage.getDisplay();
        errorMessageResource = ResourceBundle.getBundle("ErrorMessages");
        errorMessage = error;
        Menu backGroundColor = BackgroundMenu();
        Menu chooseAnimal = chooseAnimalMenu();
        Menu makeNewScreen = makeNewScreen();
        options = new MenuBar();
        options.getMenus().addAll(backGroundColor, chooseAnimal, makeNewScreen);
        options.setPrefHeight(height_of_options);
    }

    private Menu makeNewScreen(){
        Menu temp = new Menu(menuResource.getString("MakeNewString"));
        temp.setOnAction(e->{
            new Main();
        });
        return temp;
    }

    private Menu BackgroundMenu() {
        Menu temp = new Menu(menuResource.getString("ChooseColor"));
        ToggleGroup group = new ToggleGroup();
        for (String listColor: myDisplayModel.getColorsList()){
            RadioMenuItem color = new RadioMenuItem(listColor);
            color.setOnAction(e->setBackground(listColor));
            color.setToggleGroup(group);
            color.setSelected(true);
            temp.getItems().add(color);
        }
        return temp;
    }

    private Menu chooseAnimalMenu(){
        Menu temp = new Menu(menuResource.getString("ChooseAnimalType"));
        ToggleGroup group = new ToggleGroup();
        for (String imageFile: myDisplayModel.getAnimalShapesList()){
            RadioMenuItem shape = new RadioMenuItem(imageFile);
            shape.setOnAction(e->setAnimal(imageFile));
            shape.setToggleGroup(group);
            shape.setSelected(true);
            temp.getItems().add(shape);
        }
        return temp;
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

    public void setErrorDisplay(){
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
