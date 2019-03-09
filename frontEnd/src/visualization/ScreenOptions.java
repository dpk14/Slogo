package visualization;

import mainpackage.Main;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import general.Animal;
import general.SystemStorage;

import java.util.Set;


public class ScreenOptions {

    MenuBar options;
    Pane centerScreen;
    SystemStorage mySystemStorage;


    public ScreenOptions(Pane canvas, SystemStorage storage, double height_of_options) {
        mySystemStorage = storage;

        centerScreen = canvas;

        Menu backGroundColor = BackgroundMenu();
        Menu chooseAnimal = chooseAnimalMenu();
        Menu makeNewScreen = makeNewScreen();
        options = new MenuBar();
        options.getMenus().addAll(backGroundColor, chooseAnimal, makeNewScreen);
        options.setPrefHeight(height_of_options);
    }

    private Menu makeNewScreen(){
         Menu temp = new Menu("Make new screen");
        temp.setOnAction(e->{
            new Main();
        });
        return temp;
    }

    private Menu BackgroundMenu() {
        Menu temp = new Menu("Choose Color");
        ToggleGroup group = new ToggleGroup();
        RadioMenuItem azure  = new RadioMenuItem("Azure");
        azure.setOnAction(e->setBackground("azure"));
        azure.setToggleGroup(group);
        azure.setSelected(true);
        RadioMenuItem blue = new RadioMenuItem("Blue");
        blue.setToggleGroup(group);
        blue.setOnAction(e->setBackground("lightblue"));
        RadioMenuItem yellow = new RadioMenuItem("Yellow");
        yellow.setToggleGroup(group);
        yellow.setOnAction(e->setBackground("lightyellow"));
        RadioMenuItem green  = new RadioMenuItem("Green");
        green.setToggleGroup(group);
        green.setOnAction(e->setBackground("lightgreen"));
        temp.getItems().addAll(azure, blue, yellow, green);
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


    public MenuBar getOptions() {
        return options;
    }



}
