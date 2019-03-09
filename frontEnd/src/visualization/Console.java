package visualization;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import general.SystemStorage;

public class Console {
    private TextArea userInput;
    private Button implement;
    private TextArea history;
    private ShowVariables myVariableDisplay;
    private VBox consoleArea;
    private int WIDTH_OF_HISTORY_INPUT = 900;
    private int WIDTH_VARIABLE_DISPLAY = 100;


    public Console(SystemStorage mySystemStorage, int height_of_console_area) {
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
        inputHistoryBox.setPrefWidth(WIDTH_OF_HISTORY_INPUT);
        TextArea variableDisplay = myVariableDisplay.getVDisplay();
        variableDisplay.setPrefWidth(WIDTH_VARIABLE_DISPLAY);
        variableBoxArea.getChildren().addAll(inputHistoryBox, variableDisplay);
        consoleArea = new VBox();
        consoleArea.setPrefHeight(height_of_console_area);
        consoleArea.getChildren().addAll(variableBoxArea, implement);

    }

    public String getText(){
        String input = userInput.getText();
        for(String line : input.split("\n")){
            if(!line.isEmpty()) {
                addToHistory(line + "\n");
            }
        }
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
