package mainpackage;

import java.util.ArrayList;

public class MakeVariable extends ControlStructure {
    private double myVariableValue;
    private String myVariableName;

    public MakeVariable(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public double executeCode(){
        String variable=myUserInput.get(myStartingIndex+1);
        myVariableName=myParser.removeColon(variable);
        ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+2, myUserInput);
        String simplifiedExpression=simplifiedLine.get(myStartingIndex+2);
        myVariableValue=Double.parseDouble(simplifiedExpression);
        myStorage.setVariableValue(myVariableName, myVariableValue);
        return myVariableValue;
    }
}