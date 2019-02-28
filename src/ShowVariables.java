import javafx.scene.Node;
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
    }

    public void updateVariables(){
        Map<String, Double> variables = myStorage.getVariableMap();
        for(String variable : myStorage.getVariableNames()){
            String variablePair = String.format("%s : %.2f \n", variable, variables.get(variable));
            variableDisplay.appendText(variablePair);
        }
    }

    public Node getVDisplay(){
        return variableDisplay;
    }
}
