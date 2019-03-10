package visualization;

import general.SystemStorage;
import javafx.scene.control.TextArea;
import java.util.Map;

public class ShowVariables {
    SystemStorage myStorage;
    TextArea variableDisplay;

    public ShowVariables(SystemStorage storage){
        myStorage = storage;
        variableDisplay = new TextArea();
        variableDisplay.setEditable(false);
        variableDisplay.setWrapText(true);
        variableDisplay.appendText("VARIABLES:\n");
    }

    public void updateVariables(){
        Map<String, Double> variables = myStorage.getVariableMap();
        variableDisplay.clear();
        variableDisplay.appendText("VARIABLES:\n");
        for(String variable : myStorage.getVariableNames()){
            String variablePair = String.format("%s : %.2f \n", variable, variables.get(variable));
            variableDisplay.appendText(variablePair);
        }
    }

    public TextArea getVDisplay(){
        return variableDisplay;
    }
}
