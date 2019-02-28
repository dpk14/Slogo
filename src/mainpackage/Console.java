package mainpackage;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Console {
    TextArea userInput;
    Button implement;
    TextArea history;
    ShowVariables myVariableDisplay;
    VBox consoleArea;

    public Console(SystemStorage mySystemStorage) {
        myVariableDisplay = new ShowVariables(mySystemStorage);
        var inputHistoryBox = new VBox();
        var variableBoxArea = new HBox();
        userInput = new TextArea();
        userInput.setWrapText(true);
        implement = new Button("Run");
        history = new TextArea();
        history.setPrefHeight(50);
        history.setEditable(false);
        history.setWrapText(true);
        inputHistoryBox.getChildren().addAll(history, userInput);
        inputHistoryBox.setPrefWidth(900);
        TextArea variableDisplay = myVariableDisplay.getVDisplay();
        variableDisplay.setPrefWidth(100);
        variableBoxArea.getChildren().addAll(inputHistoryBox, variableDisplay);
        consoleArea = new VBox();
        consoleArea.getChildren().addAll(variableBoxArea, implement);

    }

    public String getText(){
        String input = userInput.getText();
        addToHistory(input+"\n");
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

    public void updateVariables(){
        myVariableDisplay.updateVariables();
    }
}
