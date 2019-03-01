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
        String variable=mySimplifiableLine.get(myStartingIndex+1);
        myVariableName=myParser.removeColon(variable);
        simplifyAndEvaluate(mySimplifiableLine, myStartingIndex+2);
        String simplifiedExpression=mySimplifiableLine.get(myStartingIndex+2);
        myVariableValue=Double.parseDouble(simplifiedExpression);
        myStorage.setVariableValue(myVariableName, myVariableValue);
        return myVariableValue;
    }

    @Override
    protected ArrayList<String> replaceCodeWithReturnValue(double returnValue, ArrayList<String> simplifiableLine){
        for(int k=0; k<3; k++) {
                simplifiableLine.remove(myStartingIndex);
            }
        simplifiableLine.add(myStartingIndex, Double.toString(returnValue));
        return simplifiableLine;
    }
}