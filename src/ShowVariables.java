import javafx.scene.control.TextArea;

import java.util.Map;

public class ShowVariables {
    SystemStorage myStorage;

    public ShowVariables(SystemStorage storage){
        TextArea variables = new TextArea();
        variables.setEditable(false);
        myStorage = storage;
    }

    public void updateVariables(){
        Map<String, Double> variables = myStorage.getVariableMap();
        for(String variable : myStorage.getVariableNames()){
            String variablePair = String.format("%s : %.2f", variable, variables.get(variable));
        }
    }
}
