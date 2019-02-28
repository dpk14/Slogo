import java.util.ArrayList;

public class Setter extends ControlStructure {
    private double myVariableValue;
    private String myVariableName;

    @Override
    public double executeCode(){
        String variable=myUserInput.get(myStartingIndex+1);
        myVariableName=removeColon(variable);
        ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+2, myUserInput);
        String simplifiedExpression=simplifiedLine.get(myStartingIndex+2);
        myVariableValue=Double.parseDouble(simplifiedExpression);
        myStorage.setVariableValue(myVariableName, myVariableValue);
        return myVariableValue;
    }
}