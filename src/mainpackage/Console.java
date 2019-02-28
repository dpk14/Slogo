package mainpackage;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Console {
    TextArea userInput;
    VBox consoleArea;
    Button implement;
    TextArea history;

    public Console() {
        consoleArea = new VBox();
        userInput = new TextArea();
        userInput.setWrapText(true);
        implement = new Button("Run");
        history = new TextArea();
        history.setPrefHeight(50);
        history.setEditable(false);
        history.setWrapText(true);
        consoleArea.getChildren().addAll(history, userInput, implement);

    }

    public String getText(){
        String input = userInput.getText();
        addToHistory(input);
        userInput.clear();
        return input;
    }

    private void addToHistory(String string){
        history.appendText(string);
    }

    public Button getButton(){
        return implement;
    }


    public VBox getConsoleArea(){
        return consoleArea;
    }
}
