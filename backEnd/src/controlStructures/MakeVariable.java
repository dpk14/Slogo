package controlStructures;

import general.ProgramParser;
import general.SystemStorage;

public class MakeVariable extends ControlStructure {
    private double myVariableValue;
    private String myVariableName;

    public MakeVariable(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new MakeVariable(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }


    @Override
    public void simplifyAndExecuteStructure(){
        String variable=mySimplifiableLine.get(myStartingIndex+1);
        myVariableName=myParser.removeColon(variable);
        simplifyAndEvaluate(mySimplifiableLine, myStartingIndex+2, myAnimal);
        String simplifiedExpression=mySimplifiableLine.get(myStartingIndex+2);
        myVariableValue=Double.parseDouble(simplifiedExpression);
        myStorage.setVariableValue(myVariableName, myVariableValue);
    }

    @Override
    public double executeCode(){
        simplifyAndExecuteStructure();
        return myVariableValue;

    }

}