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

    private void addToHistory(String string){
        history.appendText(string);
    }


    public ArrayList<String> getText(){
        String input = userInput.getText();
        ArrayList<String> textBlock = (ArrayList<String>) Arrays.asList(userInput.getText().split("\n"));
        addToHistory(input);
        userInput.clear();
        return textBlock;
    }




    public Button getButton(){
        return implement;
    }


    public VBox getConsoleArea(){
        return consoleArea;
    }
}
